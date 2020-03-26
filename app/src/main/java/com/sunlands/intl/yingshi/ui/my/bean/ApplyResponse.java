package com.sunlands.intl.yingshi.ui.my.bean;

import java.util.List;

/**
 * 文件名: ApplyResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/14
 */
public class ApplyResponse {

    private List<ApplyBean> applications;

    public List<ApplyBean> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplyBean> applications) {
        this.applications = applications;
    }

    public static class ApplyBean {
        /**
         * university : 澳大利亚管理学院
         * major : 工商管理硕士
         * batch : 第一批次
         * reason :
         * checkStep : 8
         * infomationStep : 1,2,3,4,5
         * applicationStep : 5
         * bgUrl : https://education-1254383113.file.myqcloud.com/15974500122494.png
         */

        private String university;
        private String major;
        private String batch;
        private String reason;
        private String checkStep;
        private String infomationStep;
        private int applicationStep;
        private String bgUrl;
        private int hasExpired;

        public int getHasExpired() {
            return hasExpired;
        }

        public void setHasExpired(int hasExpired) {
            this.hasExpired = hasExpired;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getCheckStep() {
            return checkStep;
        }

        public void setCheckStep(String checkStep) {
            this.checkStep = checkStep;
        }

        public String getInfomationStep() {
            return infomationStep;
        }

        public void setInfomationStep(String infomationStep) {
            this.infomationStep = infomationStep;
        }

        public int getApplicationStep() {
            return applicationStep;
        }

        public void setApplicationStep(int applicationStep) {
            this.applicationStep = applicationStep;
        }

        public String getBgUrl() {
            return bgUrl;
        }

        public void setBgUrl(String bgUrl) {
            this.bgUrl = bgUrl;
        }
    }
}
