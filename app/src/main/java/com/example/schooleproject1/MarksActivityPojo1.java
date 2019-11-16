package com.example.schooleproject1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class MarksActivityPojo1 {



    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("std_name")
    @Expose
    private String stdName;
    @SerializedName("std_cls")
    @Expose
    private String stdCls;
    @SerializedName("exam_result")
    @Expose
    private List<ExamResult> examResult = null;

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

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdCls() {
        return stdCls;
    }

    public void setStdCls(String stdCls) {
        this.stdCls = stdCls;
    }

    public List<ExamResult> getExamResult() {
        return examResult;
    }

    public void setExamResult(List<ExamResult> examResult) {
        this.examResult = examResult;
    }
}
