package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassBean;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;

/**
 * Created by yanxin on 2019/3/8.
 */

public class MoreSmallClassListAdapter extends BaseQuickAdapter<SmallClassBean.PaginationListBean, BaseViewHolder> {

    public MoreSmallClassListAdapter() {
        super(R.layout.item_small_class);
    }

    @Override
    protected void convert(BaseViewHolder helper, SmallClassBean.PaginationListBean item) {
        ImageView lessonCover = helper.getView(R.id.iv_image);
        GlideUtils.loadImage(mContext, item.getThumb(), lessonCover, new RoundedCorners(DensityUtil.dip2px(mContext, 5)));
        TextView tv_title = helper.getView(R.id.tv_title);
        tv_title.setText(item.getTitle());
        TextView tv_name = helper.getView(R.id.tv_name);
        tv_name.setText(item.getTeacher());
        TextView tv_price = helper.getView(R.id.tv_price);
        tv_price.setText("¥" + item.getPrice());
        TextView tv_lable = helper.getView(R.id.tv_lable);
        tv_lable.setText(item.getCatName());
        TextView tv_old_price = helper.getView(R.id.tv_show_price);
        tv_old_price.setText("原价：¥" + item.getShowPrice() + "");
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getCanView() == 1) {
                    SmallClasListActivity.show(mContext, item.getCourseId(), item.getTitle());
                    return;
                }
                SmallClassDetailsActivity.show(mContext, item.getCourseId());
            }
        });
    }
}
