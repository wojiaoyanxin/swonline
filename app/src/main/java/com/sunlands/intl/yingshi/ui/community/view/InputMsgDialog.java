package com.sunlands.intl.yingshi.ui.community.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.helper.BaseDialogHelper;
import com.sunlands.intl.yingshi.R;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.view
 * 创 建 人: xueh
 * 创建日期: 2019/3/8 15:36
 * 备注：
 */
public class InputMsgDialog extends BaseDialogHelper {
    EditText inputEdit;
    ImageView sendContent;

    @Override
    public int getLayoutId() {
        return R.layout.dialog_input_mail_layout;
    }

    interface SendMsgCallBack {
        void sendMsgCallBack(String sendMsgContent);

        void onDissMissDialog(String sendMsgContent);
    }

    private SendMsgCallBack sendMsgCallBack;

    public void setOnSenCallback(SendMsgCallBack callback) {
        this.sendMsgCallBack = callback;
    }

    public static InputMsgDialog getInstance() {
        InputMsgDialog dialogFragment = new InputMsgDialog();
        dialogFragment.setCanceledBack(true);
        dialogFragment.setCanceledOnTouchOutside(true);
        dialogFragment.setGravity(Gravity.BOTTOM);
        return dialogFragment;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        KeyboardUtils.unregisterSoftInputChangedListener(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        inputEdit = getView().findViewById(R.id.dialog_et_mail_input_content);
        sendContent = getView().findViewById(R.id.dialog_iv_mail_content_send);
        inputEdit.addTextChangedListener(new ContentWatcher());

        sendContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //执行回调
                if (sendMsgCallBack != null) {
//                    if (CommonUtils.containsEmoji(inputEdit.getText().toString())) {
//                        ToastUtils.showShort("不能发送系统表情哦");
//                    } else {
                    if (TextUtils.isEmpty(inputEdit.getText().toString().trim())) {
                        ToastUtils.showShort("输入为空...请重新输入");
                        return;
                    }
                    sendMsgCallBack.sendMsgCallBack(inputEdit.getText().toString());
                    inputEdit.setText("");
                    InputMethodManager imm =
                            (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(inputEdit.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    dismiss();
//                    }
                }
            }
        });
        if (getDialog().getWindow() != null) {
            KeyboardUtils.registerSoftInputChangedListener(getActivity(), new KeyboardUtils.OnSoftInputChangedListener() {
                @Override
                public void onSoftInputChanged(int height) {
                    if (!KeyboardUtils.isSoftInputVisible(getActivity())) {
                        dismiss();
                    }
                }
            });
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            getDialog().getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int[] viewLocation = new int[2];
                    view.getLocationOnScreen(viewLocation);
                    if (motionEvent.getY() < viewLocation[1]) {
                        InputMethodManager imm =
                                (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(inputEdit.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                        if (sendMsgCallBack != null) {
                            sendMsgCallBack.onDissMissDialog(inputEdit.getText().toString().trim());
                            InputMsgDialog.this.dismiss();
                        }
                        dismiss();
                    }
                    return false;
                }
            });
        }
    }

    //监听是否有内容，让发送按钮可以使用
    class ContentWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() > 0) {
                if (sendContent != null) {
                    sendContent.setEnabled(true);
                }
            } else {
                if (sendContent != null) {
                    sendContent.setEnabled(false);
                }
            }
        }
    }


}
