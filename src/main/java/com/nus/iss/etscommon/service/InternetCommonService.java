package com.nus.iss.etscommon.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nus.iss.etscommon.config.ApiGatewayProperties;
import com.nus.iss.etscommon.config.ApiProperties;
import com.nus.iss.etscommon.dto.HttpClientResponse;
import com.nus.iss.etscommon.dto.InternetTransferDTO;
import com.nus.iss.etscommon.enumeration.HOST_TYPE;
import com.nus.iss.etscommon.util.HttpClientUtils;
import com.nus.iss.etscommon.util.JsonUtils;
import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternetCommonService {

    private final Logger log = LoggerFactory.getLogger(InternetCommonService.class);

    private final ApiGatewayProperties apiGatewayProperties;

    public InternetCommonService(ApiGatewayProperties apiGatewayProperties) {
        this.apiGatewayProperties = apiGatewayProperties;
    }

    public <T> ResponseEntity<T> post(Class<T> clazz, String apiName, HttpHeaders headers, Object requestParams, HOST_TYPE hostType) throws JsonProcessingException {

        InternetTransferDTO internetTransferDTO = null;
        if (null != requestParams) {
            internetTransferDTO = this.encodeRequestParams(requestParams);
        }

        String finalUrl = getUrlByName(apiName, hostType);
        headers = this.addHeaders(headers);
        return execute(finalUrl, headers, internetTransferDTO, clazz);
    }

    private <T> ResponseEntity<T> execute(String finalUrl, HttpHeaders headers, InternetTransferDTO internetTransferDTO, Class<T> clazz) throws JsonProcessingException {
        String jsonParams = "";
        if (null != internetTransferDTO) {
            jsonParams = JsonUtils.objectToString(internetTransferDTO);
        }

        HttpClientResponse response = HttpClientUtils.doPostJson(finalUrl, headers, jsonParams);
        Header[] allHeaders = response.getResponse().getAllHeaders();
        HttpHeaders httpHeaders = new HttpHeaders();
        for (Header header : allHeaders) {
            httpHeaders.add(header.getName(), header.getValue());
        }

        if (HttpStatus.OK.equals(response.getStatus()) || HttpStatus.CREATED.equals(response.getStatus())) {
            if (null == response.getJsonData() || StringUtils.isEmpty(response.getJsonData())) {
                return ResponseEntity.ok().header(String.valueOf(httpHeaders)).body(null);
            } else {
                T o = JsonUtils.stringToObject(response.getJsonData(),clazz);
                return ResponseEntity.ok().headers(httpHeaders).body(o);
            }
        } else if (HttpStatus.NO_CONTENT.equals(response.getStatus())) {
            return ResponseEntity.ok().headers(httpHeaders).body(null);
        }
        return ResponseEntity.ok().headers(httpHeaders).body(null);
    }

    private String getUrlByName(String apiName, HOST_TYPE hostType) {
        List<String> apiList = apiGatewayProperties.getApiList().stream()
            .filter(api -> api.getApiName().equals(apiName))
            .map(ApiProperties::getApiUrl).collect(Collectors.toList());

        if (apiList.isEmpty()) {
            log.info("API [{}] not found in configuration list", apiName);
        }

        if (apiList.size() > 1) {
            log.info("Duplicate API [{}] found in the configuration list", apiName);
        }

        String host = "";
        switch(hostType) {
            case EVENT:
                host = apiGatewayProperties.getEventHost();
                break;
            case PAYMENT:
                host = apiGatewayProperties.getPaymentHost();
                break;
            case BOOKING:
                host = apiGatewayProperties.getBookingHost();
                break;
        }
        return host + "/" + apiList.get(0);
    }

    private InternetTransferDTO encodeRequestParams(Object requestParams) {
        String jsonParam = JsonUtils.objectToString(requestParams);
        String encodedBase64 = Base64.getEncoder().encodeToString(jsonParam.getBytes());
        return new InternetTransferDTO(encodedBase64, jsonParam.hashCode());
    }

    public <T> T decodeRequestParams(InternetTransferDTO internetTransferDTO, Class<T> clazz) {
        String params = internetTransferDTO.getBase64String();
        String jsonStr = new String(Base64.getDecoder().decode(params));
        return JsonUtils.stringToObject(jsonStr, clazz);
    }

    private HttpHeaders addHeaders(HttpHeaders headers) {
        headers.remove("Content-Length");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


}
