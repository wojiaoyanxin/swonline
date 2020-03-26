package com.sunlands.intl.yingshi.bean;


/**
 * 文件名: TempCourseBean
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/10
 */
public class TempCourseBean {

    private String subjectId;
    private String subjectName;
    private String courseId;
    private String courseName;
    private String handout;
    private String date;
    private String handout_name;
    private int sid;

    public TempCourseBean(String subjectId, String subjectName,
                          String courseId, String courseName,
                          String handout, String date, String handout_name,int sid) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.handout = handout;
        this.date = date;
        this.handout_name = handout_name;
        this.sid = sid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getHandout_name() {
        return handout_name;
    }

    public void setHandout_name(String handout_name) {
        this.handout_name = handout_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHandout() {
        return handout;
    }

    public void setHandout(String handout) {
        this.handout = handout;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
