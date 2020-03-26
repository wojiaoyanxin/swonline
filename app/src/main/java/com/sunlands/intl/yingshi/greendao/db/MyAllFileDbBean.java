package com.sunlands.intl.yingshi.greendao.db;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MyAllFileDbBean {

    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String subjectId;
    private String subjectName;
    private String courseId;
    private String courseName;
    private String date;
    private String filePath;
    private String fileName;
    private String fileUrl;
    private int sid;
    private String userId;
    @Generated(hash = 639688557)
    public MyAllFileDbBean(Long id, @NotNull String subjectId, String subjectName,
            String courseId, String courseName, String date, String filePath,
            String fileName, String fileUrl, int sid, String userId) {
        this.id = id;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.date = date;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.sid = sid;
        this.userId = userId;
    }
    @Generated(hash = 1580922213)
    public MyAllFileDbBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSubjectId() {
        return this.subjectId;
    }
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
    public String getSubjectName() {
        return this.subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public String getCourseId() {
        return this.courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return this.courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getFilePath() {
        return this.filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileUrl() {
        return this.fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public int getSid() {
        return this.sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
