package com.sunlands.intl.yingshi.ui.activity.home.leakfilling.adapter;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.base.BaseHeaderAdapter;
import com.sunlands.intl.yingshi.bean.multi.PinnedHeaderEntity;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean.LeakOtherBean;
import com.sunlands.intl.yingshi.ui.widget.countdown.CountdownView;
import com.sunlands.intl.yingshi.util.DensityUtil;

import java.util.List;

/**
 * 文件名: ScoreQueryAdapter
 * 描    述: 任务提醒]
 * 创建人: 闫鑫
 * 创建时间:
 */
public class LeakOtherClassAdapter extends BaseHeaderAdapter<PinnedHeaderEntity<LeakOtherBean.ListBean>> {


    int type;

    public LeakOtherClassAdapter(List<PinnedHeaderEntity<LeakOtherBean.ListBean>> data, int i) {
        super(data);
        type = i;
    }

    @Override
    protected void convert(BaseViewHolder helper, PinnedHeaderEntity<LeakOtherBean.ListBean> item) {
        switch (item.getItemType()) {
            case BaseHeaderAdapter.TYPE_HEADER:
                String pinnedHeaderName = item.getPinnedHeaderName();
                helper.setText(R.id.time, pinnedHeaderName);
                if (!("近7天需完成").equals(pinnedHeaderName)) {
                    helper.getView(R.id.findbymounth).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.findbymounth).setVisibility(View.GONE);
                }
                View view = helper.getView(R.id.findbymounth);
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (onClickMonth != null) {
                                onClickMonth.getView((TextView) v);
                            }
                            return true;
                        }

                        return false;
                    }
                });
//                if (helper.getPosition() == 0 && "其他".equals(pinnedHeaderName)) {
//                    helper.getView(R.id.findbymounth).setVisibility(View.GONE);
//                    helper.setTextColor(R.id.time, CommonUtils.getColor(R.color.transparent));
//                }else {
//                    helper.getView(R.id.findbymounth).setVisibility(View.VISIBLE);
//                    helper.setTextColor(R.id.time, CommonUtils.getColor(R.color.text333));
//                }

                break;
            case BaseHeaderAdapter.TYPE_DATA:

                helper.setText(R.id.title, item.getData().getTitle());
                // 0 否  1 是
                int isEnd = item.getData().getHasEnd();
                int isRepeat = item.getData().getCanRepeat();
                int isStart = item.getData().getHasBegin();
                int hasJoin = item.getData().getHasJoined();

                CountdownView tvCountDown = helper.getView(R.id.tv_count_down);
                View LlCountDown = helper.getView(R.id.ll_countdown);

                int left = item.getData().getLeft();
                if (left == 0) {
                    LlCountDown.setVisibility(View.GONE);
                } else {
                    LlCountDown.setVisibility(View.VISIBLE);
                    tvCountDown.setCountdownTime(left, helper.getPosition() + "");
                }

                if (type == 2) {
                    helper.setText(R.id.time, item.getData().getEndTimeFormat());
                    helper.setText(R.id.btn_home, "进入答题");
                    LlCountDown.setVisibility(View.GONE);
                } else if (type == 1) {
                    helper.setText(R.id.time, item.getData().getEndTimeFormat());
                    helper.setText(R.id.btn_home, "查看");
                } else if (type == 3) {
                    LlCountDown.setVisibility(View.GONE);
                    helper.setText(R.id.time, item.getData().getTime());
                    helper.setText(R.id.btn_home, "提交论文");

                    if ((isEnd == 1 && hasJoin == 0)) { //已结束 未参加
                        helper.setTextColor(R.id.btn_home, Color.parseColor("#999999"));
                        helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home_un);
                        helper.setText(R.id.btn_home, "未参加");
                    } else if ((isEnd == 0 && hasJoin == 0)) { //未结束 未参加
                        helper.setTextColor(R.id.btn_home, Color.parseColor("#333333"));
                        helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home);
                        helper.setText(R.id.btn_home, "提交论文");
                    } else {
                        helper.setTextColor(R.id.btn_home, Color.parseColor("#333333"));
                        helper.setBackgroundRes(R.id.btn_home, R.drawable.button_common_home);
                    }
                }

                if (type == 2) {

                    if (isStart == 0) { //未开始
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
                //0 全圆角   // 1 上圆角   //2 下圆角   //3 没有圆角
                View root = helper.getView(R.id.rl_other_home_root);
                root.setPadding(0, DensityUtil.dip2px(mContext, 16), 0, 0);
                int layout = item.getData().getLayout();
                if (layout == 0) {
                    root.setPadding(0, DensityUtil.dip2px(mContext, 16), 0, DensityUtil.dip2px(mContext, 16));
                    helper.setBackgroundRes(R.id.rl_other_home_root, R.drawable.home_plan);
                } else if (layout == 1) {
                    helper.setBackgroundRes(R.id.rl_other_home_root, R.drawable.home_plan_topt);
                } else if (layout == 2) {
                    helper.setBackgroundRes(R.id.rl_other_home_root, R.drawable.home_plan_topb);
                    root.setPadding(0, DensityUtil.dip2px(mContext, 16), 0, DensityUtil.dip2px(mContext, 16));
                } else if (layout == 3) {
                    helper.setBackgroundColor(R.id.rl_other_home_root, Color.WHITE);
                }
                // 没有近七天记录   没有其他

                if ("没有近七天记录".equals(item.getData().getTitle())
                        | "没有其他".equals(item.getData().getTitle())) {
                    helper.setVisible(R.id.root_view, false);
                    helper.setVisible(R.id.kong, true);
                } else {
                    helper.setVisible(R.id.root_view, true);
                    helper.setVisible(R.id.kong, false);
                }

                break;
            default:
                break;

        }
    }

    @Override
    protected void addItemTypes() {
        addItemType(BaseHeaderAdapter.TYPE_HEADER, R.layout.item_leak_class);
        addItemType(BaseHeaderAdapter.TYPE_DATA, R.layout.item_common_leak_class);
    }


    public interface OnClickMonth {

        void getView(TextView view);

    }

    private OnClickMonth onClickMonth;

    public void setOnClickMonth(OnClickMonth onClickMonth) {
        this.onClickMonth = onClickMonth;
    }
}
