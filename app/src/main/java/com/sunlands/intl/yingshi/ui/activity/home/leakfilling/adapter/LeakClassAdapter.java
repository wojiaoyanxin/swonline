package com.sunlands.intl.yingshi.ui.activity.home.leakfilling.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean.LeakClassBean;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;

import java.util.List;

public class LeakClassAdapter extends BaseQuickAdapter<LeakClassBean.CourseListBean, BaseViewHolder> {

    public LeakClassAdapter(List<LeakClassBean.CourseListBean> data) {
        super(R.layout.item_class_leak, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LeakClassBean.CourseListBean item) {

        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.time, item.getTime());
        helper.setText(R.id.package_name, "科目：" + item.getPackageName());
        ImageView lessonCover = (ImageView) helper.getView(R.id.image);
        GlideUtils.loadImage(mContext, item.getPic(), lessonCover, new RoundedCorners(DensityUtil.dip2px(mContext, 4)));

        View viewRoot = helper.getView(R.id.root);

        if(helper.getPosition() == getData().size()) {
            viewRoot.setPadding(0,DensityUtil.dip2px(mContext,18) ,0,DensityUtil.dip2px(mContext,18));
        }else {
            viewRoot.setPadding(0,DensityUtil.dip2px(mContext,18),0,0);
        }

        int status = item.getStatus();
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
            int hasJoin = item.getHasJoined();
            if (hasJoin == 1) {
                view.setVisibility(View.GONE);
                view.setImageResource(R.drawable.ico_signin);
            } else {
                view.setVisibility(View.VISIBLE);
                view.setImageResource(R.drawable.ico_absent);
            }
        }
    }
}
