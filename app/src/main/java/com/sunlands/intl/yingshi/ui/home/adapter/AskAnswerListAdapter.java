package com.sunlands.intl.yingshi.ui.home.adapter;

import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.home.bean.AskAnswerBean;
import com.sunlands.intl.yingshi.ui.home.view.AskAnswerDetailsActivity;

/**
 * Created by yanxin on 2019/3/8.
 */

public class AskAnswerListAdapter extends BaseQuickAdapter<AskAnswerBean.ListBean, BaseViewHolder> {

    public AskAnswerListAdapter() {
        super(R.layout.item_ask_answer);
    }


    @Override
    protected void convert(BaseViewHolder helper, AskAnswerBean.ListBean item) {
        helper.setText(R.id.tv_name, item.getTitle());
        helper.setText(R.id.tv_content, item.getContent());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AskAnswerDetailsActivity.class);
                intent.putExtra(AskAnswerDetailsActivity.ANSWERID, item.getAnswerId());
                ActivityUtils.startActivity(intent);
            }
        });
    }
}
