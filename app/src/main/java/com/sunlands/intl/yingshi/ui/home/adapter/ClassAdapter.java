package com.sunlands.intl.yingshi.ui.home.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.home.bean.HomeDataResponse;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;

/**
 * Created by yanxin on 2019/3/8.
 */

public class ClassAdapter extends BaseQuickAdapter<HomeDataResponse.WeekPlanBean.CourseListBean, BaseViewHolder> {

    public ClassAdapter() {
        super(R.layout.item_class);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataResponse.WeekPlanBean.CourseListBean item) {

        int status = item.getPlayStatus();
        ImageView view = helper.getView(R.id.state);
        view.setVisibility(View.GONE);

        if (status == 0) { //未开始
            view.setVisibility(View.VISIBLE);
            view.setImageResource(R.drawable.ico_waitingbegin);
        } else if (status == 1) {  //正在播放
            view.setVisibility(View.VISIBLE);
            view.setImageResource(R.drawable.ico_living);
        } else if (status == 2) {  //回放生成中
            view.setVisibility(View.GONE);
        } else if (status == 3){
            int hasJoin = item.getHasJoin();
            if (hasJoin == 1) {
                view.setVisibility(View.GONE);
                view.setImageResource(R.drawable.ico_signin);
            } else {
                view.setVisibility(View.VISIBLE);
                view.setImageResource(R.drawable.ico_absent);
            }
        }
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.time, item.getTime());
        helper.setText(R.id.package_name, "科目：" + item.getPackageX());
        ImageView lessonCover = (ImageView) helper.getView(R.id.image);
        GlideUtils.loadImage(mContext, item.getThumb(), lessonCover,new RoundedCorners(DensityUtil.dip2px(mContext,4)));
    }
}
