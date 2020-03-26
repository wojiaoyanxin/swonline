package com.sunlands.intl.yingshi.ui.activity.home.plan.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.base.BaseHeaderAdapter;
import com.sunlands.intl.yingshi.bean.multi.PinnedHeaderEntity;
import com.sunlands.intl.yingshi.ui.activity.home.plan.bean.PlanBean;
import com.sunlands.intl.yingshi.ui.widget.countdown.CountdownView;

import java.util.List;

public class ClassOtherAdapter extends BaseHeaderAdapter<PinnedHeaderEntity<PlanBean.DateListBean.ListBean>> {


    public ClassOtherAdapter(List<PinnedHeaderEntity<PlanBean.DateListBean.ListBean>> data) {
        super(data);
    }

    @Override
    protected void addItemTypes() {
        addItemType(BaseHeaderAdapter.TYPE_HEADER, R.layout.item_plan_lesson_header);
        addItemType(BaseHeaderAdapter.TYPE_DATA, R.layout.item_common_other_home);
    }

    @Override
    protected void convert(BaseViewHolder helper, PinnedHeaderEntity<PlanBean.DateListBean.ListBean> item) {
        switch (item.getItemType()) {
            case BaseHeaderAdapter.TYPE_HEADER:
                helper.setText(R.id.tv_date, item.getPinnedHeaderName());
                TextView view = helper.getView(R.id.tv_date);
                view.setTextSize(20);
                break;
            case BaseHeaderAdapter.TYPE_DATA:
                helper.setText(R.id.lable, "");
                helper.setText(R.id.title, item.getData().getTitle());
                helper.setText(R.id.time, item.getData().getTime());
                String classify = item.getData().getClassify();
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
                int isEnd = item.getData().getIsEnd();
                int isRepeat = item.getData().getIsRepeat();
                int isStart = item.getData().getIsStart();
                int hasJoin = item.getData().getHasJoin();
                String thesisId = item.getData().getThesisId();

                CountdownView tvCountDown = helper.getView(R.id.tv_count_down);
                View LlCountDown = helper.getView(R.id.ll_countdown);

                int left = item.getData().getLeft();
                if (left == 0) {
                    LlCountDown.setVisibility(View.GONE);
                } else {
                    LlCountDown.setVisibility(View.VISIBLE);
                    tvCountDown.setCountdownTime(left, helper.getPosition() + "");
                }




                if ("1".equals(classify) | "2".equals(classify)) {

                } else if (isStart == 0) { //未开始
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


                if (!TextUtils.isEmpty(thesisId)) {
                    helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home);
                    helper.setText(R.id.btn_home, "提交论文");
                    helper.setTextColor(R.id.btn_home, Color.parseColor("#333333"));
                    helper.setText(R.id.lable, "论文");

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
                        helper.setText(R.id.btn_home, "已结束 ");
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
                Boolean show = item.getData().getShow();
                if (show == true) {
                    helper.setVisible(R.id.view, true);
                } else {
                    helper.setVisible(R.id.view, false);
                }
                //0 全圆角   // 1 上圆角   //2 下圆角   //3 没有圆角
                int layout = item.getData().getLayout();
                if (layout == 0) {
                    helper.setBackgroundRes(R.id.rl_other_home_root, R.drawable.home_plan);
                } else if (layout == 1) {
                    helper.setBackgroundRes(R.id.rl_other_home_root, R.drawable.home_plan_topt);
                } else if (layout == 2) {
                    helper.setBackgroundRes(R.id.rl_other_home_root, R.drawable.home_plan_topb);
                } else if (layout == 3) {
                    helper.setBackgroundColor(R.id.rl_other_home_root, Color.WHITE);
                }
                break;
            default:
                break;
        }
    }
}
