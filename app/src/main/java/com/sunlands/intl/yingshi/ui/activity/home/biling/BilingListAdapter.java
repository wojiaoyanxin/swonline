package com.sunlands.intl.yingshi.ui.activity.home.biling;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.helper.UpdataRequestHelper;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;

/**
 * Created by yanxin on 2019/3/8.
 * 大咖课程列表
 */

public class BilingListAdapter extends BaseQuickAdapter<BilingBean.ListBean, BaseViewHolder> {

    public BilingListAdapter() {

        super(R.layout.item_bilingdaka);
    }

    @Override
    protected void convert(BaseViewHolder helper, BilingBean.ListBean item) {


        int is_free = item.getIs_free();
        int view_num = item.getView_num();
        if (is_free == 0) {

            helper.setVisible(R.id.limit_free, false);
        } else {
            helper.setVisible(R.id.limit_free, true);
        }


        int status = item.getStatus();
        TextView statusView = helper.getView(R.id.status);
        TextView yuyue = helper.getView(R.id.yuyue);
        yuyue.setVisibility(View.GONE);
        statusView.setVisibility(View.VISIBLE);
        helper.setText(R.id.watch_counts, view_num + "人观看");
        statusView.setTextColor(Color.parseColor("#ffffff"));
        if (status == 1) { //提醒预约
            yuyue.setVisibility(View.VISIBLE);
            yuyue.setBackgroundResource(R.drawable.button_common_home);
            yuyue.setText("预约");
            statusView.setVisibility(View.GONE);
            helper.setText(R.id.watch_counts, view_num + "人想看");
            yuyue.setTextColor(Color.parseColor("#333333"));
        } else if (status == 2) { //已经预约
            yuyue.setVisibility(View.VISIBLE);
            yuyue.setText("已预约");
            yuyue.setTextColor(Color.parseColor("#999999"));
            yuyue.setBackgroundResource(R.drawable.button_common_home_un);
            statusView.setVisibility(View.GONE);
            helper.setText(R.id.watch_counts, view_num + "人想看");
        } else if (status == 3) { //直播中
            statusView.setText("直播中");
            statusView.setBackgroundResource(R.drawable.living);
        } else if (status == 4) { //回放生成中
            statusView.setBackgroundResource(R.drawable.zhibozhong);
            statusView.setText("生成中");
        } else if (status == 5) { //查看回放
            statusView.setBackgroundResource(R.drawable.yijieshu);
            statusView.setText("回放");
        }

        String intro = item.getTeacher_label();
        String title = item.getTitle();
        String teacher_name = item.getTeacher_name();

        helper.setText(R.id.teacher_intro, intro);
        helper.setText(R.id.teacher_name, teacher_name);
        helper.setText(R.id.class_name, title);
        helper.setText(R.id.time, item.getTime());
        ImageView lessonCover = (ImageView) helper.getView(R.id.daka_head_img);
        GlideUtils.loadImage(mContext, item.getTeacher_head_img(), lessonCover, new RoundedCorners(DensityUtil.dip2px(mContext, 4)));
        TextView watchCounts = helper.getView(R.id.watch_counts);

        yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ("预约".equals(yuyue.getText().toString())) {
                    new UpdataRequestHelper().order(item.getCourse_id() + "", yuyue, watchCounts, view_num);
                }
            }
        });
    }
}
