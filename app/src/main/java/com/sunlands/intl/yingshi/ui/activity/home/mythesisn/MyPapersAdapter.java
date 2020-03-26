package com.sunlands.intl.yingshi.ui.activity.home.mythesisn;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;

import java.util.List;

/**
 * 文件名: ExamScheduleAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/22
 */
public class MyPapersAdapter extends BaseQuickAdapter<PagerBean, BaseViewHolder> {


    public MyPapersAdapter(@Nullable List<PagerBean> data) {
        super(R.layout.item_my_papers, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PagerBean item) {
        helper.setText(R.id.tv_subject_name, item.getSubjectName());
        helper.setText(R.id.tv_pager_title, item.getTitle());
        helper.addOnClickListener(R.id.fl_file);
        helper.addOnClickListener(R.id.ll_jiexi);
    }
}
