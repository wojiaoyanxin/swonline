package com.sunlands.intl.yingshi.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.base.BaseDialogFragment;


public class ApplyExpireNoticeDialog extends BaseDialogFragment implements View.OnClickListener {

    ImageView mIvClose;
    RelativeLayout mRlParent;
    TextView mTvSure;

    @Override
    protected void initStyle() {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_dim);
    }

    public static ApplyExpireNoticeDialog newInstance() {
        ApplyExpireNoticeDialog studyNoticeDialog = new ApplyExpireNoticeDialog();
        Bundle bundle = new Bundle();
        studyNoticeDialog.setArguments(bundle);
        return studyNoticeDialog;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIvClose = view.findViewById(R.id.iv_close);
        mRlParent = view.findViewById(R.id.rl_parent);
        mTvSure = view.findViewById(R.id.tv_sure);
        mIvClose.setOnClickListener(this);
        mRlParent.setOnClickListener(this);
        mTvSure.setOnClickListener(this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.dialog_apply_expire_notice;
    }


    @Override
    public void onClick(View view) {
        dismiss();
    }
}