package com.sunlands.intl.yingshi.ui.my.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.contrarywind.interfaces.IPickerViewData;
import com.google.gson.annotations.SerializedName;
import com.sunlands.intl.yingshi.bean.IPickerViewCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名: UserInfo
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/3
 */
public class UserInfo implements Parcelable {

    /**
     * name : 李明
     * tel : 15011282874
     * isShow : 0
     * headUrl : https://education-1254383113.file.myqcloud.com/66013555764376.png
     * nickname : 150****287
     * universityMajor : 北京大学-经济管理
     * term : 2018-09-01 ~ 2019-10-01
     * industry :
     * position :
     * university : 北京大学
     * city :
     * workingYear :
     * signature :
     * points : 16
     * totalNum : 135
     * absenceNum : 125
     * studyNum : 8.65
     * version : 1.0.0
     * viewList : [{"courseId":"6368","viewTime":"1554775267","type":"2","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","title":"录播课2"},{"courseId":"6376","viewTime":"1554720977","type":"2","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","title":"4.8直播课"},{"courseId":"6365","viewTime":"1554197527","type":"2","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","title":"直播课"}]
     */

    private String name;
    private String tel;
    private int isShow;
    private String headUrl;
    private String nickname;
    private String universityMajor;
    private String term;
    private String industry;
    private String position;//职位

    private String university;
    private String sex;
    private String company;
    private String city;
    private String workingYear;
    private String signature;
    private int points;
    private String totalNum;
    private int absenceNum;
    private String studyNum;
    private String version;
    private String className;
    private int edit;

    public String getCurrentUniversity() {
        return className;
    }

    public void setCurrentUniversity(String className) {
        this.className = className;
    }

    public int getEdit() {
        return edit;
    }

    public void setEdit(int edit) {
        this.edit = edit;
    }

    private List<ViewListBean> viewList;
    private List<IndustryListBean> industryList;


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUniversityMajor() {
        return universityMajor;
    }

    public void setUniversityMajor(String universityMajor) {
        this.universityMajor = universityMajor;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWorkingYear() {
        return workingYear;
    }

    public void setWorkingYear(String workingYear) {
        this.workingYear = workingYear;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public int getAbsenceNum() {
        return absenceNum;
    }

    public void setAbsenceNum(int absenceNum) {
        this.absenceNum = absenceNum;
    }

    public String getStudyNum() {
        return studyNum;
    }

    public void setStudyNum(String studyNum) {
        this.studyNum = studyNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ViewListBean> getViewList() {
        return viewList;
    }

    public void setViewList(List<ViewListBean> viewList) {
        this.viewList = viewList;
    }

    public List<IndustryListBean> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<IndustryListBean> industryList) {
        this.industryList = industryList;
    }

    public static class ViewListBean {
        /**
         * courseId : 6368
         * viewTime : 1554775267
         * type : 2
         * pic : https://education-1254383113.file.myqcloud.com/124322162994881.jpg
         * title : 录播课2
         */

        private String courseId;
        private String viewTime;
        private String type;
        private String pic;
        private String title;

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getViewTime() {
            return viewTime;
        }

        public void setViewTime(String viewTime) {
            this.viewTime = viewTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.tel);
        dest.writeInt(this.isShow);
        dest.writeString(this.headUrl);
        dest.writeString(this.nickname);
        dest.writeString(this.universityMajor);
        dest.writeString(this.term);
        dest.writeString(this.industry);
        dest.writeString(this.position);
        dest.writeString(this.university);
        dest.writeString(this.city);
        dest.writeString(this.workingYear);
        dest.writeString(this.signature);
        dest.writeInt(this.points);
        dest.writeString(this.sex);
        dest.writeString(this.company);
        dest.writeInt(this.absenceNum);
        dest.writeString(this.studyNum);
        dest.writeString(this.version);
        dest.writeList(this.viewList);
        dest.writeList(this.industryList);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.name = in.readString();
        this.tel = in.readString();
        this.isShow = in.readInt();
        this.headUrl = in.readString();
        this.nickname = in.readString();
        this.sex = in.readString();
        this.company = in.readString();
        this.universityMajor = in.readString();
        this.term = in.readString();
        this.industry = in.readString();
        this.position = in.readString();
        this.university = in.readString();
        this.city = in.readString();
        this.workingYear = in.readString();
        this.signature = in.readString();
        this.points = in.readInt();
        this.totalNum = in.readString();
        this.absenceNum = in.readInt();
        this.studyNum = in.readString();
        this.version = in.readString();
        this.viewList = new ArrayList<ViewListBean>();
        this.industryList = new ArrayList<IndustryListBean>();
        in.readList(this.viewList, ViewListBean.class.getClassLoader());
        in.readList(this.industryList, IndustryListBean.class.getClassLoader());
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public static class IndustryListBean implements IPickerViewData,IPickerViewCode {
        /**
         * name : IT/互联网
         * data : ["IT系统集成","工具软件","大数据","电子商务","游戏","社交网络","新媒体","O2O","知识付费","信息安全"]
         */

        @SerializedName("name")
        private String nameX;
        private List<String> data;

        public String getNameX() {
            return nameX;
        }

        public void setNameX(String nameX) {
            this.nameX = nameX;
        }

        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }

        @Override
        public String getPickerViewText() {
            return nameX;
        }

        @Override
        public String getPickerViewCode() {
            return null;
        }
    }
}
