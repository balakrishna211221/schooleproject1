package com.example.schooleproject1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class SubjectMarks {


    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("obtained_marks")
    @Expose
    private Object obtainedMarks;
    @SerializedName("highest_mark")
    @Expose
    private Object highestMark;
    @SerializedName("grade")
    @Expose
    private Object grade;
    @SerializedName("comment")
    @Expose
    private Object comment;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Object getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(Object obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public Object getHighestMark() {
        return highestMark;
    }

    public void setHighestMark(Object highestMark) {
        this.highestMark = highestMark;
    }

    public Object getGrade() {
        return grade;
    }

    public void setGrade(Object grade) {
        this.grade = grade;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }
}
