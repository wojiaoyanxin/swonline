package com.sunlands.intl.yingshi.common;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.shangde.lepai.uilib.indicator.MagicIndicator;
import com.shangde.lepai.uilib.statusbar.StatusBarHelper;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.intl.yingshi.R;


/**
 * Created by yanxin on 2019/5/15.
 */

public abstract class CommonActivity<T> extends MvpActivity<CommonPresenter<T>> implements CommonContract.ICommonView<T>, BaseViewImpl.OnClickListener {


    protected boolean mCheckNetwork = true;/*默认检查网络状态*/
    protected boolean mNetConnected;/*网络连接的状态，true表示有网络，flase表示无网络连接*/
    protected View mEmptyView;
    protected TextView mTvEmpty;
    protected ImageView ivEmpty;
    public RelativeLayout vgBack;
    public TextView tvTitle;
    public  MagicIndicator magicIndicator;
    public ViewPager mViewPager;
    public RecyclerView mRecyclerView;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public boolean isRefresh = true;

    public int mNextPage = 1;


    public void reset(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (!isRefresh) {
            mNextPage++;
        } else {
            mNextPage = 1;
        }
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {

        tvTitle = FBIA(R.id.tv_title);
        vgBack = FBIA(R.id.vg_back);
        magicIndicator = FBIA(R.id.magic_indicator);
        mViewPager = FBIA(R.id.view_pager);
        mRecyclerView = FBIA(R.id.recycler_view);
        mSwipeRefreshLayout = FBIA(R.id.swipe_refresh_layout);

    }

    protected void inflateEmptyView(ViewGroup viewGroup) {
        mEmptyView = getLayoutInflater().inflate(R.layout.layout_empty, viewGroup, false);
        mTvEmpty = (TextView) mEmptyView.findViewById(R.id.tv_empty);
        ivEmpty = (ImageView) mEmptyView.findViewById(R.id.iv_empty);
    }

    @Override
    public int getCreateViewLayoutId() {
        return getLayoutId();
    }

    public void onSuccess(T bean) {

    }


    protected abstract int getLayoutId();


    @Override
    public void initDataAfterView() {

    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        mImmersionBar = ImmersionBar.with(this);
        if(tvTitle!=null) {
            tvTitle.setText(getTitleText());
        }
    }

    public abstract String getTitleText() ;

    @Override
    public void initListener() {

        if (vgBack != null) {
            RxBindingHelper.setOnClickListener(vgBack, this);
        }
    }


    @Override
    public void getDataSuccess(T bean) {

        onSuccess(bean);
    }

    @Override
    public void getDataFailed(BaseModel model) {

        onFailed(model);

    }

    public void onFailed(BaseModel model) {


    }


    @Override
    protected CommonPresenter<T> createPresenter(IBaseView view) {
        return new CommonPresenter<T>(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.vg_back) {
            onBackPressed();
        }
    }

    public void getDataNet(Boolean isShow, Object... args) {
        getPresenter().getDataFromNet(getClass().getName(), isShow, args);
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            //  | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
            StatusBarHelper.setStatusBarLightMode(this);
        }
    }
}
