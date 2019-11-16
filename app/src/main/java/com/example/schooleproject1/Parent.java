package com.example.schooleproject1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Parent {

    @SerializedName("parents_login")
    @Expose
    private String parentsLogin;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("login_type")
    @Expose
    private String loginType;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;

    public String getParentsLogin() {
        return parentsLogin;
    }

    public void setParentsLogin(String parentsLogin) {
        this.parentsLogin = parentsLogin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}