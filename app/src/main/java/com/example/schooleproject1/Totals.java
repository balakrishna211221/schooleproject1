package com.example.schooleproject1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Totals {

    @SerializedName("total_marks")
    @Expose
    private float totalMarks;
    @SerializedName("gpa")
    @Expose
    private float gpa;
    @SerializedName("percentage")
    @Expose
    private Integer percentage;

    public float  getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(float  totalMarks) {
        this.totalMarks = totalMarks;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
