package com.sunlands.intl.yingshi.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.EmptyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名: ExamScheduleAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/22
 */
public class AnswerCardAdapter extends BaseQuickAdapter<EmptyBean, BaseViewHolder> {


    private static List<EmptyBean> sEmptyBeans = new ArrayList<>();

    static {
        for (int i = 0; i < 100; i++) {
            sEmptyBeans.add(new EmptyBean());
        }
    }

    public AnswerCardAdapter(@Nullable List<EmptyBean> data) {
        super(R.layout.item_answer_card, sEmptyBeans);

    }

    @Override
    protected void convert(BaseViewHolder helper, EmptyBean item) {
        helper.setText(R.id.tv_index, String.valueOf(helper.getAdapterPosition()));
    }
}
