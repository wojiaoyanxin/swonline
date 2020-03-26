package com.sunlands.intl.yingshi.ui.question.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

import com.sunlands.comm_core.utils.DrawableUtils;
import com.sunlands.intl.yingshi.R;


/**
 * 创 建 人: xueh
 * 创建日期: 2019/2/24 15:39
 * 备注：可选中ConstraintLayout
 */
public class CheckableConstraintLayout extends ConstraintLayout implements Checkable {

    // checked状态
    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};

    // 是否选中
    private boolean mChecked = false;

    public CheckableConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 判断是否选中
     */
    public boolean isChecked() {
        return mChecked;
    }

    /**
     * 设置选中状态
     */
    public void setChecked(boolean b) {
        if (b != mChecked) {
            mChecked = b;
            refreshDrawableState();
        }
        if (mChecked) {
            DrawableUtils.setRoundBg(this,8, R.color.cl_FFF7F2);
        } else {
            DrawableUtils.setRoundBg(this, 8, R.color.cl_F5F9FC);
        }
    }

    /**
     * 切换当前的选中状态
     */
    public void toggle() {
        setChecked(!mChecked);
    }

    public void setRightState() {
        DrawableUtils.setRoundBg(this, 8, R.color.cl_DCF6E9);
        ((ImageView)findViewById(R.id.iv_question_state)).setImageResource(R.drawable.ic_q_state_right);
    }

    public void setErrorState() {
        DrawableUtils.setRoundBg(this, 8, R.color.cl_FFE9EB);
        ((ImageView)findViewById(R.id.iv_question_state)).setImageResource(R.drawable.ic_q_state_error);
    }

    public void setCheckedState() {
        DrawableUtils.setRoundBg(this,8, R.color.cl_FFF7F2);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        // 在原有状态中添加一个空间space用于保存checked状态
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            // 将checked状态合并到原有的状态数组中
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
