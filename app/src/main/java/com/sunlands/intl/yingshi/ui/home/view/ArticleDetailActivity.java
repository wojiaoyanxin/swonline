package com.sunlands.intl.yingshi.ui.home.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;


public class ArticleDetailActivity extends CommonActivity<Object> {

    ConstraintLayout emptyLayout;
    LinearLayout mLlParent;
    private String mArticleUrl;
    private AgentWeb.PreAgentWeb mPreAgentWeb;
    private AgentWeb mAgentWeb;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        emptyLayout = FBIA(R.id.empty_layout);
        mLlParent = FBIA(R.id.ll_parent);
    }

    public static void show(Context context, String url) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra(Constants.Key.KEY_ARTICLE_URL, url);
        context.startActivity(intent);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        initView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void initView() {
        AgentWebConfig.clearDiskCache(this);
        mPreAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) mLlParent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(CommonUtils.getColor(R.color.yellow_bar))
                .setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        tvTitle.setText(title);
                        if ("详情页".equals(title)) {
                            tvTitle.setText("公告详情");
                        }
                    }
                })
                .createAgentWeb()
                .ready();
        if (!mArticleUrl.contains("http")) {
            mArticleUrl = "https://" + mArticleUrl;
        }
        mAgentWeb = mPreAgentWeb.go(mArticleUrl);

        if (TextUtils.isEmpty(mArticleUrl)
                || "https://".equals(mArticleUrl)
                || mArticleUrl.length() < 10) {
            tvTitle.setText("详情");
            emptyLayout.setVisibility(View.VISIBLE);
        }
        Log.e("TAG", mArticleUrl + "");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    public String getTitleText() {
        return null;
    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
        mArticleUrl = getIntent().getStringExtra(Constants.Key.KEY_ARTICLE_URL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onResume();
    }
}
