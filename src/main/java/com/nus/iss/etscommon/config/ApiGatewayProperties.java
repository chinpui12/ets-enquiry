package com.nus.iss.etscommon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "api-gateway")
public class ApiGatewayProperties {

    private String host;

    private String eventHost;

    private String paymentHost;

    private String bookingHost;

    private String enquiryHost;

    private List<ApiProperties> apiList;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEventHost() {
        return eventHost;
    }

    public void setEventHost(String eventHost) {
        this.eventHost = eventHost;
    }

    public String getPaymentHost() {
        return paymentHost;
    }

    public String getBookingHost() {
        return bookingHost;
    }

    public void setBookingHost(String bookingHost) {
        this.bookingHost = bookingHost;
    }

    public void setPaymentHost(String paymentHost) {
        this.paymentHost = paymentHost;
    }

    public String getEnquiryHost() {
        return enquiryHost;
    }

    public void setEnquiryHost(String enquiryHost) {
        this.enquiryHost = enquiryHost;
    }

    public List<ApiProperties> getApiList() {
        return apiList;
    }

    public void setApiList(List<ApiProperties> apiList) {
        this.apiList = apiList;
    }
}
