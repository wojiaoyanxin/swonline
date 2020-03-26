package com.sunlands.intl.yingshi.bean.multi;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sunlands.intl.yingshi.bean.EmptyBean;

public class HandoutItem extends AbstractExpandableItem<EmptyBean>  implements MultiItemEntity {

    public String title;
    public String timestamp;
    public String path;
    public String fileName;
    public String subjectId;
    public boolean select;
    public String courseId;
    public String count;
    private String progress;
    private String fileUrl;
    private int sid;


    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public HandoutItem(String title, String timestamp, String path, String fileName) {
        this.title = title;
        this.timestamp = timestamp;
        this.path = path;
        this.fileName = fileName;
    }

    public HandoutItem() {
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int getItemType() {
        return 1;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProgress() {
        return progress;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "HandoutItem{" +
                "title='" + title + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", select=" + select +
                ", courseId='" + courseId + '\'' +
                ", count='" + count + '\'' +
                ", progress='" + progress + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", sid=" + sid +
                '}';
    }
}