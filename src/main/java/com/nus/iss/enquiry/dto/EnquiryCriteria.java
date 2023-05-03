package com.nus.iss.enquiry.dto;

import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * @author yanbintan
 */
public class EnquiryCriteria {
    private StringFilter title;
    private BooleanFilter readEmail;

    public EnquiryCriteria() {

    }

    public EnquiryCriteria(StringFilter title, BooleanFilter readEmail) {
        this.title = title;
        this.readEmail = readEmail;
    }

    public EnquiryCriteria(EnquiryCriteria other) {

    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public BooleanFilter getReadEmail() {
        return readEmail;
    }

    public void setReadEmail(BooleanFilter readEmail) {
        this.readEmail = readEmail;
    }

    @Override
    public String toString() {
        return "EnquiryCriteria{" +
                "title=" + title +
                ", readEmail=" + readEmail +
                '}';
    }
}
