package com.sunlands.intl.yingshi.ui.community.other;

import android.support.v4.util.SparseArrayCompat;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.community.other
 * 创 建 人: xueh
 * 创建日期: 2019/4/9 15:10
 * 备注：
 */
public class Pool<T> {
    private SparseArrayCompat<T> mPool;
    private New<T> mNewInstance;

    public Pool(New<T> newInstance) {
        mPool = new SparseArrayCompat<>();
        mNewInstance = newInstance;
    }

    public T get(int key) {
        T res = mPool.get(key);
        if (res == null) {
            res = mNewInstance.get();
            mPool.put(key, res);
        }
        return res;
    }

    public interface New<T> {
        T get();
    }
}
