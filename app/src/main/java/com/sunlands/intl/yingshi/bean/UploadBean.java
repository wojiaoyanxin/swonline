package com.sunlands.intl.yingshi.bean;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/4/10 11:32
 * 备注：
 */
public class UploadBean {

    public String getFileId() {
        return fileId == null ? "" : fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileUrl() {
        return fileUrl == null ? "" : fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * fileId : 69901037874227.png
     * fileUrl : bj-test-1254383113.file.myqcloud.com/69901037874227.png
     */

    public String fileId;
    public String fileUrl;
}
