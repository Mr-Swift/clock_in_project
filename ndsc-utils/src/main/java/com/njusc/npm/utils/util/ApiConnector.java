/**
 * APIConnector.java
 */
package com.njusc.npm.utils.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author jinzf
 * @date Oct 17, 2014
 * @description Api连接者
 * @version 1.0
 */
public final class ApiConnector {
    private static final Logger logger = LoggerFactory
            .getLogger(ApiConnector.class);

    private static PoolingHttpClientConnectionManager connManager = null;
    private static CloseableHttpClient httpclient = null;

    /**
     * 最大连接数
     */
    public final static int MAX_TOTAL_CONNECTIONS = 800;
    /**
     * 获取连接的最大等待时间
     */
    public final static int WAIT_TIMEOUT = 60000;
    /**
     * 每个路由最大连接数
     */
    public final static int MAX_ROUTE_CONNECTIONS = 400;
    /**
     * 连接超时时间
     */
    public final static int CONNECT_TIMEOUT = 10000;

    static {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .build();

        connManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);

        // Create socket configuration
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true)
                .build();

        connManager.setDefaultSocketConfig(socketConfig);
        // Create message constraints
        MessageConstraints messageConstraints = MessageConstraints.custom()
                .setMaxHeaderCount(200).setMaxLineLength(2000).build();
        // Create connection configuration
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints).build();
        connManager.setDefaultConnectionConfig(connectionConfig);
        connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        connManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);

        httpclient = HttpClients.custom().setConnectionManager(connManager)
                .build();
    }

    public static String post(String accountId, String password,List<NameValuePair> pairs,
            String encoding) {
        //HttpPost post = new HttpPost(url.trim());
        
        return "";
    }
    
    //短信息测试
    public static String postTest(String url, List<NameValuePair> pairs,String encoding) {
        HttpPost post = new HttpPost(url.trim());
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(CONNECT_TIMEOUT)
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CONNECT_TIMEOUT)
                    .setExpectContinueEnabled(false).build();
            post.setConfig(requestConfig);
            if (pairs != null && pairs.size() > 0) {
                post.setEntity(new UrlEncodedFormEntity(pairs, encoding));
            }

            logger.info("[HttpUtils Post] begin invoke url:{} , params:{}",
                    url, pairs);
            long s1 = System.currentTimeMillis();
            CloseableHttpResponse response = httpclient.execute(post);
            long s2 = System.currentTimeMillis();
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        String str = EntityUtils.toString(entity, encoding);
                        logger.info(
                                "[HttpUtils Post]Debug response, url : {}  , params : {}, response string : {} ,time : {}",
                                url, pairs, str, s2 - s1);
                        return str;
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {
            post.releaseConnection();
        }
        return "";
    }
    
    public static String post(String url, List<NameValuePair> pairs,
            String encoding) {
        HttpPost post = new HttpPost(url.trim());
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(CONNECT_TIMEOUT)
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CONNECT_TIMEOUT)
                    .setExpectContinueEnabled(false).build();
            post.setConfig(requestConfig);
            if (pairs != null && pairs.size() > 0) {
                post.setEntity(new UrlEncodedFormEntity(pairs, encoding));
            }

            logger.info("[HttpUtils Post] begin invoke url:{} , params:{}",
                    url, pairs);
            long s1 = System.currentTimeMillis();
            CloseableHttpResponse response = httpclient.execute(post);
            long s2 = System.currentTimeMillis();
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        String str = EntityUtils.toString(entity, encoding);
                        logger.info(
                                "[HttpUtils Post]Debug response, url : {}  , params : {}, response string : {} ,time : {}",
                                url, pairs, str, s2 - s1);
                        return str;
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {
            post.releaseConnection();
        }
        return "";
    }

    public static String post(String url, List<NameValuePair> pairs) {
        HttpPost post = new HttpPost(url.trim());
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(CONNECT_TIMEOUT)
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CONNECT_TIMEOUT)
                    .setExpectContinueEnabled(false).build();
            post.setConfig(requestConfig);

            if (pairs != null && pairs.size() > 0) {
                post.setEntity(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
            }

            logger.info("[HttpUtils Post] begin invoke url:{} , params:{}",
                    url, pairs);
            long s1 = System.currentTimeMillis();
            CloseableHttpResponse response = httpclient.execute(post);
            long s2 = System.currentTimeMillis();
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        String str = EntityUtils.toString(entity, Consts.UTF_8);
                        logger.info(
                                "[HttpUtils Post]Debug response, url : {}  , params : {}, response string : {} ,time : {}",
                                url, pairs, str, s2 - s1);
                        return str;
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {
            post.releaseConnection();
        }
        return "";
    }

    @SuppressWarnings("deprecation")
    public static String get(String url, List<NameValuePair> pairs,
            String encode) {
        String responseString = null;
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(CONNECT_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECT_TIMEOUT).build();

        StringBuilder sb = new StringBuilder();
        sb.append(url.trim());
        int i = 0;
        if (pairs != null && pairs.size() > 0) {
            for (NameValuePair entry : pairs) {
                if (i == 0 && !url.contains("?")) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(entry.getName());
                sb.append("=");
                String value = entry.getValue();
                try {
                    sb.append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    logger.warn("encode http get params error, value is {}",
                            value, e);
                    sb.append(URLEncoder.encode(value));
                }
                i++;
            }
        }

        logger.info("[HttpUtils Get] begin invoke:" + url.toString());
        HttpGet get = new HttpGet(url.toString());
        get.setConfig(requestConfig);
        try {
            long s1 = System.currentTimeMillis();
            CloseableHttpResponse response = httpclient.execute(get);
            long s2 = System.currentTimeMillis();
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        responseString = EntityUtils.toString(entity, encode);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } catch (Exception e) {
                logger.error("[HttpUtils Get]get response error, url:{}",
                        url.toString(), e);
                return responseString;
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            logger.info(
                    "[HttpUtils Get]Debug url:{} , response string :{},time={}",
                    url.toString(), responseString, s2 - s1);
        } catch (SocketTimeoutException e) {
            logger.error("[HttpUtils Get]invoke get timout error, url:{}",
                    url.toString(), e);
            return responseString;
        } catch (Exception e) {
            logger.error("[HttpUtils Get]invoke get error, url:{}",
                    url.toString(), e);
        } finally {
            get.releaseConnection();
        }
        return responseString;
    }

    public static String postJson(String url, JSON data, String encode) {
        String responseString = null;

        logger.info("[HttpUtils PostJson] begin invoke:{},param:{}",
                url.toString(), data);
        HttpPost post = new HttpPost(url.trim());
        post.setEntity(new StringEntity(data.toJSONString(),
                ContentType.APPLICATION_JSON));

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(CONNECT_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECT_TIMEOUT).build();
        post.setConfig(requestConfig);

        try {
            long s1 = System.currentTimeMillis();
            CloseableHttpResponse response = httpclient.execute(post);
            long s2 = System.currentTimeMillis();
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        responseString = EntityUtils.toString(entity, encode);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } catch (Exception e) {
                logger.error("[HttpUtils PostJson]post response error, url:{}",
                        url.toString(), e);
                return responseString;
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            logger.info(
                    "[HttpUtils Post]Debug response, url : {}  , params : {}, response string : {} ,time : {}",
                    url, data, responseString, s2 - s1);
        } catch (SocketTimeoutException e) {
            logger.error(
                    "[HttpUtils PostJson]invoke post timout error, url:{}",
                    url.toString(), e);
            return responseString;
        } catch (Exception e) {
            logger.error("[HttpUtils PostJson]invoke post error, url:{}",
                    url.toString(), e);
        } finally {
            post.releaseConnection();
        }
        return responseString;
    }

    public static String postJson(String url, JSON data) {
        String responseString = null;

        logger.info("[HttpUtils PostJson] begin invoke:{},param:{}",
                url.toString(), data);
        HttpPost post = new HttpPost(url.trim());
        post.setEntity(new StringEntity(data.toJSONString(),
                ContentType.APPLICATION_JSON));

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(CONNECT_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECT_TIMEOUT).build();
        post.setConfig(requestConfig);

        try {
            long s1 = System.currentTimeMillis();
            CloseableHttpResponse response = httpclient.execute(post);
            long s2 = System.currentTimeMillis();
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        responseString = EntityUtils.toString(entity,
                                Consts.UTF_8);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } catch (Exception e) {
                logger.error("[HttpUtils PostJson]post response error, url:{}",
                        url.toString(), e);
                return responseString;
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            logger.info(
                    "[HttpUtils PostJson]Debug url:{} , response string :{},time={}",
                    url.toString(), responseString, s2 - s1);
        } catch (SocketTimeoutException e) {
            logger.error(
                    "[HttpUtils PostJson]invoke post timout error, url:{}",
                    url.toString(), e);
            return responseString;
        } catch (Exception e) {
            logger.error("[HttpUtils PostJson]invoke post error, url:{}",
                    url.toString(), e);
        } finally {
            post.releaseConnection();
        }
        return responseString;
    }

    @SuppressWarnings("deprecation")
    public static String get(String url, List<NameValuePair> pairs) {
        String responseString = null;
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(CONNECT_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECT_TIMEOUT).build();

        StringBuilder sb = new StringBuilder();
        sb.append(url.trim());
        int i = 0;
        if (pairs != null && pairs.size() > 0) {
            for (NameValuePair entry : pairs) {
                if (i == 0 && !url.contains("?")) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(entry.getName());
                sb.append("=");
                String value = entry.getValue();
                try {
                    sb.append(URLEncoder.encode(value, Consts.UTF_8.name()));
                } catch (UnsupportedEncodingException e) {
                    logger.warn("encode http get params error, value is {}",
                            value, e);
                    sb.append(URLEncoder.encode(value));
                }
                i++;
            }
        }

        logger.info("[HttpUtils Get] begin invoke:{}", url.toString());
        HttpGet get = new HttpGet(url.toString());
        get.setConfig(requestConfig);

        try {
            long s1 = System.currentTimeMillis();
            CloseableHttpResponse response = httpclient.execute(get);
            long s2 = System.currentTimeMillis();
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        responseString = EntityUtils.toString(entity,
                                Consts.UTF_8);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } catch (Exception e) {
                logger.error("[HttpUtils Get]get response error, url:{}",
                        url.toString(), e);
                return responseString;
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            logger.info(
                    "[HttpUtils Get]Debug url:{} , response string :{},time={}",
                    url.toString(), responseString, s2 - s1);
        } catch (SocketTimeoutException e) {
            logger.error("[HttpUtils Get]invoke get timout error, url:{}",
                    url.toString(), e);
            return responseString;
        } catch (Exception e) {
            logger.error("[HttpUtils Get]invoke get error, url:{}",
                    url.toString(), e);
        } finally {
            get.releaseConnection();
        }
        return responseString;
    }

    /**
     * HTTPS请求，默认超时为5S
     * 
     * @param reqURL
     * @param params
     * @return
     */
    public static String connectPostHttps(String reqURL,
            Map<String, String> params) {

        String responseContent = null;

        HttpPost httpPost = new HttpPost(reqURL);
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(CONNECT_TIMEOUT)
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(CONNECT_TIMEOUT).build();

            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            httpPost.setEntity(new UrlEncodedFormEntity(formParams,
                    Consts.UTF_8));
            httpPost.setConfig(requestConfig);
            // 绑定到请求 Entry
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                // 执行POST请求
                HttpEntity entity = response.getEntity(); // 获取响应实体
                try {
                    if (null != entity) {
                        responseContent = EntityUtils.toString(entity,
                                Consts.UTF_8);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            logger.info("requestURI : " + httpPost.getURI()
                    + ", responseContent: " + responseContent);
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            httpPost.releaseConnection();
        }
        return responseContent;

    }

}
