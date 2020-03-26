package com.sunlands.intl.yingshi.ui.home.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyFragment;
import com.sunlands.intl.yingshi.ui.home.adapter.HomeClassListAdapter;

import java.util.Arrays;

/**
 * @author yxin
 * @date 2020-03-12 - 14:42
 * @des
 */
public class HomeClassListFragment extends MyFragment<Object> {


    public HomeClassListFragment() {

    }

    public static HomeClassListFragment newInstance() {
        return new HomeClassListFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_class_list;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return new HomeClassListAdapter();
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.fragment_home_class_header,
                (ViewGroup) mRecyclerView.getParent(), false);
        RecyclerView rvHomeHeader = view.findViewById(R.id.rv_home_header);
        TextView tvAllClass = view.findViewById(R.id.tv_all_class);
        tvAllClass.setText("全部课程(" + "" + ")");
        View tvDownload = view.findViewById(R.id.tv_download);
        tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rvHomeHeader.setLayoutManager(new LinearLayoutManager(mContext));
        rvHomeHeader.setAdapter(new CommonAdapter(mContext, Arrays.asList("", "", ""), R.layout.item_fragment_home_class_header) {
            @Override
            public void convert(ViewHolder holder, Object o) {

            }
        });
        baseQuickAdapter.addHeaderView(view);
        setData(Arrays.asList("", "", "", "", "", "", "", ""), false);
    }
}
