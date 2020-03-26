package com.sunlands.intl.yingshi.ui.activity.home.examarrangement;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import java.util.List;

public class ExamScheduleAdapter extends BaseQuickAdapter<ExamArrangementsResponse.ExamBean, BaseViewHolder> {

    public ExamScheduleAdapter(List<ExamArrangementsResponse.ExamBean> data) {
        super(R.layout.item_exam_schedule, data);//
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamArrangementsResponse.ExamBean item) {
        helper.setText(R.id.tv_exam_name, item.getName());
        helper.setText(R.id.tv_exam_time, item.getDatetime());
        helper.setText(R.id.join_tv, item.getStatus());
        helper.setText(R.id.tv_count_down_day, String.valueOf(item.getCountDown()));
        // 开启倒计时
        helper.setTextColor(R.id.tv_count_down_day, mContext.getResources().getColor(
                item.getCountDown() < 6 ? R.color.colorTextCountDownGold
                        : R.color.colorTextCountDownWhite));
        //statusCode  1 未开始   2  未参加  3  已参加

        ImageView view = helper.getView(R.id.iv_jiexi);

        if (item.getStatusCode() == 3) {//已参加    666666

            helper.setBackgroundColor(R.id.join_tv, Color.parseColor("#dddddd"));
            helper.setTextColor(R.id.look_jiexi, Color.parseColor("#666666"));
            helper.setText(R.id.join_exam, "进入答题");
            helper.setImageResource(R.id.iv_jiexi, R.drawable.look_jiexi);

        } else if (item.getStatusCode() == 1) {//未开始   666666
            helper.setBackgroundColor(R.id.join_tv, Color.parseColor("#dddddd"));
            helper.setTextColor(R.id.look_jiexi, Color.parseColor("#BFBFBF"));
            helper.setText(R.id.join_exam, "进入答题");
            helper.setImageResource(R.id.iv_jiexi, R.drawable.watch_any);

        } else if (item.getStatusCode() == 2) {//未参加   BFBFBF
            helper.setTextColor(R.id.look_jiexi, Color.parseColor("#BFBFBF"));
            helper.setBackgroundColor(R.id.join_tv, Color.parseColor("#DCA73D"));
            helper.setText(R.id.join_exam, "进入答题");
            helper.setImageResource(R.id.iv_jiexi, R.drawable.watch_any);
        }

        View ll = helper.getView(R.id.ll);
        View line = helper.getView(R.id.line);
        if (item.getIs_repeat() == 1) {
            ll.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        } else {
            ll.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.join_exam);
        helper.addOnClickListener(R.id.look_jiexi);
    }
}
