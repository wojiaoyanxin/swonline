package com.sunlands.intl.yingshi.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.base.BaseDialogFragment;
public class OrderTipDialog extends BaseDialogFragment {


   // @BindView(R.id.tv_no_apply)
    TextView mTvNoApply;
  //  @BindView(R.id.tv_apply)
    TextView mTvApply;
  ///  @BindView(R.id.iv_close)
    ImageView mIvClose;
  //  @BindView(R.id.rl_parent)
    RelativeLayout mRlParent;


    @Override
    protected void initStyle() {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_dim);
    }

    public static OrderTipDialog newInstance() {
        OrderTipDialog studyNoticeDialog = new OrderTipDialog();
        Bundle bundle = new Bundle();
        studyNoticeDialog.setArguments(bundle);
        return studyNoticeDialog;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }


    @Override
    public int getLayoutId() {
        return R.layout.dialog_order_tip;
    }

//    @OnClick({R.id.iv_close, R.id.rl_parent,R.id.tv_no_apply})
//    public void onClickBack() {
//        dismiss();
//    }
//
//    @OnClick({R.id.tv_apply})
//    public void onClickToApply() {
//        ActivityUtils.startActivity( MSiteActivity.class);
//        dismiss();
//    }

}