package com.sunlands.intl.yingshi.ui.home.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.helper.UpdataRequestHelper;
import com.sunlands.intl.yingshi.ui.home.bean.QuestionBean;
import com.sunlands.intl.yingshi.ui.my.handout.view.MaterialActivity;

import static com.sunlands.intl.yingshi.constant.Constants.Key.KEY_FILE_NAME;
import static com.sunlands.intl.yingshi.constant.Constants.Key.KEY_FILE_PATH;

/**
 * Created by yanxin on 2019/3/8.
 */

public class QuestionListAdapter extends BaseQuickAdapter<QuestionBean.ListBean, BaseViewHolder> {

    public QuestionListAdapter() {
        super(R.layout.item_question);
    }


    @Override
    protected void convert(BaseViewHolder helper, QuestionBean.ListBean item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_download_num, item.getNum() + "次浏览");
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MaterialActivity.class);
                intent.putExtra(KEY_FILE_PATH, item.getFile_url());
                intent.putExtra(KEY_FILE_NAME, item.getName());
                mContext.startActivity(intent);
                new UpdataRequestHelper().actionJoin(item.getId() + "");
            }
        });
    }
}
