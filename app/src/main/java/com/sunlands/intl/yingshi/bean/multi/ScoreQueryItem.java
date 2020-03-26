package com.sunlands.intl.yingshi.bean.multi;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sunlands.intl.yingshi.ui.activity.home.scorequery.ScoreResponse;
import com.sunlands.intl.yingshi.ui.classroom.adapter.ReplayLessonAdapter;


public class ScoreQueryItem extends AbstractExpandableItem<ScoreResponse.ScoreListBean.ExamListBean> implements MultiItemEntity {

    public ScoreResponse.ScoreListBean.ExamListBean data;

    public ScoreResponse.ScoreListBean.ExamListBean getData() {
        return data;
    }

    public ScoreQueryItem(ScoreResponse.ScoreListBean.ExamListBean replayCourseBean) {
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