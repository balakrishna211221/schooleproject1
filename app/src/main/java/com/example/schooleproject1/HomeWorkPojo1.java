package com.example.schooleproject1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class HomeWorkPojo1 {



    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("home_works")
    @Expose
    private List<HomeWorkPojo2> homeWorks = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<HomeWorkPojo2> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWorkPojo2> homeWorks) {
        this.homeWorks = homeWorks;
    }

}
