package com.sunlands.intl.yingshi.bean;

/**
 * Created by yanxin on 2019/4/24.
 */

public class PageBean {


    String filePath;
    int page;
    String progress;

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
