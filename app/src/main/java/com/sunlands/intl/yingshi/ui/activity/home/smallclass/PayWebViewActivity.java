package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ActivityUtils;
import com.just.agentweb.AgentWeb;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.dialog.DialogUtils;
import com.sunlands.intl.yingshi.ui.my.view.OrderDetailsActivity;

import androidx.annotation.Nullable;

public class PayWebViewActivity extends CommonActivity<Object> {
    private AgentWeb agentWeb;
    private RelativeLayout mRv_web_content;
    public static final String URL = "url";
    public static final String TITLE = "title";
    public static final String COUESEID = "courseId";

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
    protected int getLayoutId() {
        return R.layout.activity_pay_web;
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        mRv_web_content = findViewById(R.id.rv_web_content);
        findViewById(R.id.tv_xieyi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void initDataAfterView() {
        String mUrl = getIntent().getStringExtra(URL);
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mRv_web_content, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setWebViewClient(new WebViewClient() {
                    @Nullable
                    @android.support.annotation.Nullable
                    @Override
                    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                        if (url.contains("payStatus=PAYMENT_SUCCESS")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    DialogUtils.go(PayWebViewActivity.this,null, new DialogUtils.onClick() {
                                        @Override
                                        public void sure() {
                                            SmallClasListActivity.show(PayWebViewActivity.this,
                                                    getIntent().getStringExtra(COUESEID),
                                                    getIntent().getStringExtra(TITLE));
                                            ActivityUtils.finishActivity(SmallClassDetailsActivity.class);
                                            ActivityUtils.finishActivity(OrderDetailsActivity.class);
                                            finish();
                                        }
                                    });
                                }
                            });
                        }
                        return super.shouldInterceptRequest(view, url);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                    }
                })
                .createAgentWeb()
                .ready()
                .go(mUrl);
        Log.e("TAG", mUrl);
    }

    @Override
    public String getTitleText() {
        return "收银台";
    }
}
