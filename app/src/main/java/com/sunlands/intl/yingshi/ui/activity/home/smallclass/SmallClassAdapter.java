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

public class SmallClassAdapter extends MoreSmallClassListAdapter {

    @Override
    public int getItemCount() {
        return getData().size() >= 3 ? 3 : getData().size();
    }

}
