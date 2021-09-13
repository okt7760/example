package com.okt.entity;

/**
 * Create by obeeey on 2021/2/1
 * 即使再小的帆也能远航
 */
//职工
public class Empolyee {
    private String f_username;
    private String f_id;
    private String f_name;
    private String f_password;
    private String f_phone;
    private int f_role;
    private int f_gender;

    public String getF_username() {
        return f_username;
    }

    public void setF_username(String f_username) {
        this.f_username = f_username;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_password() {
        return f_password;
    }

    public void setF_password(String f_password) {
        this.f_password = f_password;
    }

    public String getF_phone() {
        return f_phone;
    }

    public void setF_phone(String f_phone) {
        this.f_phone = f_phone;
    }

    public int getF_role() {
        return f_role;
    }

    public void setF_role(int f_role) {
        this.f_role = f_role;
    }

    public int getF_gender() {
        return f_gender;
    }

    public void setF_gender(int f_gender) {
        this.f_gender = f_gender;
    }

    @Override
    public String toString() {
        return "Empolyee{" +
                "f_username='" + f_username + '\'' +
                ", f_id='" + f_id + '\'' +
                ", f_name='" + f_name + '\'' +
                ", f_password='" + f_password + '\'' +
                ", f_phone='" + f_phone + '\'' +
                ", f_role='" + f_role + '\'' +
                ", f_gender='" + f_gender + '\'' +
                '}';
    }
}
