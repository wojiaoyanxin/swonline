package com.sunlands.intl.yingshi.ui.home.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.home.bean.HomeDataResponse;

/**
 * Created by yanxin on 2019/3/8.
 */

public class ThesisAdapter extends BaseQuickAdapter<HomeDataResponse.WeekPlanBean.ThesisListBean, BaseViewHolder> {

    public ThesisAdapter() {
        super(R.layout.item_common_home);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataResponse.WeekPlanBean.ThesisListBean item) {
        helper.setText(R.id.lable, "");
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.time, item.getTime());
        helper.setText(R.id.btn_home, "提交论文");
        helper.setText(R.id.lable, "论文");
        int hasJoin = item.getHasJoin();
        int isEnd = item.getIsEnd();
        int isStart = item.getIsStart();
        if (isStart == 0) { //待开始
            helper.setTextColor(R.id.btn_home, Color.parseColor("#999999"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home_un);
            helper.setText(R.id.btn_home, "待开始");
        } else if ((isEnd == 1 && hasJoin == 0)) { //已结束 未参加
            helper.setTextColor(R.id.btn_home, Color.parseColor("#999999"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home_un);
            helper.setText(R.id.btn_home, "未参加");
        } else if ((isEnd == 0 && hasJoin == 0)) { //未结束 未参加
            helper.setTextColor(R.id.btn_home, Color.parseColor("#333333"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home);
            helper.setText(R.id.btn_home, "提交论文");
        } else if ((isEnd == 1 && hasJoin == 1)) { //已结束 已参加
            helper.setText(R.id.btn_home, "已结束");
            helper.setTextColor(R.id.btn_home, Color.parseColor("#999999"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home_un);
        } else if ((isEnd == 0 && hasJoin == 1)) { //未结束 已参加
            helper.setText(R.id.btn_home, "已参加");
            helper.setTextColor(R.id.btn_home, Color.parseColor("#333333"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home);
        } else {
            helper.setTextColor(R.id.btn_home, Color.parseColor("#333333"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home);
        }

    }
}
