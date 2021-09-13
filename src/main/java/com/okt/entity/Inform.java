package com.okt.entity;

import java.util.Date;

/**
 * Create by obeeey on 2021/3/21
 * 即使再小的帆也能远航
 */
public class Inform {
    private String inf_id;
    private String inf_title;
    private String inf_context;
    private String inf_writer;
    private String inf_writer_str;
    private int inf_role;
    private Date desc_date;
    private String desc_date_str;

    public String getDesc_date_str() {
        return desc_date_str;
    }

    public void setDesc_date_str(String desc_date_str) {
        this.desc_date_str = desc_date_str;
    }

    public String getInf_writer_str() {
        return inf_writer_str;
    }

    public void setInf_writer_str(String inf_writer_str) {
        this.inf_writer_str = inf_writer_str;
    }

    public Date getDesc_date() {
        return desc_date;
    }

    public void setDesc_date(Date desc_date) {
        this.desc_date = desc_date;
    }

    public String getInf_id() {
        return inf_id;
    }

    public void setInf_id(String inf_id) {
        this.inf_id = inf_id;
    }

    public String getInf_title() {
        return inf_title;
    }

    public void setInf_title(String inf_title) {
        this.inf_title = inf_title;
    }

    public String getInf_context() {
        return inf_context;
    }

    public void setInf_context(String inf_context) {
        this.inf_context = inf_context;
    }


    public String getInf_writer() {
        return inf_writer;
    }

    public void setInf_writer(String inf_writer) {
        this.inf_writer = inf_writer;
    }

    public int getInf_role() {
        return inf_role;
    }

    public void setInf_role(int inf_role) {
        this.inf_role = inf_role;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "inf_id='" + inf_id + '\'' +
                ", inf_title='" + inf_title + '\'' +
                ", inf_context='" + inf_context + '\'' +
                ", inf_writer='" + inf_writer + '\'' +
                ", inf_writer_str='" + inf_writer_str + '\'' +
                ", inf_role=" + inf_role +
                ", desc_date=" + desc_date +
                ", desc_date_str='" + desc_date_str + '\'' +
                '}';
    }
}
