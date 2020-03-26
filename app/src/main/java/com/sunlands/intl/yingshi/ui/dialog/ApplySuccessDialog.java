package com.sunlands.intl.yingshi.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.blankj.utilcode.util.ActivityUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.base.BaseDialogFragment;
import com.sunlands.intl.yingshi.ui.activity.home.apply.ApplyActivity;

public class ApplySuccessDialog extends BaseDialogFragment implements View.OnClickListener {


     ImageView mIvClose;
    RelativeLayout mRlParent;

    @Override
    protected void initStyle() {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_dim);
    }

    public static ApplySuccessDialog newInstance() {
        ApplySuccessDialog studyNoticeDialog = new ApplySuccessDialog();
        Bundle bundle = new Bundle();
        studyNoticeDialog.setArguments(bundle);
        return studyNoticeDialog;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIvClose = view.findViewById(R.id.iv_close);
        mRlParent = view.findViewById(R.id.rl_parent);
        mIvClose.setOnClickListener(this);
        mRlParent.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_apply_success;
    }

    @Override
    public void onClick(View view) {
        dismiss();
        ActivityUtils.finishActivity(ApplyActivity.class);
    }
}