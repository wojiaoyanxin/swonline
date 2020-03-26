package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.util.GlideUtils;

/**
 * @author yxin
 * @date 2020-01-06 - 13:03
 * @des
 */
public class SmallClassDetailsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SmallClassDetailsAdapter() {
        super(R.layout.layout_small_intro_img);
    }

    @Override
    protected void convert(BaseViewHolder holder, String s) {
        GlideUtils.loadIntoUseFitWidth(mContext, s, holder.getView(R.id.iv_img));
    }
}
