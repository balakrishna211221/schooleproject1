package com.example.schooleproject1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class HomeWorkPojo2 {


    @SerializedName("sno")
    @Expose
    private Integer sno;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("students")
    @Expose
    private String students;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("work")
    @Expose
    private String work;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
