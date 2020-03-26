package com.sunlands.intl.yingshi.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.base.BaseDialogFragment;


public class StudyNoticeDialog extends BaseDialogFragment implements View.OnClickListener {

    TextView mTvNoOpenNotice;
    TextView mTvOpenNotice;
    ImageView mIvClose;
    RelativeLayout mRlParent;

    @Override
    protected void initStyle() {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_dim);
    }

    public static StudyNoticeDialog newInstance() {
        StudyNoticeDialog studyNoticeDialog = new StudyNoticeDialog();
        Bundle bundle = new Bundle();
        studyNoticeDialog.setArguments(bundle);
        return studyNoticeDialog;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mTvNoOpenNotice = view.findViewById(R.id.tv_no_open_notice);
        mTvOpenNotice = view.findViewById(R.id.tv_open_notice);
        mIvClose = view.findViewById(R.id.iv_close);
        mRlParent = view.findViewById(R.id.rl_parent);
        mRlParent.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_study_notice;
    }

    @Override
    public void onClick(View view) {

    }
}