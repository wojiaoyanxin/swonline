package com.sunlands.intl.yingshi.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.shangde.lepai.uilib.view.text.QMUITouchableSpan;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.helper.BaseDialogHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.web.WebViewActivity;

/**
 * 当前包名: com.sunlands.intl.yingshi.dialog
 * 创 建 人: xueh
 * 创建日期: 2019/3/18 11:16
 * 备注：
 */
public class TwoBtTitleDialog extends BaseDialogHelper implements BaseViewImpl.OnClickListener {
    private static String strLeft = "", strRight = "", centerContent = "", Strtitle = "";

    public static TwoBtTitleDialog getInstance(String title, String left, String right, String content) {
        TwoBtTitleDialog dialogFragment = new TwoBtTitleDialog();
        dialogFragment.setCanceledBack(true);
        dialogFragment.setCanceledOnTouchOutside(false);
        dialogFragment.setGravity(Gravity.CENTER);
        strLeft = left;
        strRight = right;
        Strtitle = title;
        centerContent = content;
        return dialogFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_two_button_title_layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        TextView tv_left = view.findViewById(R.id.tv_left);
        TextView tv_right = view.findViewById(R.id.tv_right);
        TextView tv_content = view.findViewById(R.id.tv_content);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_content.setText(centerContent);
        tv_left.setText(strLeft);
        tv_right.setText(strRight);
        tv_title.setText(Strtitle);
        RxBindingHelper.setOnClickListener(tv_left, this);
        RxBindingHelper.setOnClickListener(tv_right, this);

        if (!TextUtils.isEmpty(centerContent)) {
            return;
        }

        String a = "请你务必审慎阅读、充分理解\"服务协" +
                "议\"和“隐私政策\"各条款，包括但不限于:" +
                "为了向你提供资料下载、内容分享等服务" +
                "我们需要收集你的设备信息、操作日志等个" +
                "人信息。你可以在设置\"中查看、变更、删" +
                "除个人信息并管理你的授权。" +
                "你可阅读";
        String b = "《服务协议》";
        String c = "和";
        String d = "《隐私政策》";
        String e = "了解详细信息。如你同意，请点击\"同意\"开始接受我们的服务。";

        SpanUtils.with(tv_content)
                .append(a)
                .append(b)
                .setForegroundColor(CommonUtils.getColor(R.color.cl_4A90E2))
                .setClickSpan(new QMUITouchableSpan(CommonUtils.getColor(R.color.cl_4A90E2), CommonUtils.getColor(R.color.cl_4A90E2), 0, 0) {
                    @Override
                    public void onSpanClick(View widget) {
                        WebViewActivity.goActivity(RestApi.PROTOCAL, "");
                    }
                })
                .append(c)
                .append(d)
                .setForegroundColor(CommonUtils.getColor(R.color.cl_4A90E2))
                .setClickSpan(new QMUITouchableSpan(CommonUtils.getColor(R.color.cl_4A90E2), CommonUtils.getColor(R.color.cl_4A90E2), 0, 0) {
                    @Override
                    public void onSpanClick(View widget) {
                        WebViewActivity.goActivity(RestApi.PRIVACY, "");
                    }
                })
                .append(e)
                .create();


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
            if (mOnRightClick != null) {
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
