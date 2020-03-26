package com.sunlands.intl.yingshi.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.AnswerCardTabItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名: ExamScheduleAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/22
 */
public class AnswerCardTabAdapter extends BaseQuickAdapter<AnswerCardTabItem, AnswerCardTabAdapter.MyViewHolder> {
    public static final int PAGE_SIZE = 30;


    int colorTextUnSelect;
    int colorTextSelect;


    public AnswerCardTabAdapter() {
        super(R.layout.item_answer_card_tab);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = super.onCreateViewHolder(parent, viewType);
        colorTextUnSelect = mContext.getResources().getColor(R.color.colorBtnSureGold);
        colorTextSelect = mContext.getResources().getColor(R.color.white);
        return myViewHolder;
    }

    @Override
    protected void convert(MyViewHolder helper, AnswerCardTabItem item) {
        helper.setText(R.id.tv_index_start, String.valueOf(item.getIndexStart()));
        helper.setText(R.id.tv_index_end, String.valueOf(item.getIndexEnd()));
        helper.getView(R.id.ll_tab).setSelected(item.isSelected());
        helper.setTextColor(R.id.tv_index_start, getTextColor(item.isSelected()));
        helper.setTextColor(R.id.tv_index_split, getTextColor(item.isSelected()));
        helper.setTextColor(R.id.tv_index_end, getTextColor(item.isSelected()));
    }

    private int getTextColor(boolean isSelected) {
        return isSelected ? colorTextSelect : colorTextUnSelect;
    }

    private List<AnswerCardTabItem> mAnswerCardTabItems = new ArrayList<>();

    public void setCardItem(int totalCount) {
        mAnswerCardTabItems.clear();
        int lasePageSize = totalCount % PAGE_SIZE;
        int pageCount = totalCount / PAGE_SIZE + 1;
        for (int i = 0; i < pageCount; i++) {
            AnswerCardTabItem answerCardTabItem = new AnswerCardTabItem();
            answerCardTabItem.setIndexStart(i * PAGE_SIZE + 1);
            if (i == pageCount - 1) {
                answerCardTabItem.setIndexEnd(i * PAGE_SIZE + lasePageSize);
            } else {
                answerCardTabItem.setIndexEnd(i * PAGE_SIZE + PAGE_SIZE);
            }
            answerCardTabItem.setSelected(i == 0);
            mAnswerCardTabItems.add(answerCardTabItem);
        }
        setNewData(mAnswerCardTabItems);
    }


    public void selectPage(int pageIndex) {
        for (AnswerCardTabItem answerCardTabItem : mAnswerCardTabItems) {
            answerCardTabItem.setSelected(false);
        }
        mAnswerCardTabItems.get(pageIndex).setSelected(true);
        notifyDataSetChanged();

    }

    public class MyViewHolder extends BaseViewHolder {

        public MyViewHolder(View view) {
            super(view);
        }
    }
}
