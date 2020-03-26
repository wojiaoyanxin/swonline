package com.sunlands.intl.yingshi.bean.multi;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sunlands.intl.yingshi.ui.classroom.adapter.ReplayLessonAdapter;
import com.sunlands.intl.yingshi.ui.classroom.bean.AllCourseResponse;

public class ReplayLessonItem extends AbstractExpandableItem<AllCourseResponse.ListBean.CourseListBean> implements MultiItemEntity {

    public AllCourseResponse.ListBean.CourseListBean data;

    public AllCourseResponse.ListBean.CourseListBean getData() {
        return data;
    }

    public ReplayLessonItem(AllCourseResponse.ListBean.CourseListBean replayCourseBean) {
        this.data = replayCourseBean;
    }

    @Override
    public int getItemType() {
        return ReplayLessonAdapter.TYPE_LEVEL_1;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}