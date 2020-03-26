package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassUnitsBean;
import com.sunlands.intl.yingshi.util.GlideUtils;

/**
 * Created by yanxin on 2019/3/8.
 */

public class SmallClassListAdapter extends BaseQuickAdapter<SmallClassUnitsBean.ListBean, BaseViewHolder> {

    public SmallClassListAdapter() {
        super(R.layout.item_small_list_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, SmallClassUnitsBean.ListBean item) {
        ImageView lessonCover = helper.getView(R.id.iv_small_teacher_img);
        GlideUtils.loadRoundImage(mContext, item.getThumb(), lessonCover);
        TextView tv_title = helper.getView(R.id.tv_small_name);
        tv_title.setText(item.getTeach_unit_name());
        TextView tv_name = helper.getView(R.id.tv_teacher_name);
        tv_name.setText("讲师: " + item.getTeacher());
        TextView time = helper.getView(R.id.tv_small_start_time);
        time.setText(item.getStart_time());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmallPlayActivity.show(mContext,item.getSmall_course_id() + "", item.getTeach_unit_name());
            }
        });
    }
}
