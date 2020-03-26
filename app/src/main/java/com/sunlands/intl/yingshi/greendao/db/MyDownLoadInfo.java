package com.sunlands.intl.yingshi.greendao.db;

import com.arialyy.aria.core.download.DownloadEntity;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author yxin
 * @date 2020-03-02 - 10:44
 * @des
 */

@Entity
public class MyDownLoadInfo {


    @Id(autoincrement = true)
    private Long id;
    long userId;

    String fileType; // 1  视频  2 音频  3 课件 4 其他

    String schoolName; //学校名字
    int schoolId;
    String productName; //产品包名字
    int produceId; //
    String courseName; //课程名字
    int courseId;//
    String fileName; //文件名字
    int fileId;
 
    int lastProgress;//上次观看进度

    @Convert(columnType = String.class, converter = Temp_DownloadEntity.class)
    DownloadEntity downloadEntity;

    @Generated(hash = 62282110)
    public MyDownLoadInfo(Long id, long userId, String fileType, String schoolName,
            int schoolId, String productName, int produceId, String courseName,
            int courseId, String fileName, int fileId, int lastProgress,
            DownloadEntity downloadEntity) {
        this.id = id;
        this.userId = userId;
        this.fileType = fileType;
        this.schoolName = schoolName;
        this.schoolId = schoolId;
        this.productName = productName;
        this.produceId = produceId;
        this.courseName = courseName;
        this.courseId = courseId;
        this.fileName = fileName;
        this.fileId = fileId;
        this.lastProgress = lastProgress;
        this.downloadEntity = downloadEntity;
    }

    @Generated(hash = 1855815741)
    public MyDownLoadInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProduceId() {
        return this.produceId;
    }

    public void setProduceId(int produceId) {
        this.produceId = produceId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileId() {
        return this.fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getLastProgress() {
        return this.lastProgress;
    }

    public void setLastProgress(int lastProgress) {
        this.lastProgress = lastProgress;
    }

    public DownloadEntity getDownloadEntity() {
        return this.downloadEntity;
    }

    public void setDownloadEntity(DownloadEntity downloadEntity) {
        this.downloadEntity = downloadEntity;
    }


}
