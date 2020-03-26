package com.sunlands.intl.yingshi.ui.home.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.home.bean.HomeDataResponse;
import com.sunlands.intl.yingshi.ui.widget.countdown.CountdownView;

/**
 * Created by yanxin on 2019/3/8.
 */

public class HomeWorkAdapter extends BaseQuickAdapter<HomeDataResponse.WeekPlanBean.TaskListBean, BaseViewHolder> {

    public HomeWorkAdapter() {
        super(R.layout.item_common_home);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataResponse.WeekPlanBean.TaskListBean item) {

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

        CountdownView tvCountDown = helper.getView(R.id.tv_count_down);
        View LlCountDown = helper.getView(R.id.ll_countdown);

        int left = item.getLeft();
        if (left == 0) {
            LlCountDown.setVisibility(View.GONE);
        } else {
            LlCountDown.setVisibility(View.VISIBLE);
            tvCountDown.setCountdownTime(left, helper.getPosition() + "");
        }
    }
}
