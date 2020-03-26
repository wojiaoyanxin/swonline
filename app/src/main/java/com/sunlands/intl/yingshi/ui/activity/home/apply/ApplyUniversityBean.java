package com.sunlands.intl.yingshi.ui.activity.home.apply;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文件名: ApplyUniversityBean
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/8
 */
public class ApplyUniversityBean implements Parcelable{

    /**
     * universityId : 8
     * name : 布里斯托大学
     * nameEn : Bristol University
     * type : MBA
     */

    private String universityId;
    private String name;
    private String nameEn;
    private String type;

    public ApplyUniversityBean(Parcel in) {
        universityId = in.readString();
        name = in.readString();
        nameEn = in.readString();
        type = in.readString();
    }

    public ApplyUniversityBean(String universityId, String name, String nameEn, String type) {
        this.universityId = universityId;
        this.name = name;
        this.nameEn = nameEn;
        this.type = type;
    }

    public static final Creator<ApplyUniversityBean> CREATOR = new Creator<ApplyUniversityBean>() {
        @Override
        public ApplyUniversityBean createFromParcel(Parcel in) {
            return new ApplyUniversityBean(in);
        }

        @Override
        public ApplyUniversityBean[] newArray(int size) {
            return new ApplyUniversityBean[size];
        }
    };

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(universityId);
        dest.writeString(name);
        dest.writeString(nameEn);
        dest.writeString(type);
    }
}
