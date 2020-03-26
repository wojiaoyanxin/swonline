package com.sunlands.intl.yingshi.greendao.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 文件名: PaperDbBean
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/17
 */
@Entity
public class PaperDbBean {
    @Id
    private String thesisId ;
    private String subjectName ;
    private String title ;
    private String fileUrl ;
    private String editUrl ;
    private Long fileSize;
    private String filePath;

    @Generated(hash = 282979505)
    public PaperDbBean(String thesisId, String subjectName, String title,
            String fileUrl, String editUrl, Long fileSize, String filePath) {
        this.thesisId = thesisId;
        this.subjectName = subjectName;
        this.title = title;
        this.fileUrl = fileUrl;
        this.editUrl = editUrl;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    @Generated(hash = 2041585831)
    public PaperDbBean() {
    }

    public String getThesisId() {
        return thesisId;
    }

    public void setThesisId(String thesisId) {
        this.thesisId = thesisId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getEditUrl() {
        return editUrl;
    }

    public void setEditUrl(String editUrl) {
        this.editUrl = editUrl;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
