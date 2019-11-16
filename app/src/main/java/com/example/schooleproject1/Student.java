package com.example.schooleproject1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Student {

    @SerializedName("sno")
    @Expose
    private Integer sno;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("acesscard_no")
    @Expose
    private String acesscardNo;
    @SerializedName("admno")
    @Expose
    private String admno;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("teacher")
    @Expose
    private String teacher;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAcesscardNo() {
        return acesscardNo;
    }

    public void setAcesscardNo(String acesscardNo) {
        this.acesscardNo = acesscardNo;
    }

    public String getAdmno() {
        return admno;
    }

    public void setAdmno(String admno) {
        this.admno = admno;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}