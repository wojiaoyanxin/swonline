package com.sunlands.intl.yingshi.ui.my.adapter;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.my.bean.UserInfo;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;

/**
 * Created by yanxin on 2019/3/8.
 */

public class LocalWatchAdapter extends BaseQuickAdapter<UserInfo.ViewListBean, BaseViewHolder> {

    public LocalWatchAdapter() {
        super(R.layout.item_my_local_watch);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserInfo.ViewListBean item) {

//        helper.setText(R.id.time, item.getStartTime());
//        helper.setText(R.id.name, item.getTitle());
//        helper.setText(R.id.iv_introduce, item.getSubject());
//        RelativeLayout root = helper.getView(R.id.root);
//        ViewGroup.LayoutParams lp = root.getLayoutParams();
//        lp.height = DensityUtils.dip2px(mContext, 120);
//        if (getData().size() == 1) {
//            lp.width = ScreenUtils.getScreenWidth() - DensityUtils.dip2px(mContext,20);
//        } else {
//            lp.width = ScreenUtils.getScreenWidth() - DensityUtils.dip2px(mContext,50);
//        }
//        root.setLayoutParams(lp);
        helper.setText(R.id.class_name,item.getTitle());
        ImageView view = (ImageView)helper.getView(R.id.image);
        GlideUtils.loadImage(mContext, item.getPic(), view, new RoundedCorners(DensityUtil.dip2px(mContext, 4)));
    }
}
