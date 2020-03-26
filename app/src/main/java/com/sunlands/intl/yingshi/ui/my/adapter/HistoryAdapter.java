package com.sunlands.intl.yingshi.ui.my.adapter;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.my.bean.HistorySectionEntity;
import com.sunlands.intl.yingshi.ui.my.bean.HistoryBean;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.StringFormatUtils;

/**
 * 文件名: HistoryAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/23
 */
public class HistoryAdapter extends BaseSectionQuickAdapter<HistorySectionEntity, BaseViewHolder> {
    public HistoryAdapter() {
        super(R.layout.item_history_content, R.layout.item_history_header, null);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, HistorySectionEntity item) {
        helper.setText(R.id.tv_history_header, String.valueOf(item.header));
    }

    @Override
    protected void convert(BaseViewHolder helper, HistorySectionEntity item) {
        HistoryBean historyBean = item.t;
        ImageView imageView = helper.getView(R.id.iv_cover);
        GlideUtils.loadImage(mContext,  historyBean.getHeadImg(), imageView, new RoundedCorners(DensityUtil.dip2px(mContext, 4)));
        helper.setText(R.id.tv_course_name, historyBean.getTitle())
                .setText(R.id.tv_teacher_name, historyBean.getTeacher());
        String last_progress = historyBean.getLast_progress();
        try {
            int seconds = Integer.parseInt(last_progress);
            String s = StringFormatUtils.secondsToFormatTime(seconds);
            helper.setText(R.id.tv_last_watch_time, s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (historyBean.getCourseType() == 2) {
            if (historyBean.getIs_free() == 0) {
                helper.setVisible(R.id.limit_free, false);
            } else {
                helper.setVisible(R.id.limit_free, true);
            }
        } else {
            helper.setVisible(R.id.limit_free, false);
        }

    }
}
