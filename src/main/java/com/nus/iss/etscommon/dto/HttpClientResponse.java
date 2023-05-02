package com.nus.iss.etscommon.dto;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class HttpClientResponse implements Serializable {

    private HttpStatus status;

    private String timestamp;

    private String jsonData;

    private CloseableHttpResponse response;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public CloseableHttpResponse getResponse() {
        return response;
    }

    public void setResponse(CloseableHttpResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "HttpClientResponse{" +
            "status=" + status +
            ", timestamp='" + timestamp + '\'' +
            ", jsonData='" + jsonData + '\'' +
            ", response=" + response +
            '}';
    }
}
