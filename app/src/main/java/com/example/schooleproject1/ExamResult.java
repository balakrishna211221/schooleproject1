package com.example.schooleproject1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class ExamResult {

    @SerializedName("sno")
    @Expose
    private Integer sno;
    @SerializedName("exam_name")
    @Expose
    private String examName;
    @SerializedName("subjects")
    @Expose
    private List<SubjectMarks> subjects = null;
    @SerializedName("totals")
    @Expose
    private Totals totals;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public List<SubjectMarks> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectMarks> subjects) {
        this.subjects = subjects;
    }

    public Totals getTotals() {
        return totals;
    }

    public void setTotals(Totals totals) {
        this.totals = totals;
    }
}
