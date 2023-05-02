package com.nus.iss.etscommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class InternetTransferDTO implements Serializable {

    private static final long serialVersionId = -1;

    @JsonProperty("base64String")
    private String base64String;

    @JsonProperty("hashCode")
    private int hashCode;

    public InternetTransferDTO(String base64String, int hashCode) {
        this.base64String = base64String;
        this.hashCode = hashCode;
    }

    public String getBase64String() {
        return base64String;
    }

    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    @Override
    public String toString() {
        return "InternetTransferDTO{" +
            "base64String='" + base64String + '\'' +
            ", hashCode=" + hashCode +
            '}';
    }
}
