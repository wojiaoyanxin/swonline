package com.sunlands.intl.yingshi.greendao.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 文件名: HandoutDbBean
 * 描    述: [讲义数据模型]
 * 创建人: duzzi
 * 创建时间: 2018/10/9
 */
@Entity
public class HandoutDbBean {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private Long subjectDbBeanId;
    @NotNull
    private String subjectId;
    private String courseId;
    private String courseName;
    private String date;
    private String filePath;
    private String fileName;
    private String fileUrl;
    private int sid;


    private Long userId;

    @Generated(hash = 455092959)
    public HandoutDbBean(Long id, @NotNull Long subjectDbBeanId, @NotNull String subjectId,
            String courseId, String courseName, String date, String filePath, String fileName,
            String fileUrl, int sid, Long userId) {
        this.id = id;
        this.subjectDbBeanId = subjectDbBeanId;
        this.subjectId = subjectId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.date = date;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.sid = sid;
        this.userId = userId;
    }

    @Generated(hash = 1436268678)
    public HandoutDbBean() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "HandoutDbBean{" +
                "id=" + id +
                ", subjectDbBeanId=" + subjectDbBeanId +
                ", subjectId='" + subjectId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", date='" + date + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    public Long getSubjectDbBeanId() {
        return subjectDbBeanId;
    }

    public void setSubjectDbBeanId(Long subjectDbBeanId) {
        this.subjectDbBeanId = subjectDbBeanId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSid() {
        return this.sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
