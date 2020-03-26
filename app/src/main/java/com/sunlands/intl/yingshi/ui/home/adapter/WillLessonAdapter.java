package com.sunlands.intl.yingshi.ui.home.adapter;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.home.bean.HomeDataResponse;

/**
 * Created by yanxin on 2019/3/8.
 */

public class WillLessonAdapter extends BaseQuickAdapter<HomeDataResponse.CoursesBean, BaseViewHolder> {

    public WillLessonAdapter() {
        super(R.layout.item_willlesson);
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeDataResponse.CoursesBean item) {

        helper.setText(R.id.time, item.getStartTime());
        helper.setText(R.id.name, item.getTitle());
        helper.setText(R.id.iv_introduce, item.getSubject());
        RelativeLayout root = helper.getView(R.id.root);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) root.getLayoutParams();
        lp.height = CommonUtils.dip2px(120);
        lp.width = ScreenUtils.getScreenWidth() - CommonUtils.dip2px(50);
        if (getItemCount() == 1) {
            lp.width = ScreenUtils.getScreenWidth() - CommonUtils.dip2px(32);
        } else {
            if (helper.getPosition() == getItemCount() - 1) {
                lp.rightMargin = CommonUtils.dip2px(16);
            }
        }
        root.setLayoutParams(lp);
    }
}
