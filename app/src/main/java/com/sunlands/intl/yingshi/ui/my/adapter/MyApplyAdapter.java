package com.sunlands.intl.yingshi.ui.my.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.my.bean.ApplyResponse;
import com.sunlands.intl.yingshi.util.GlideUtils;

public class MyApplyAdapter extends BaseQuickAdapter<ApplyResponse.ApplyBean, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    private final String[] mApplyStepUnfinishedArray;
    private final String[] mApplyStepFinishedArray;

    public MyApplyAdapter(Context context) {
        super(R.layout.item_my_apply_parent);
        mApplyStepFinishedArray = context.getResources().getStringArray(R.array.apply_step_finished);
        mApplyStepUnfinishedArray = context.getResources().getStringArray(R.array.apply_step_unfinished);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplyResponse.ApplyBean item) {

        View view = helper.getView(R.id.ll_my_apply_detail);
        ImageView ivBg = helper.getView(R.id.iv_my_apply_item_bg);
        View viewArrow = helper.getView(R.id.iv_collapse_expand);
        View clItem = helper.getView(R.id.cl_my_apply_item);
        boolean isGone1 = view.getVisibility() == View.GONE;
        viewArrow.animate().setDuration(250).rotation(!isGone1 ? 90 : 0).start();
        clItem.setOnClickListener(v -> {
            boolean isGone = view.getVisibility() == View.GONE;
            view.setVisibility(isGone ? View.VISIBLE : View.GONE);
            viewArrow.animate().setDuration(250).rotation(isGone ? 90 : 0).start();
        });
        helper.addOnClickListener(R.id.cl_submit_data);
        helper.setText(R.id.tv_college_name, item.getUniversity())
                .setText(R.id.tv_degree_name, item.getMajor())
                .setText(R.id.tv_batch, item.getBatch());
        GlideUtils.loadImageFix(mContext, item.getBgUrl(), ivBg);

        int applicationStep = item.getApplicationStep();
        helper.setText(R.id.tv_status_submit_data, applicationStep >= 1 ? mApplyStepFinishedArray[0] : mApplyStepUnfinishedArray[0]);
        if(applicationStep == 2) {
            helper.setText(R.id.tv_status_data_verification, "正在审核");
        }else {
            helper.setText(R.id.tv_status_data_verification, applicationStep > 2 ? mApplyStepFinishedArray[1] : mApplyStepUnfinishedArray[1]);
        }
        helper.setText(R.id.tv_status_send_offer, applicationStep > 3 ? mApplyStepFinishedArray[2] : mApplyStepUnfinishedArray[2]);
        helper.setText(R.id.tv_status_pay_tuition_fees, applicationStep > 4 ? mApplyStepFinishedArray[3] : mApplyStepUnfinishedArray[3]);
        helper.setText(R.id.tv_status_course_start, applicationStep > 5 ? mApplyStepFinishedArray[4] : mApplyStepUnfinishedArray[4]);
        helper.setText(R.id.tv_status_language_exam, applicationStep > 6 ? mApplyStepFinishedArray[5] : mApplyStepUnfinishedArray[5]);
        helper.setText(R.id.tv_status_issuing_official_offer, applicationStep > 7 ? mApplyStepFinishedArray[6] : mApplyStepUnfinishedArray[6]);
    }

}