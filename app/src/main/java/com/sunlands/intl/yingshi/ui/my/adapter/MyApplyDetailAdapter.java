package com.sunlands.intl.yingshi.ui.my.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名: ExamScheduleAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/22
 */
public class MyApplyDetailAdapter extends BaseQuickAdapter<EmptyBean, BaseViewHolder> {


    private static List<EmptyBean> sEmptyBeans = new ArrayList<>();
    private final String[] mArrayStepDetail;
    private int[] stepFinishedDrawable = {
            R.drawable.ic_user_info_black,
            R.drawable.ic_edu_bg_black,
            R.drawable.ic_work_exp_black,
            R.drawable.ic_file_upload_black,
            R.drawable.ic_readme_essay_black
    };
    private int[] stepUnfinishedDrawable = {
            R.drawable.ic_user_info_gray,
            R.drawable.ic_edu_bg_gray,
            R.drawable.ic_work_exp_gray,
            R.drawable.ic_file_upload_gray,
            R.drawable.ic_readme_essay_gray
    };

    static {
        for (int i = 0; i < 5; i++) {
            sEmptyBeans.add(new EmptyBean());
        }
    }

    private String mInformationStep;
    private final String[] mArrayFinished;
    private final String[] mArrayUnfinished;
    private final Context mContext;

    public MyApplyDetailAdapter(Context context, String str) {
        super(R.layout.item_my_apply_detail, sEmptyBeans);
        mInformationStep = str;
        mContext = context;
        mArrayStepDetail = context.getResources().getStringArray(R.array.apply_step_detail);
        mArrayFinished = context.getResources().getStringArray(R.array.apply_step_detail_finished);
        mArrayUnfinished = context.getResources().getStringArray(R.array.apply_step_detail_unfinished);
    }

    @Override
    protected void convert(BaseViewHolder helper, EmptyBean item) {
        int position = helper.getAdapterPosition();
        ImageView imageView = (ImageView) helper.getView(R.id.iv_step_detail);
        helper.setText(R.id.tv_step_detail_name, mArrayStepDetail[position]);
        if (!StringUtils.isEmpty(mInformationStep)
                && mInformationStep.contains(String.valueOf(position + 1))) {
            GlideUtils.loadImage(mContext, mContext.getResources().getDrawable(stepFinishedDrawable[position]), imageView);
            helper.setText(R.id.tv_step_detail_status, mArrayFinished[position]);
        } else {
            GlideUtils.loadImage(mContext, mContext.getResources().getDrawable(stepUnfinishedDrawable[position]), imageView);
            helper.setText(R.id.tv_step_detail_status, mArrayUnfinished[position]);

        }
    }
}
