package com.sunlands.intl.yingshi.base;

import android.content.Context;
import android.text.TextUtils;

import com.sunlands.intl.yingshi.constant.Constants;

import java.lang.ref.WeakReference;


public abstract class BasePresenter<T extends IBaseView> {
    protected Context mContext;
    private WeakReference<T> mViews;
    protected int mNextPage = Constants.DEFAULT_NEXT_PAGE;


    public BasePresenter(Context context, T view) {
        mContext = context;
        mViews = new WeakReference<T>(view);
    }


    public BasePresenter() {
    }

    public long getCurrentTime() {
        java.util.Date date = new java.util.Date();
        long datetime = date.getTime();
        return datetime;
    }

    protected boolean isNetError(String msg) {
        return !TextUtils.isEmpty(msg) && (msg.contains("UnknownHostException")
                || msg.contains("ConnectException")
                || msg.contains("java.net.TimeoutException"));
    }


    public void attachView(T view) {
        mViews = new WeakReference<T>(view);
    }

    public T getView() {
        return isViewAttached() ? mViews.get() : null;
    }

    public boolean isViewAttached() {
        return null != mViews && null != mViews.get();
    }

    public void detachView() {
        if (null != mViews) {
            mViews.clear();
            mViews = null;
        }
    }

    public int getNextPageIndex() {
        return mNextPage;
    }

}