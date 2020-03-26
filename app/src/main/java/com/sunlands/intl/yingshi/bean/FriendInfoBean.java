package com.sunlands.intl.yingshi.bean;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.rvadapter.IHeaderHelper;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.community.view.MyFriendActivityActivity;
import com.sunlands.intl.yingshi.util.GlideUtils;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/2/25 11:58
 * 备注：
 */
public class FriendInfoBean implements IHeaderHelper {

    /**
     * is_show : 1
     * name : CC
     * sex : 1
     * avatar : https://education-1254383113.file.myqcloud.com/62191320560835.png
     * university : 测试llj
     * major : ZOOMceshi1
     * batch : 第二批次
     * company : 1
     * birth : 1993-08-01
     * finish_university : 1
     * mail : zhangjianfang163.com
     * tel : 18601399020
     * industry : 服务行业（物流、信息、教育、培训、外包、交通运输等）
     * position : 1
     */

    public int is_show;
    public String name;
    public int sex;
    public String avatar;

    public int getIs_show() {
        return is_show;
    }

    public void setIs_show(int mIs_show) {
        is_show = mIs_show;
    }

    @Override
    public String toString() {
        return "FriendInfoBean{" +
                "is_show=" + is_show +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", avatar='" + avatar + '\'' +
                ", university='" + university + '\'' +
                ", major='" + major + '\'' +
                ", batch='" + batch + '\'' +
                ", company='" + company + '\'' +
                ", birth='" + birth + '\'' +
                ", finish_university='" + finish_university + '\'' +
                ", mail='" + mail + '\'' +
                ", tel='" + tel + '\'' +
                ", industry='" + industry + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String mName) {
        name = mName;
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

    public String getUniversity() {
        return university == null ? "" : university;
    }

    public void setUniversity(String mUniversity) {
        university = mUniversity;
    }

    public String getMajor() {
        return major == null ? "" : major;
    }

    public void setMajor(String mMajor) {
        major = mMajor;
    }

    public String getBatch() {
        return batch == null ? "" : batch;
    }

    public void setBatch(String mBatch) {
        batch = mBatch;
    }

    public String getCompany() {
        return company == null ? "" : company;
    }

    public void setCompany(String mCompany) {
        company = mCompany;
    }

    public String getBirth() {
        return birth == null ? "" : birth;
    }

    public void setBirth(String mBirth) {
        birth = mBirth;
    }

    public String getFinish_university() {
        return finish_university == null ? "" : finish_university;
    }

    public void setFinish_university(String mFinish_university) {
        finish_university = mFinish_university;
    }

    public String getMail() {
        return mail == null ? "" : mail;
    }

    public void setMail(String mMail) {
        mail = mMail;
    }

    public String getTel() {
        return tel == null ? "" : tel;
    }

    public void setTel(String mTel) {
        tel = mTel;
    }

    public String getIndustry() {
        return industry == null ? "" : industry;
    }

    public void setIndustry(String mIndustry) {
        industry = mIndustry;
    }

    public String getPosition() {
        return position == null ? "" : position;
    }

    public void setPosition(String mPosition) {
        position = mPosition;
    }

    public String university;
    public String major;
    public String batch;
    public String company;
    public String birth;
    public String finish_university;
    public String mail;
    public String tel;
    public String industry;
    public String position;
    public String city;

    @Override
    public int getItemLayoutId() {
        return R.layout.header_my_friend_activity;
    }

    @Override
    public void onBind(ViewHolder holder) {
        holder.setText(R.id.tv_friend_name, getName());
        GlideUtils.loadImage(ApplicationsHelper.context(), getAvatar(), holder.getView(R.id.iv_friend_head));
        //显示
        if (getSex() == 0) {
            //未知
            holder.setText(R.id.tv_sex_content,"未知");
        } else if (getSex() == 1) {
            holder.setText(R.id.tv_sex_content,"女");
        } else if (getSex() == 2) {
            holder.setText(R.id.tv_sex_content,"男");
        }
        holder.setText(R.id.tv_school_content,"院校: "+getUniversity());
        holder.setText(R.id.tv_company_content,getCompany());
        holder.setText(R.id.tv_industry_content,getIndustry());
        holder.setText(R.id.tv_post_content,getPosition());
        holder.setText(R.id.tv_city,city);
        RxBindingUtils.setOnClickListener(holder.getView(R.id.iv_my_friend_back), new BaseViewImpl.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityUtils.finishActivity(MyFriendActivityActivity.class);
                    }
                });
    }
}
