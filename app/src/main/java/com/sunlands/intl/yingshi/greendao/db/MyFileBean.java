package com.sunlands.intl.yingshi.greendao.db;

import java.util.List;

public class MyFileBean {

    String subjectName;
    String subjectId;
    List<MyAllFileDbBean> file;

    public MyFileBean(String subjectName, String subjectId, List<MyAllFileDbBean> file) {
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.file = file;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public List<MyAllFileDbBean> getFile() {
        return file;
    }

    public void setFile(List<MyAllFileDbBean> file) {
        this.file = file;
    }
}
