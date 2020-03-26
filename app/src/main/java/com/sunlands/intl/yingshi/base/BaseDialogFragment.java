package com.sunlands.intl.yingshi.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ScreenUtils;
import com.sunlands.intl.yingshi.R;


/**
 * 文件名: BaseDialogFragment
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/6
 */
public abstract class BaseDialogFragment extends DialogFragment {

    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initStyle();
    }

    protected void initStyle() {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_default);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initDialog();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view,savedInstanceState);

    }

    protected abstract void initView(View view, Bundle savedInstanceState);


    protected void initDialog() {
        Dialog dialog = getDialog();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        //设置dialog的背景颜色，我们可以直接在XML中给dialog加一个背景色，如果不设置背景颜色就会造成背景是透明的
        //window.setBackgroundDrawable(new ColorDrawable(0x00000000));
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得窗体的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        //设置Dialog宽度匹配屏幕宽度
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置Dialog高度自适应
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;//会导致状态栏变黑
        lp.height = ScreenUtils.getScreenHeight();
        //将属性设置给窗体
        window.setAttributes(lp);
    }

    public abstract int getLayoutId();

}
