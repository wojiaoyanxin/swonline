package com.sunlands.intl.yingshi.bean;

/**
 * @author yxin
 * @date 2020-02-24 - 11:43
 * @des
 */
public class DownLoadInfo {


    /**
     * schoolId : 1
     * schoolName : 清华大学
     * produceId : 1001
     * productName : 测试包
     * courseId : 46382
     * courseName : 会计实务
     * fileName : 视频46382
     * fileId : 121
     * url : http://46382
     * type : 视频
     */

    private int schoolId;
    private String schoolName;
    private int produceId;
    private String productName;
    private int courseId;
    private String courseName;
    private String fileName;
    private int fileId;
    private String url;
    private String type;

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getProduceId() {
        return produceId;
    }

    public void setProduceId(int produceId) {
        this.produceId = produceId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
