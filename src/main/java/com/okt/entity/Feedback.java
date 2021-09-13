package com.okt.entity;

import java.util.Date;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */

public class Feedback {
    private String fb_id;
    private String r_username;
    private String fb_context;
    private Date fb_date;
    private String fb_date_str;
    private String fb_status;
    private String fb_processor;
    private String fb_processor_str;
    private String fb_reply;

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
    }

    public String getR_username() {
        return r_username;
    }

    public void setR_username(String r_username) {
        this.r_username = r_username;
    }

    public String getFb_context() {
        return fb_context;
    }

    public void setFb_context(String fb_context) {
        this.fb_context = fb_context;
    }

    public Date getFb_date() {
        return fb_date;
    }

    public void setFb_date(Date fb_date) {
        this.fb_date = fb_date;
    }

    public String getFb_date_str() {
        return fb_date_str;
    }

    public void setFb_date_str(String fb_date_str) {
        this.fb_date_str = fb_date_str;
    }

    public String getFb_status() {
        return fb_status;
    }

    public void setFb_status(String fb_status) {
        this.fb_status = fb_status;
    }

    public String getFb_processor() {
        return fb_processor;
    }

    public void setFb_processor(String fb_processor) {
        this.fb_processor = fb_processor;
    }

    public String getFb_processor_str() {
        return fb_processor_str;
    }

    public void setFb_processor_str(String fb_processor_str) {
        this.fb_processor_str = fb_processor_str;
    }

    public String getFb_reply() {
        return fb_reply;
    }

    public void setFb_reply(String fb_reply) {
        this.fb_reply = fb_reply;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "fb_id='" + fb_id + '\'' +
                ", r_username='" + r_username + '\'' +
                ", fb_context='" + fb_context + '\'' +
                ", fb_date=" + fb_date +
                ", fb_date_str='" + fb_date_str + '\'' +
                ", fb_status='" + fb_status + '\'' +
                ", fb_processor='" + fb_processor + '\'' +
                ", fb_processor_str='" + fb_processor_str + '\'' +
                ", fb_reply='" + fb_reply + '\'' +
                '}';
    }
}
