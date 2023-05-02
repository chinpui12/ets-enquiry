package com.nus.iss.etscommon.util;

import com.nus.iss.etscommon.dto.HttpClientResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    private final static Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    private static HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
        @Override
        public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
            return false;
        }
    };

    public static HttpClientResponse doPostJson(String url, HttpHeaders headers, String jsonStr) {

        HttpClientResponse httpClientResponse = new HttpClientResponse();

        String result = "";
        try(CloseableHttpClient httpClient = HttpClients.custom().setRetryHandler(retryHandler).build();) {
            HttpPost httpPost = new HttpPost(url);
            if (null != httpPost && null != headers && headers.size() > 0) {
                for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue().toString().replace("[","").replace("]",""));
                }
                httpPost.setHeader("access-control-allow-origin", "*");
            }

            if (!StringUtils.isEmpty(jsonStr)) {
                StringEntity entity = new StringEntity(jsonStr);
                entity.setContentType("application/json;charset=utf8");
                httpPost.setEntity(entity);
            }

            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);

            httpClientResponse.setStatus(HttpStatus.valueOf(closeableHttpResponse.getStatusLine().getStatusCode()));

            if (closeableHttpResponse.getEntity().getContentLength() != 0) {
                result = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            }
            httpClientResponse.setJsonData(result);
            httpClientResponse.setResponse(closeableHttpResponse);
            httpClientResponse.setTimestamp(System.currentTimeMillis() + "");
            return httpClientResponse;
        } catch (Exception exception) {
            log.info("Error doPostJson " + exception);
        }
        return httpClientResponse;
    }
}
