package com.sunlands.intl.yingshi.ui.activity.home.plan.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.base.BaseHeaderAdapter;
import com.sunlands.intl.yingshi.bean.multi.PinnedHeaderEntity;
import com.sunlands.intl.yingshi.ui.activity.home.plan.bean.PlanBean;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;

import java.util.List;

public class ClassLessonsAdapter extends BaseHeaderAdapter<PinnedHeaderEntity<PlanBean.DateListBean.ListBean>> {

    public ClassLessonsAdapter(List<PinnedHeaderEntity<PlanBean.DateListBean.ListBean>> data) {
        super(data);
    }

    @Override
    protected void addItemTypes() {
        addItemType(BaseHeaderAdapter.TYPE_HEADER, R.layout.item_plan_lesson_header);
        addItemType(BaseHeaderAdapter.TYPE_DATA, R.layout.item_class_plan);
    }

    @Override
    protected void convert(BaseViewHolder helper, PinnedHeaderEntity<PlanBean.DateListBean.ListBean> item) {
        switch (item.getItemType()) {
            case BaseHeaderAdapter.TYPE_HEADER:
                helper.setText(R.id.tv_date, item.getPinnedHeaderName());
                break;
            case BaseHeaderAdapter.TYPE_DATA:
                //0 未开始   3   未出勤
                int status = item.getData().getPlayStatus();
                ImageView view = helper.getView(R.id.state);
                view.setVisibility(View.VISIBLE);

                if (status == 0) { //未开始
                    view.setVisibility(View.VISIBLE);
                    view.setImageResource(R.drawable.ico_waitingbegin);
                } else if (status == 1) {  //正在播放
                    view.setVisibility(View.VISIBLE);
                    view.setImageResource(R.drawable.ico_living);
                } else if (status == 2) {  //回放生成中
                    view.setVisibility(View.GONE);
                } else if (status == 3){
                    int hasJoin = item.getData().getHasJoin();
                    if (hasJoin == 1) {
                        view.setVisibility(View.GONE);
                        view.setImageResource(R.drawable.ico_signin);
                    } else {
                        view.setVisibility(View.VISIBLE);
                        view.setImageResource(R.drawable.ico_absent);
                    }
                }
                helper.setText(R.id.title, item.getData().getTitle());
                helper.setText(R.id.time, item.getData().getTime());
                helper.setText(R.id.package_name, "科目：" + item.getData().getPackageX());
                ImageView lessonCover = (ImageView) helper.getView(R.id.image);
                GlideUtils.loadImage(mContext, item.getData().getThumb(), lessonCover,new RoundedCorners(DensityUtil.dip2px(mContext,4)));
                //0 全圆角   // 1 上圆角   //2 下圆角   //3 没有圆角
                int layout = item.getData().getLayout();
                if (layout == 0) {
                    helper.setBackgroundRes(R.id.rl_plan_root, R.drawable.home_plan);
                } else if (layout == 1) {
                    helper.setBackgroundRes(R.id.rl_plan_root, R.drawable.home_plan_topt);
                } else if (layout == 2) {
                    helper.setBackgroundRes(R.id.rl_plan_root, R.drawable.home_plan_topb);
                } else if (layout == 3) {
                    helper.setBackgroundColor(R.id.rl_plan_root, Color.WHITE);
                }
                break;
            default:
                break;
        }

    }
}
