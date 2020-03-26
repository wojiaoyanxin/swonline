package com.sunlands.intl.yingshi.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.helper.BaseDialogHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.intl.yingshi.R;

/**
 * 当前包名: com.sunlands.intl.yingshi.dialog
 * 创 建 人: xueh
 * 创建日期: 2019/3/18 11:16
 * 备注：
 */
public class TwoButtonDialog extends BaseDialogHelper implements BaseViewImpl.OnClickListener {
    private static String strLeft, strRight, centerContent;

    public static TwoButtonDialog getInstance(String left, String right, String content) {
        TwoButtonDialog dialogFragment = new TwoButtonDialog();
        dialogFragment.setCanceledBack(true);
        dialogFragment.setCanceledOnTouchOutside(false);
        dialogFragment.setGravity(Gravity.CENTER);
        strLeft = left;
        strRight = right;
        centerContent = content;
        return dialogFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.dialog_two_button_layout;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        TextView tv_left =  view.findViewById(R.id.tv_left);
        TextView tv_right =  view.findViewById(R.id.tv_right);
        TextView tv_content = view.findViewById(R.id.tv_content);
        tv_content.setText(centerContent);
        tv_left.setText(strLeft);
        tv_right.setText(strRight);
        RxBindingHelper.setOnClickListener(tv_left, this);
        RxBindingHelper.setOnClickListener(tv_right, this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_left) {
            if (mOnLeftClick != null) {
                mOnLeftClick.onLeft();
            }
            this.dismiss();
        } else if (id == R.id.tv_right) {
            if (mOnRightClick!=null) {
                mOnRightClick.onRight();
            }

            this.dismiss();
        }
    }



    public interface onLeftClick {
        void onLeft();

    }

    public interface onRightClick {

        void onRight();
    }

    onRightClick mOnRightClick;

    public void setOnRightClick(onRightClick click) {
        this.mOnRightClick = click;
    }

    onLeftClick mOnLeftClick;

    public void setOnLeftClick(onLeftClick click) {
        this.mOnLeftClick = click;
    }
}
