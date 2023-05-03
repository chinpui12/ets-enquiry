package com.nus.iss.enquiry.domain;

import javax.persistence.*;

/**
 * @author yanbintan
 */
@Entity
@Table(name = "enquiry")
public class Enquiry extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "sender_email")
    private String senderEmail;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "message")
    private String message;

    @Column(name = "sender_contact")
    private String senderContact;

    @Column(name = "is_read")
    private Boolean readEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderContact() {
        return senderContact;
    }

    public void setSenderContact(String senderContact) {
        this.senderContact = senderContact;
    }

    public Boolean getReadEmail() {
        return readEmail;
    }

    public void setReadEmail(Boolean readEmail) {
        this.readEmail = readEmail;
    }
}
