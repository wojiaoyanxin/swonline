package com.sunlands.intl.yingshi.ui.activity.home.apply;

/**
 * 文件名: ApplyParam
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/18
 */
public class ApplyParam {
    public enum DegreeType {
        BBA,
        MBA,
        MED
    }


    String stuId;
    String type;
    String universityId;
    String name;
    String tel;
    String place;
    String educational;
    String industry;
    String positionType;
    /*{"universityId":"8","name":"布里斯托大学","nameEn":"Bristol University","type":"MBA"}*/

    public String getStuId() {
        return stuId;
    }

    public ApplyParam setStuId(String stuId) {
        this.stuId = stuId;
        return this;
    }

    public String getType() {
        return type;
    }

    public ApplyParam setType(String type) {
        this.type = type;
        return this;
    }

    public String getUniversityId() {
        return universityId;
    }

    public ApplyParam setUniversityId(String universityId) {
        this.universityId = universityId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ApplyParam setName(String name) {
        this.name = name;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public ApplyParam setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public ApplyParam setPlace(String place) {
        this.place = place;
        return this;
    }

    public String getEducational() {
        return educational;
    }

    public ApplyParam setEducational(String educational) {
        this.educational = educational;
        return this;
    }

    public String getIndustry() {
        return industry;
    }

    public ApplyParam setIndustry(String industry) {
        this.industry = industry;
        return this;
    }

    public String getPositionType() {
        return positionType;
    }

    public ApplyParam setPositionType(String positionType) {
        this.positionType = positionType;
        return this;
    }
}
