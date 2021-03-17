package com.njusc.npm.utils.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;

/**
 * 网络工具类，基于apache httpclient
 *
 * @author Michael
 */
public class HttpUtils {

    private static final Logger log = Logger.getLogger(HttpUtils.class);

    private HttpUtils() {
    }

    public static String httpGet(final String uri) {
        return BaseHttp.httpGet(uri);
    }

    public static String httpPost(final String uri,
                                  final HttpEntity entity) {
        return BaseHttp.httpPost(uri, entity);
    }

    public static String httpsGet(final String uri) {
        return BaseHttp.httpsGet(uri);
    }

    public static String httpsPost(final String uri,
                                   final HttpEntity entity) {
        return BaseHttp.httpsPost(uri, entity);
    }

    private static class BaseHttp {

        /**
         * http get请求
         *
         * @param uri 请求资源地址
         * @return String
         */
        protected static String httpGet(final String uri) {
            return get(uri, HttpClients.createDefault());
        }

        /**
         * http post请求
         *
         * @param uri 请求资源地址
         * @return String
         */
        protected static String httpPost(final String uri,
                                         final HttpEntity entity) {
            return post(uri, HttpClients.createDefault(), entity);
        }

        /**
         * https get请求
         *
         * @param url 请求资源地址
         * @return String
         */
        protected static String httpsGet(final String url) {
            return get(url, createSSLClientDefault());
        }

        /**
         * https post请求
         *
         * @param url 请求资源地址
         * @return String
         */
        protected static String httpsPost(final String url,
                                          final HttpEntity entity) {
            return post(url, createSSLClientDefault(), entity);
        }

        /**
         * 创建https(ssl) httpclient
         *
         * @return CloseableHttpClient
         */
        private static CloseableHttpClient createSSLClientDefault() {
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                    /** 信任所有 */
                    @Override
                    public boolean isTrusted(X509Certificate[] chain,
                                             String authType) {
                        return true;
                    }
                }).build();

                SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(sslContext);
                return HttpClients.custom().setSSLSocketFactory(scsf).build();
            } catch (Exception e) {
                log.info(e.getMessage(), e);
            }
            return HttpClients.createDefault();
        }

        private static String get(final String url,
                                  final CloseableHttpClient httpClient) {
            try {
                if (Util.n(httpClient)) {
                    log.info("httpclient is null ");
                    return null;
                }

                log.info("request url = " + url);

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                HttpGet get = new HttpGet(url);
                return httpClient.execute(get, responseHandler);
            } catch (Exception e) {
                log.info(e.getMessage(), e);
            } finally {
                try {
                    httpClient.close();
                } catch (Exception e) {
                    log.info(e.getMessage(), e);
                }
            }
            return null;
        }

        private static String post(final String url,
                                   final CloseableHttpClient httpClient,
                                   final HttpEntity entity) {
            try {
                if (Util.n(httpClient)) {
                    log.info("httpclient is null ");
                    return null;
                }

                log.info("request url = " + url);

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                HttpPost post = new HttpPost(url);
                post.setEntity(entity);
                return httpClient.execute(post, responseHandler);
            } catch (Exception e) {
                log.info(e.getMessage(), e);
            } finally {
                try {
                    httpClient.close();
                } catch (Exception e) {
                    log.info(e.getMessage(), e);
                }
            }
            return null;
        }

    }
}
