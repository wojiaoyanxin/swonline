package com.sunlands.intl.yingshi.ui.activity.home.examarrangement;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.widget.countdown.CountdownView;

import java.util.List;


/**
 * 文件名: ExamScheduleAdapter
 * 描    述: [考试安排]
 * 创建人: duzzi
 * 创建时间: 2018/8/22
 */
public class HomeWorkScheduleAdapter extends BaseQuickAdapter<ExamArrangementsResponse.ExamBean, BaseViewHolder> {

    public HomeWorkScheduleAdapter(List<ExamArrangementsResponse.ExamBean> data) {
        super(R.layout.item_homework_schedule, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamArrangementsResponse.ExamBean item) {

        helper.setText(R.id.tv_exam_name, item.getName());
        helper.setText(R.id.tv_exam_time, item.getDatetime());
        helper.setText(R.id.join_tv, item.getStatus());

        CountdownView singleCountDownView = helper.getView(R.id.tv_count_down);
        View LlCountDown = helper.getView(R.id.ll_countdown);

        int left = item.getLeft();
        if (left == 0) {
            LlCountDown.setVisibility(View.GONE);
        } else {
            LlCountDown.setVisibility(View.VISIBLE);
            singleCountDownView.setCountdownTime(left, helper.getPosition() + "");
        }

        //statusCode  1 未开始   2  未参加  3  已参加

        if (item.getStatusCode() == 3) {//已参加    666666

            helper.setBackgroundColor(R.id.join_tv, Color.parseColor("#dddddd"));
            helper.setTextColor(R.id.look_jiexi, Color.parseColor("#666666"));
            helper.setText(R.id.join_exam, "进入答题");
            helper.setImageResource(R.id.iv_jiexi, R.drawable.look_jiexi);
        } else if (item.getStatusCode() == 1) {//未开始   666666
            helper.setBackgroundColor(R.id.join_tv, Color.parseColor("#dddddd"));
            helper.setTextColor(R.id.look_jiexi, Color.parseColor("#BFBFBF"));
            helper.setImageResource(R.id.iv_jiexi, R.drawable.watch_any);
            helper.setText(R.id.join_exam, "进入答题");
        } else if (item.getStatusCode() == 2) {//未参加   BFBFBF
            helper.setTextColor(R.id.look_jiexi, Color.parseColor("#BFBFBF"));
            helper.setBackgroundColor(R.id.join_tv, Color.parseColor("#DCA73D"));
            helper.setImageResource(R.id.iv_jiexi, R.drawable.watch_any);
            helper.setText(R.id.join_exam, "进入答题");
        }

    }
}
