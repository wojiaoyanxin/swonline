package com.sunlands.intl.yingshi.ui.home.adapter;

import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.home.bean.HomeDataResponse;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.web.WebViewActivity;

/**
 * Created by yanxin on 2019/3/8.
 */

public class TodayHotListFromHomeAdapter extends BaseQuickAdapter<HomeDataResponse.NewBean, BaseViewHolder> {

    public TodayHotListFromHomeAdapter() {
        super(R.layout.item_hot);
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeDataResponse.NewBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_hot_num, item.getViewNum() + " 热度");
        helper.setText(R.id.tv_lable_name, item.getAuthor());
        GlideUtils.loadImage(mContext, item.getPoster(), helper.getView(R.id.iv_image), new RoundedCorners(DensityUtil.dip2px(mContext, 4)));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.goActivity(item.getUrl(), item.getTitle());
            }
        });
        if (item.getType() == 2) {
            helper.setVisible(R.id.iv_play, true);
        } else {
            helper.setVisible(R.id.iv_play, false);
        }
        if (TextUtils.isEmpty(item.getPoster())) {
            helper.getView(R.id.rl_img).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.rl_img).setVisibility(View.VISIBLE);
        }
    }
}
