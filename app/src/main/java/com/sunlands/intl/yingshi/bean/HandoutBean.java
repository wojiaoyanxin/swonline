package com.sunlands.intl.yingshi.bean;

import com.sunlands.intl.yingshi.greendao.db.HandoutDbBean;
import com.sunlands.intl.yingshi.bean.multi.SubjectItem;

import java.util.List;

/**
 * Created by yanxin on 2019/4/25.
 */

public class HandoutBean {

    SubjectItem subjectItem;

    List<HandoutDbBean> mHandoutDbBeans;

    public SubjectItem getSubjectItem() {
        return subjectItem;
    }

    public void setSubjectItem(SubjectItem subjectItem) {
        this.subjectItem = subjectItem;
    }

    public List<HandoutDbBean> getmHandoutDbBeans() {
        return mHandoutDbBeans;
    }

    public void setmHandoutDbBeans(List<HandoutDbBean> mHandoutDbBeans) {
        this.mHandoutDbBeans = mHandoutDbBeans;
    }
}
