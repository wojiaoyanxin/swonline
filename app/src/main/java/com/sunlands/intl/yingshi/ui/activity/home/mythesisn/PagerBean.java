package com.sunlands.intl.yingshi.ui.activity.home.mythesisn;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Generated;

/**
 * 文件名: PagerBean
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/4
 */
public class PagerBean implements Parcelable{
//    thesisId	论文id	string
//subjectName	科目名称	string
//title	论文题目	string
//fileUrl	论文地址	string
//editUrl	论文解析地址	string
    private String thesisId ;
    private String subjectName ;
    private String title ;
    private String fileUrl ;
    private String editUrl ;

    public PagerBean() {
    }

    public PagerBean(Parcel in) {
        thesisId = in.readString();
        subjectName = in.readString();
        title = in.readString();
        fileUrl = in.readString();
        editUrl = in.readString();
    }

    @Generated(hash = 901572649)
    public PagerBean(String thesisId, String subjectName, String title,
            String fileUrl, String editUrl) {
        this.thesisId = thesisId;
        this.subjectName = subjectName;
        this.title = title;
        this.fileUrl = fileUrl;
        this.editUrl = editUrl;
    }

    public static final Creator<PagerBean> CREATOR = new Creator<PagerBean>() {
        @Override
        public PagerBean createFromParcel(Parcel in) {
            return new PagerBean(in);
        }

        @Override
        public PagerBean[] newArray(int size) {
            return new PagerBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thesisId);
        dest.writeString(subjectName);
        dest.writeString(title);
        dest.writeString(fileUrl);
        dest.writeString(editUrl);
    }
}
