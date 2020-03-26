package com.sunlands.intl.yingshi.greendao.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * 文件名: LoginInfo
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/16
 */
@Entity
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * mUserId : 10
     * username :
     * surname :
     * sex : 0
     * avatar :
     * tel : 150****3418
     * mSessionKey : 5b9b59fd85224
     * mStuId : 0
     * term :
     * entrance : 0
     * export : 0
     * url :
     * isVip : false
     */
    @Id(autoincrement = true)
    private Long id;
    private int userId;
    private String username;
    private String surname;
    private int sex;
    private String avatar;
    private String tel;
    private String sessionKey;
    private int stuId;
    private String term;
    private String fullTel;
    private int entrance;
    private int export;
    private String url;
    private int isVip;
    private String university;
    @Generated(hash = 241359300)
    public LoginInfo(Long id, int userId, String username, String surname, int sex,
            String avatar, String tel, String sessionKey, int stuId, String term,
            String fullTel, int entrance, int export, String url, int isVip,
            String university) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.surname = surname;
        this.sex = sex;
        this.avatar = avatar;
        this.tel = tel;
        this.sessionKey = sessionKey;
        this.stuId = stuId;
        this.term = term;
        this.fullTel = fullTel;
        this.entrance = entrance;
        this.export = export;
        this.url = url;
        this.isVip = isVip;
        this.university = university;
    }

    @Generated(hash = 1911824992)
    public LoginInfo() {
    }


    public String getFullTell() {
        return fullTel;
    }

    public void setFullTell(String fullTell) {
        this.fullTel = fullTell;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long mId) {
        id = mId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int mUserId) {
        userId = mUserId;
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String mUsername) {
        username = mUsername;
    }

    public String getSurname() {
        return surname == null ? "" : surname;
    }

    public void setSurname(String mSurname) {
        surname = mSurname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int mSex) {
        sex = mSex;
    }

    public String getAvatar() {
        return avatar == null ? "" : avatar;
    }

    public void setAvatar(String mAvatar) {
        avatar = mAvatar;
    }

    public String getTel() {
        return tel == null ? "" : tel;
    }

    public void setTel(String mTel) {
        tel = mTel;
    }

    public String getSessionKey() {
        return sessionKey == null ? "" : sessionKey;
    }

    public void setSessionKey(String mSessionKey) {
        sessionKey = mSessionKey;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int mStuId) {
        stuId = mStuId;
    }

    public String getTerm() {
        return term == null ? "" : term;
    }

    public void setTerm(String mTerm) {
        term = mTerm;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int mEntrance) {
        entrance = mEntrance;
    }

    public int getExport() {
        return export;
    }

    public void setExport(int mExport) {
        export = mExport;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String mUrl) {
        url = mUrl;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int mIsVip) {
        isVip = mIsVip;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "mUserId=" + userId +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", avatar='" + avatar + '\'' +
                ", tel='" + tel + '\'' +
                ", mSessionKey='" + sessionKey + '\'' +
                ", mStuId=" + stuId +
                ", term='" + term + '\'' +
                ", entrance=" + entrance +
                ", export=" + export +
                ", url='" + url + '\'' +
                ", isVip=" + isVip +
                '}';
    }

    public String getFullTel() {
        return this.fullTel;
    }

    public void setFullTel(String fullTel) {
        this.fullTel = fullTel;
    }

    public String getUniversity() {
        return this.university==null?"":university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
