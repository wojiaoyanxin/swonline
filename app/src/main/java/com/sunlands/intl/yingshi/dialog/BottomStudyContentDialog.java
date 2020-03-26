package com.sunlands.intl.yingshi.dialog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.sunlands.comm_core.helper.BaseDialogHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.comm_core.utils.DrawableUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.Arrays;
import java.util.List;

public class BottomStudyContentDialog extends BaseDialogHelper {

    private static List<Object> mData;

    public static BottomStudyContentDialog getInstance(List<Object> listBean) {
        mData = listBean;
        BottomStudyContentDialog dialogFragment = new BottomStudyContentDialog();
        dialogFragment.setCanceledBack(true);
        dialogFragment.setCanceledOnTouchOutside(true);
        dialogFragment.setGravity(Gravity.BOTTOM);
        return dialogFragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.dialog_bottom_study_content_layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if (getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.windowAnimations = R.style.BottomToTopAnim;
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mData = null;
            }
        });
        RecyclerView recycle_view = view.findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new LinearLayoutManager(Utils.getApp()));
        recycle_view.setAdapter(new CommonAdapter(Utils.getApp(), Arrays.asList("哈哈2222", "哈哈", "妈妈们"), R.layout.item_study_content_layout) {
            @Override
            public void convert(ViewHolder holder, Object o) {

                if (holder.getPosition() == 1) {
                    DrawableUtils.setRoundBg(holder.getView(R.id.tv_lianxi), 16, R.color.cl_FF7224);
                    DrawableUtils.setRoundLineBg(holder.getView(R.id.tv_tingke), 16, R.color.cl_FF7224, R.color.cl_FF7224);
                }

            }

        });
    }

    private void setX(ViewHolder holder, int count) {

        holder.setImageResource(R.id.iv_x1, R.drawable.ic_huixing);
        holder.setImageResource(R.id.iv_x2, R.drawable.ic_huixing);
        holder.setImageResource(R.id.iv_x3, R.drawable.ic_huixing);
        holder.setImageResource(R.id.iv_x4, R.drawable.ic_huixing);

        if (count == 1) {
            holder.setImageResource(R.id.iv_x1, R.drawable.ic_liangxing);
        } else if (count == 2) {
            holder.setImageResource(R.id.iv_x1, R.drawable.ic_liangxing);
            holder.setImageResource(R.id.iv_x2, R.drawable.ic_liangxing);
        } else if (count == 3) {
            holder.setImageResource(R.id.iv_x1, R.drawable.ic_liangxing);
            holder.setImageResource(R.id.iv_x2, R.drawable.ic_liangxing);
            holder.setImageResource(R.id.iv_x3, R.drawable.ic_liangxing);
        } else if (count == 4) {
            holder.setImageResource(R.id.iv_x1, R.drawable.ic_liangxing);
            holder.setImageResource(R.id.iv_x2, R.drawable.ic_liangxing);
            holder.setImageResource(R.id.iv_x3, R.drawable.ic_liangxing);
            holder.setImageResource(R.id.iv_x4, R.drawable.ic_liangxing);
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }
}
