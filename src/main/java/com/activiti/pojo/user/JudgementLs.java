package com.activiti.pojo.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 互評流水
 * Created by 12490 on 2017/8/20.
 */
public class JudgementLs implements Serializable {
    private static final long serialVersionUID = 2120869894112984147L;
    private String courseCode;
    private String judgeEmail;  //评分人的Email
    private String nonJudgeEmail;  //被评人的Email
    private Double grade;    //分数
    private Date judgeTime;   //评分时间
    private String judgement;    //评语

    public JudgementLs() {
    }

    public JudgementLs(String courseCode, String nonJudgeEmail) {
        this.courseCode = courseCode;
        this.nonJudgeEmail = nonJudgeEmail;
    }

    public JudgementLs(String courseCode, String judgeEmail, String nonJudgeEmail, Double grade) {
        this.courseCode = courseCode;
        this.judgeEmail = judgeEmail;
        this.nonJudgeEmail = nonJudgeEmail;
        this.grade = grade;
        this.judgeTime=new Date();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getJudgeEmail() {
        return judgeEmail;
    }

    public void setJudgeEmail(String judgeEmail) {
        this.judgeEmail = judgeEmail;
    }

    public String getNonJudgeEmail() {
        return nonJudgeEmail;
    }

    public void setNonJudgeEmail(String nonJudgeEmail) {
        this.nonJudgeEmail = nonJudgeEmail;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Date getJudgeTime() {
        return judgeTime;
    }

    public void setJudgeTime(Date judgeTime) {
        this.judgeTime = judgeTime;
    }

    public String getJudgement() {
        return judgement;
    }

    public void setJudgement(String judgement) {
        this.judgement = judgement;
    }

    @Override
    public String toString() {
        return "JudgementLs{" +
                "courseCode='" + courseCode + '\'' +
                ", judgeEmail='" + judgeEmail + '\'' +
                ", nonJudgeEmail='" + nonJudgeEmail + '\'' +
                ", grade=" + grade +
                ", judgeTime=" + judgeTime +
                ", judgement='" + judgement + '\'' +
                '}';
    }
}
