package com.nus.iss.etscommon.enumeration;

public enum HOST_TYPE {

    BOOKING("BOOKING", "BOOKING"),
    ENQUIRY("ENQUIRY", "ENQUIRY"),
    EVENT("EVENT", "EVENT"),
    PAYMENT("PAYMENT", "PAYMENT"),
    NOTIFICATION("NOTIFICATION", "NOTIFICATION");

    private String code;

    private String desc;

    HOST_TYPE(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
