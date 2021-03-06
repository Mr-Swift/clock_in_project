package com.njusc.elasticSearch;


import com.njusc.util.CustomConfig;
import com.njusc.npm.utils.util.JsonUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.util.CollectionUtils;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * elasticSearch ?????????
 * @author Michael
 */
public class EsClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(EsClientUtil.class);


    /**
     * ????????????client????????????????????????2????????????????????????????????????????????????????????????????????????
     */
    private static RestHighLevelClient client;
    private static BulkProcessor bulkProcessor;

    /**
     * ??????ES???hosts
     *
     * @return
     */
    private static HttpHost[] assembleESAddress() {
        HttpHost httpHost1 = new HttpHost(CustomConfig.esIpAddress(), Integer.parseInt(CustomConfig.esIpPort()), "http");
        List<HttpHost> list = new ArrayList<HttpHost>();
        list.add(httpHost1);
        //list.add(httpHost2);
        HttpHost[] ipHost = new HttpHost[list.size()];
        HttpHost[] httpHosts = list.toArray(ipHost);
        return httpHosts;
    }

    /**
     * ??????client??????
     */
    public static RestHighLevelClient getClient() {
        if (client == null) {
            synchronized (EsClientUtil.class) {
                try {
                    if (client == null) {
                        /** ?????????????????? */
                        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                        /** ?????????????????? */
                        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(CustomConfig.esUserName(), CustomConfig.esPassword()));
                        /** ??????rest client?????? */
                        RestClientBuilder builder = RestClient.builder(assembleESAddress())
                                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                                    @Override
                                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                                    }
                                });
                        client = new RestHighLevelClient(builder);
                    }
                } catch (Exception e) {
                    logger.error("EsClient????????????...." + client, e);
                }
            }
        }
        return client;
    }


    /**
     * ??????client??????
     */
    public static void closeClient() {
        if (client != null) {
            synchronized (EsClientUtil.class) {
                try {
                    client.close();
                    logger.info("ES Client ????????????...");
                } catch (Exception e) {
                    logger.error("ES Client????????????...", e);
                }
            }
        }
    }
    /**
     * ????????????false??????
     * @param index  ????????????????????????
     * @param objects     ??????
     * @return ????????????
     */
    public static   boolean batchSave(String index,String type, List<Object> objects) {
        BulkRequest bulkRequest = new BulkRequest();
        BulkResponse response=null;
        //????????????????????????20???
        for (Object object: objects) {
            IndexRequest request = new IndexRequest(index,type);
            request.source(JsonUtil.jsonToString(object), XContentType.JSON);
            bulkRequest.add(request);
        }
        try {
            response=getClient().bulk(bulkRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
        return !response.hasFailures();
    }

    /**
     * ????????????
     */
    public static void saveData(String dataBaseName,String tableName, String rowId, Map<String, Object> m) {
        try {
            RestHighLevelClient client = getClient();
            IndexRequest indexRequest = new IndexRequest(dataBaseName)
                    .id(rowId)
                    .type(tableName)
                    .source(m);
            client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
           e.printStackTrace();
        }
    }
    /**
     * ????????????????????????
     * @param index
     * @return
     */
    public static   boolean isIndexExist(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(index);
        boolean exists = getClient().indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }
    /**
     * ????????????
     * @param index
     * @return
     */
    public static boolean deleteIndex(String index) throws IOException {
        if(!isIndexExist(index)) {
            logger.error("Index is not exits!");
            return false;
        }
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        AcknowledgedResponse delete = getClient().indices()
                .delete(request, RequestOptions.DEFAULT);
        return delete.isAcknowledged();
    }
    /**
     * ????????????
     */
    public static void deleteData(String dataBaseName,String tableName, String rowId) {
        try {
            RestHighLevelClient client = getClient();
            DeleteRequest indexRequest = new DeleteRequest(dataBaseName)
                    .id(rowId)
                    .type(tableName);
            client.delete(indexRequest,RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
    }

    /**
     * ???????????? BulkProcessor ???????????????
     */
    public static BulkProcessor getBulkProcessor() {
        if (bulkProcessor == null) {
            synchronized (EsClientUtil.class) {
                try {
                    if (bulkProcessor == null) {
                        bulkProcessor = bulkProcessor(getClient());
                    }
                } catch (Exception e) {
                    logger.error("BulkProcessor????????????...." + bulkProcessor, e);
                }
            }
        }
        return bulkProcessor;
    }


    /**
     * ????????? BulkProcessor
     *
     * @param client
     * @return
     */
    public static BulkProcessor bulkProcessor(RestHighLevelClient client) {
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                //bulk???????????????
                int numberOfActions = request.numberOfActions();
                logger.info("ES Executing bulk [{}] with {} request ", executionId, numberOfActions);
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                //bulk???????????????
                if (response.hasFailures()) {
                    logger.error("ES Bulk [{}] executed with failures ", +executionId);
                } else {
                    logger.info("ES Bulk [{}] completed in {} milliseconds ", executionId, response.getTook().getMillis());
                }
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                // ???????????????
                logger.error("ES Bulk Failed to execute bulk ", failure);
            }
        };

        BulkProcessor bulkProcessor = BulkProcessor.builder(
                (request, bulkListener) -> client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
                listener)
                //  ?????????????????????
                .setBulkActions(20000)
                // ?????? ???????????????
                .setBulkSize(new ByteSizeValue(10, ByteSizeUnit.MB))
                // ???????????????????????????
                .setFlushInterval(TimeValue.timeValueSeconds(300))
                //???????????????
                .setConcurrentRequests(5)
                // ??????????????????
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)).build();
        return bulkProcessor;
    }

    /**
     * ??????es????????????
     */
    public static  Map<String, Object> searchData(String dataBaseName,String tableName, String rowId) {

        try {
            RestHighLevelClient client = getClient();
            GetRequest getRequest = new GetRequest(dataBaseName, tableName, rowId);
            //????????????
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            if (getResponse.isExists()) {
                Map<String, Object> result = getResponse.getSourceAsMap();
                return result;
            }
        }catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return null;

    }
    /**
     * ?????????????????????15???
     * @param index ??????
     * @param where where??????
     * @param isAnd  where?????????and??????or??????????????????????????????????????????and
     */
    public static List<Map<String, Object>> searchIndex(String index,Map<String, Object> where,Boolean isAnd)
    {
        return searchIndex(index,null,-1,-1,where,null,null,null,null,isAnd);
    }
    /**
     * ??????????????????
     * @param index ??????
     * @param type ????????????????????????
     * @param from 0??????????????????
     * @param size ?????????
     * @param where where??????
     * @param sortFieldsToAsc ????????????
     * @param includeFields ???????????????????????? ,????????????????????????
     * @param excludeFields ????????????????????????
     * @param timeOut ???????????????????????????60???
     * @param isAnd  where?????????and??????or??????????????????????????????????????????and
     */
    public static List<Map<String, Object>> searchIndex(String index,String[] type, int from, int size, Map<String, Object> where,
                                                        Map<String, Boolean> sortFieldsToAsc, String[] includeFields, String[] excludeFields,
                                                        Integer timeOut,Boolean isAnd) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            //??????
            if (where != null && !where.isEmpty()) {
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                where.forEach((k, v) -> {
                    if (v instanceof Map) {
                        //????????????map  ????????????
                        Map<String, Date> mapV = (Map<String, Date>) v;
                        if (mapV != null) {
                            boolQueryBuilder.must(
                                    QueryBuilders.rangeQuery(k).
                                            gte(format.format(mapV.get("start"))).
                                            lt(format.format(mapV.get("end"))));
                        }
                    } else {
                        //??????????????????
                        if(isAnd!=null&&!isAnd)
                        {
                            boolQueryBuilder.should(QueryBuilders.wildcardQuery(k, v.toString()));
                        }
                        else {
                            boolQueryBuilder.must(QueryBuilders.wildcardQuery(k, v.toString()));
                        }
                    }
                });
                sourceBuilder.query(boolQueryBuilder);
            }

            //??????
            from = from <= -1 ? 0 : from;
            size = size >= 1000 ? 1000 : size;
            size = size <= 0 ? 15 : size;
            sourceBuilder.from(from);
            sourceBuilder.size(size);

            //??????
            if(timeOut==null)timeOut=Integer.parseInt(CustomConfig.timeout());
            sourceBuilder.timeout(new TimeValue(timeOut, TimeUnit.SECONDS));

            //??????
            if (sortFieldsToAsc != null && !sortFieldsToAsc.isEmpty()) {
                sortFieldsToAsc.forEach((k, v) -> {
                    sourceBuilder.sort(new FieldSortBuilder(k).order(v ? SortOrder.ASC : SortOrder.DESC));
                });
            } else {
                sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
            }

            //??????????????????
            if (!CollectionUtils.isEmpty(includeFields) || !CollectionUtils.isEmpty(excludeFields)) {
                sourceBuilder.fetchSource(includeFields, excludeFields);
            }

            SearchRequest rq = new SearchRequest();
            //??????
            rq.indices(index);
            //??????????????????
            if(!StringUtils.isEmpty(type))
            {
                rq.types(type);
            }
            rq.source(sourceBuilder);

            //??????
            System.out.println(rq.source().toString());
            SearchResponse rp = getClient().search(rq,RequestOptions.DEFAULT);

            //????????????
            if (rp.status() != RestStatus.OK || rp.getHits().getTotalHits() <= 0) {
                return Collections.emptyList();
            }

            //??????source
            return Arrays.stream(rp.getHits().getHits()).map(b -> {
                return b.getSourceAsMap();
            }).collect(Collectors.toList());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }
    public static void main(String[] args) throws IOException {
//        Date d = new Date();
//        String id = d.getTime() + "";
//        id = "test1";
//        Map<String, Object> m = new HashMap<String, Object>(16);
//        m.put("id", id);
//        m.put("area_id", 2);
//        m.put("camera_id", 2);
//        m.put("log_time", new Date().toString());
//        m.put("age", 2);
//        //?????????????????????id???????????????????????????,??????id??????????????????????????????
//        EsClientUtil.saveData("global_house_list2","data", id, m);
//        //????????????
//        //EsClientUtil.deleteData("global_house_list2","data", id);
//        Map<String, Object> objectMap = searchData("global_house_list2", "data", id);
//        System.out.printf(objectMap.toString());



        //??????
        Map whereMap=new HashMap();
        whereMap.put("camera_id","*camera_id*");
        //List houseList2 = searchIndex("global_house_list2", new String[]{"data"}, 0, 10, whereMap, null, null, null, null,true);
        System.out.printf("1");

        List houseList3 = EsClientUtil.searchIndex("xyfwjg_credit_company", whereMap,true);
        System.out.printf("1");
    }


}