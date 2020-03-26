package com.sunlands.intl.yingshi.ui.home.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.home.bean.HomeDataResponse;

/**
 * Created by yanxin on 2019/3/8.
 */

public class ExamAdapter extends BaseQuickAdapter<HomeDataResponse.WeekPlanBean.ExamListBean, BaseViewHolder> {

    public ExamAdapter() {
        super(R.layout.item_common_home);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataResponse.WeekPlanBean.ExamListBean item) {

        helper.setText(R.id.lable, "");
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.time, item.getTime());
        String classify = item.getClassify();
        if ("1".equals(classify)) {
            helper.setText(R.id.lable, "测试");
            helper.setText(R.id.btn_home, "查看");
        } else if ("2".equals(classify)) {
            helper.setText(R.id.btn_home, "查看");
            helper.setText(R.id.lable, "随堂考");
        } else if ("3".equals(classify)) {
            helper.setText(R.id.lable, "科目考试");
            helper.setText(R.id.btn_home, "进入答题");
        } else if ("4".equals(classify)) {
            helper.setText(R.id.btn_home, "进入答题");
            helper.setText(R.id.lable, "尚德考试");
        }
        // 0 否  1 是
        int isEnd = item.getIsEnd();
        int isRepeat = item.getIsRepeat();
        int isStart = item.getIsStart();
        int hasJoin = item.getHasJoin();

        if (isStart == 0 ) { //未开始
            helper.setText(R.id.btn_home, "待开始");
            helper.setTextColor(R.id.btn_home, Color.parseColor("#999999"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home_un);
        } else if ((isEnd == 1 && hasJoin == 0)) { //已结束未参加
            helper.setText(R.id.btn_home, "未参加");
            helper.setTextColor(R.id.btn_home, Color.parseColor("#999999"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home_un);
        } else if (isStart == 1 && hasJoin == 0) { //已经开始未参加
            helper.setTextColor(R.id.btn_home, Color.parseColor("#333333"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home);
        } else if ((isRepeat == 1 && hasJoin == 1 && isEnd == 0)) { //已参加  支持多次参加
            helper.setTextColor(R.id.btn_home, Color.parseColor("#333333"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home);
            helper.setText(R.id.btn_home, "查看解析");
        } else if (isRepeat == 0 && hasJoin == 1 && isEnd == 0) { //已参加 不 支持多次参加
            helper.setTextColor(R.id.btn_home, Color.parseColor("#333333"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home);
            helper.setText(R.id.btn_home, "查看解析");
        } else if ((isEnd == 1 && hasJoin == 1)) { //已结束 已参加
            helper.setText(R.id.btn_home, "查看解析");
            helper.setTextColor(R.id.btn_home, Color.parseColor("#333333"));
            helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home);
        }
    }
}
