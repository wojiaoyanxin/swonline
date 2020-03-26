package com.sunlands.intl.yingshi.web;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.Utils;
import com.just.agentweb.AgentWeb;
import com.sunlands.comm_core.utils.rx.exception.RxException;
import com.sunlands.comm_core.utils.rx.rxjava.RxJavaUtils;
import com.sunlands.comm_core.utils.rx.subsciber.BaseSubscriber;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.js.AndroidInterface;

import java.util.concurrent.TimeUnit;

public class WebViewActivity extends MyActivity {

    private AgentWeb agentWeb;
    private RelativeLayout mRv_web_content;


    public static void goActivity(String url, String title) {
        Intent mIntent = new Intent(Utils.getApp(), WebViewActivity.class);
        mIntent.putExtra("url", url);
        mIntent.putExtra("title", title);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ActivityUtils.startActivity(mIntent);
    }


    @Override
    public void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    public void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (agentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        mRv_web_content = findViewById(R.id.rv_web_content);
    }

    @Override
    public void initDataAfterView() {
        String mUrl = getIntent().getStringExtra("url");
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mRv_web_content, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                //  .setWebChromeClient(mWebChromeClient) //WebChromeClient
                .setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        if (tvTitle != null && !TextUtils.isEmpty(view.getTitle())) {
                            addDisposable(RxJavaUtils.delay(500, TimeUnit.MILLISECONDS, new BaseSubscriber<Long>() {
                                @Override
                                public void onError(RxException e) {

                                }

                                @Override
                                public void onSuccess(Long aLong) {
                                    tvTitle.setText(view.getTitle());
                                }
                            }));
                        }
                    }
                })
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()
                .ready()
                .go(mUrl);
        Log.e("TAG0", mUrl);
        if (agentWeb != null) {
            //注入对象
            agentWeb.getJsInterfaceHolder().addJavaObject("android", new AndroidInterface(agentWeb, this));
        }

    }

    protected WebChromeClient mWebChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (tvTitle != null && !TextUtils.isEmpty(title)) {
                if (title.length() > 10) {
                    title = title.substring(0, 10).concat("...");
                }
            }
            tvTitle.setText(title);
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public String getTitleText() {
        return getIntent().getStringExtra("title");
    }

}
