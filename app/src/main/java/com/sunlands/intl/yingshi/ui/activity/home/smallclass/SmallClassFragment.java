package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.annotation.SuppressLint;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyFragment;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.SmallClassAdapter;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassBean;

/**
 * @author yxin
 * @date 2019-12-26 - 13:46
 * @des
 */
@SuppressLint("ValidFragment")
public class SmallClassFragment extends MyFragment<SmallClassBean> {

    String type;

    public SmallClassFragment(String type) {
        this.type = type;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_small_class_list;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return new MoreSmallClassListAdapter();
    }


    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataOnNet(mNextPage, type);
    }

    @Override
    public void load() {
        super.load();
        getDataOnNet(mNextPage, type);
    }

    @Override
    public void onSuccess(SmallClassBean bean) {
        super.onSuccess(bean);
        setData(bean.getPaginationList(), bean.getHasMore() == 1);
    }
    @Override
    public void setEmptyView(String tv, int imageId) {
        inflateStateView("暂无课程", R.drawable.no_class);
    }
}
