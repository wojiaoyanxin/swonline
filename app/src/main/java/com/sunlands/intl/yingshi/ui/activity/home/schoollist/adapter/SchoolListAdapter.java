package com.sunlands.intl.yingshi.ui.activity.home.schoollist.adapter;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cb.ratingbar.CBRatingBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.activity.home.schoollist.bean.SchoolBean;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.StringUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by yanxin on 2019/3/8.
 */

public class SchoolListAdapter extends BaseQuickAdapter<SchoolBean.UniversityListBean, BaseViewHolder> {

    public SchoolListAdapter() {
        super(R.layout.item_school_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, SchoolBean.UniversityListBean item) {
        ImageView imageView = helper.getView(R.id.image);
        GlideUtils.loadImageFix(mContext, item.getPoster_h5(), imageView);
        String max = StringUtils.toNumber(item.getMax_price());
        String min = StringUtils.toNumber(item.getMin_price());
        if (item.getMax_price() == item.getMin_price()) {
            helper.setText(R.id.pricemax, "¥ " + min);
        } else {
            helper.setText(R.id.pricemax, "¥ " + min + " ~ " + max);
        }
        helper.setText(R.id.school_name, item.getName());
        helper.setText(R.id.intro, item.getSummary());
        helper.setText(R.id.school_name_en, item.getName_en());
        helper.setText(R.id.read_counts, "浏览人数: " + item.getVirtual_view_num());
        List<String> label = item.getLabel();
        TagFlowLayout myFlowLayout = helper.getView(R.id.ll_lable);
        myFlowLayout.setVisibility(View.VISIBLE);
        if (label != null && label.size() > 0) {
            myFlowLayout.setAdapter(new TagAdapter<String>(label) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView iv = new TextView(mContext);
                    iv.setPadding(DensityUtil.dip2px(mContext, 10), DensityUtil.dip2px(mContext, 5),
                            DensityUtil.dip2px(mContext, 10), DensityUtil.dip2px(mContext, 5));
                    iv.setText(s);
                    iv.setGravity(Gravity.CENTER);
                    iv.setTextSize(12);
                    iv.setTextColor(Color.parseColor("#252A32"));
                    iv.setBackgroundResource(R.drawable.shape_btn_enable);
                    return iv;
                }
            });
        } else {
            myFlowLayout.setVisibility(View.GONE);
        }
        String difficulty = item.getDifficulty();
        float v = Float.parseFloat(difficulty);
        CBRatingBar bar = helper.getView(R.id.starbar);
        bar.setStarProgress(v / 5 * 100);
        helper.addOnClickListener(R.id.more);
    }
}
