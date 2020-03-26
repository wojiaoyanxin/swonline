package com.sunlands.intl.yingshi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.czt.mp3recorder.MP3Recorder;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.sunlands.comm_core.helper.EventBusHelper;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.FileBean;
import com.sunlands.intl.yingshi.bean.event.EventMessage;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.js.AndroidInterface;
import com.sunlands.intl.yingshi.util.FileUtils;
import com.sunlands.intl.yingshi.util.StringFormatUtils;
import com.sunlands.intl.yingshi.util.StringUtils;
import com.sunlands.intl.yingshi.util.Utils;

import java.io.File;
import java.io.IOException;

/**
 * H5做题页面
 */

public class DoHomeworkOrQuizActivity extends CommonActivity<FileBean> {


    LinearLayout mLlParent;

    private String mH5Url;
    private AgentWeb.PreAgentWeb mPreAgentWeb;
    private AgentWeb mAgentWeb;
    private int mCourseId;
    private int mType;
    private int mAction;
    private String from;
    private String mid;
    private MP3Recorder mr;
    private File file;
    private String mExamId;

    public static void showExam(Context context, int type, int courseId, int action, String quiz) {
        Intent intent = new Intent(context, DoHomeworkOrQuizActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.Key.KEY_COURSE_ID, courseId);
        intent.putExtra(Constants.Key.KEY_TYPE, type);
        intent.putExtra(Constants.Key.KEY_ACTION, action);
        intent.putExtra(Constants.Key.EXAM_ID, quiz);
        context.startActivity(intent);
    }

    public static void showSchool(Context context, String school, int university_id) {
        Intent intent = new Intent(context, DoHomeworkOrQuizActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.Key.KEY_COURSE_ID, university_id);
        intent.putExtra(Constants.Key.KEY_FROM, school);
        context.startActivity(intent);
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        mLlParent = FBIA(R.id.ll_parent);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mCourseId = getIntent().getIntExtra(Constants.Key.KEY_COURSE_ID, -1);
        mExamId = getIntent().getStringExtra(Constants.Key.EXAM_ID);
        mType = getIntent().getIntExtra(Constants.Key.KEY_TYPE, -1);
        from = getIntent().getStringExtra(Constants.Key.KEY_FROM);
        AgentWebConfig.clearDiskCache(this);
        mPreAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) mLlParent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(Color.parseColor("#ffefb63f"))
                .setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        Log.e("TAG", "加载中---" + url);
                        return super.shouldOverrideUrlLoading(view, url);
                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        Log.e("TAG", "加载中");
                        return super.shouldOverrideUrlLoading(view, request);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        Log.e("TAG", "加载完成" + url);
                        initJs();
                    }
                })
                .createAgentWeb()
                .ready();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_do_homework_or_quiz;
    }

    @Override
    public String getTitleText() {
        mAction = getIntent().getIntExtra(Constants.Key.KEY_ACTION, -1);
        if (mAction == 0) {
            return "答题";
        } else if (mAction == 1) {
            return "作业解析";
        }
        return "";
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getData();
    }

    protected void getData() {
        if (mAction != -1) { //考试安排 和 随堂考
            mH5Url = StringFormatUtils.getExamUrl(mType, mCourseId, mAction, mExamId);
        }
        if ("school".equals(from)) { //院校列表
            mH5Url = StringFormatUtils.getSchoolUrl(mCourseId);
        }
        if (!StringUtils.isEmpty(mH5Url)) mAgentWeb = mPreAgentWeb.go(mH5Url);
        Log.e("url", mH5Url + "");
        initJs();
    }

    public void initJs() {

        if (mAgentWeb != null) {
            //注入对象
            mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new AndroidInterface(mAgentWeb, this, new AndroidInterface.JSCallback() {
                @Override
                public void H5ToNativeCloseWebview() {
                    onBackPressed();
                }

                @Override
                public void H5ToNativeStartRecord(String id, String max) { //开始录音

                    if (mr != null && mr.isRecording()) {
                        ToastUtils.showShort("正在录制");
                        return;
                    }

                    mid = id;
                    String mFilePath = Constants.RECORD_FILE + File.separator + System.currentTimeMillis() + ".mp3";
                    FileUtils.createFile(mFilePath);
                    file = new File(mFilePath);
                    mr = new MP3Recorder(file);
                    try {
                        mr.start();
                        //启动录音  通知 H5 端
                        mAgentWeb.getJsAccessEntrace().quickCallJs("NativeToH5StartTimer('" + id + "')");
//                                String method = "NativeToH5StartTimer";
//                                String s = "questionId:" + id;
//                                String param = "{" + s + "}";
//                                Log.e("TAG", param);
//                                mAgentWeb.getJsAccessEntrace().quickCallJs("NativeCallWeb('" + method + "','" + param + "')");
                        //开始计时
                        currentTime = 0;
                        Utils.getMainThreadHandler().removeCallbacks(mCurrentTime);
                        Utils.getMainThreadHandler().removeCallbacksAndMessages(null);
                        Utils.getMainThreadHandler().post(mCurrentTime);
                        Utils.getMainThreadHandler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                mAgentWeb.getJsAccessEntrace().quickCallJs("NativeToH5RecordTimeOut('" + mid + "')");
                                H5ToNativeSubmit(id);
                            }
                        }, Integer.parseInt(max) * 1000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void H5ToNativeSubmit(String id) { // 提交
                    Utils.getMainThreadHandler().removeCallbacks(mCurrentTime);
                    Utils.getMainThreadHandler().removeCallbacksAndMessages(null);
                    mr.stop();
                    //生成文件等待中
                    showLoading();
                    Utils.getMainThreadHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getDataNet(false, file, currentTime);
                        }
                    }, 500);
                }

                @Override
                public void NativeToH5ShowLoading(String id) {
                    showLoading();
                }

                @Override
                public void NativeToH5HideLoading(String id) {
                    hideLoading();
                }
            }));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusHelper.post(new EventMessage(EventMessage.EVENT_UNMUTE));
        AgentWebConfig.clearDiskCache(this);
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onDestroy();
        if (mr != null) {
            Utils.getMainThreadHandler().removeCallbacksAndMessages(null);
            Utils.getMainThreadHandler().removeCallbacks(mCurrentTime);
            currentTime = 0;
            mr.stop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBusHelper.post(new EventMessage(EventMessage.EVENT_MUTE));
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mAgentWeb.getJsAccessEntrace().quickCallJs("NativeToH5WebviewHide()");
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onPause();
    }


    @Override
    public void onSuccess(FileBean bean) {
        super.onSuccess(bean);
        if (bean != null) {
            //   ToastUtils.showShort("上传文件成功,通知H5,调用NativeToH5SubmitSuccess");
            //   mAgentWeb.getJsAccessEntrace().quickCallJs("NativeToH5SubmitSuccess('" + mid + "','" + bean.getUrl() + "','" + currentTime + "')");
            mAgentWeb.getJsAccessEntrace().quickCallJs("NativeToH5SubmitSuccess('" + mid + "','" + bean.getUrl() + "')");
        }
    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        //   ToastUtils.showShort("上传文件失败,通知H5,调用NativeToH5SubmitFailed");
        mAgentWeb.getJsAccessEntrace().quickCallJs("NativeToH5SubmitFailed('" + mid + "','" + model.getMsg() + "','" + model.getCode() + "')");
    }

    int currentTime = 0;

    Runnable mCurrentTime = new Runnable() {
        @Override
        public void run() {
            currentTime++;
            Utils.getMainThreadHandler().postDelayed(mCurrentTime, 1000);
        }
    };

}
