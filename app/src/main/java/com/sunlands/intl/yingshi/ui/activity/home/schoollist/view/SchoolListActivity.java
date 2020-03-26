package com.sunlands.intl.yingshi.ui.activity.home.schoollist.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.activity.home.schoollist.adapter.SchoolListAdapter;
import com.sunlands.intl.yingshi.ui.activity.home.schoollist.bean.SchoolBean;
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;
import com.sunlands.intl.yingshi.util.GoH5Utils;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class SchoolListActivity extends CommonActivity<SchoolBean> {

    ImageView ivBackBar;
    TextView tvAll;
    View view1;
    RelativeLayout rlAll;
    TextView tvXuefei;
    View view2;
    LinearLayout llXuefei;
    TextView tvNandu;
    View view3;
    LinearLayout llNandu;
    TextView tvRudu;
    View view4;
    LinearLayout llRedu;
    ConstraintLayout clHeader;

    private SchoolListAdapter mAdapter;
    private List<SchoolBean.AreaListBean> areaList;
    private SchoolBean.AreaListBean index;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        ivBackBar = FBIA(R.id.iv_back_bar);
        tvAll = FBIA(R.id.tv_all);
        view1 = FBIA(R.id.view1);
        rlAll = FBIA(R.id.rl_all);
        tvXuefei = FBIA(R.id.tv_xuefei);
        view2 = FBIA(R.id.view2);
        llXuefei = FBIA(R.id.ll_xuefei);
        tvNandu = FBIA(R.id.tv_nandu);
        view3 = FBIA(R.id.view3);
        llNandu = FBIA(R.id.ll_nandu);
        tvRudu = FBIA(R.id.tv_rudu);
        view4 = FBIA(R.id.view4);
        llRedu = FBIA(R.id.ll_redu);
        clHeader = FBIA(R.id.cl_header);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(llRedu, this);
        RxBindingHelper.setOnClickListener(llNandu, this);
        RxBindingHelper.setOnClickListener(llXuefei, this);
        RxBindingHelper.setOnClickListener(rlAll, this);
    }

    @Override
    public void onSuccess(SchoolBean bean) {
        super.onSuccess(bean);

        areaList = bean.getAreaList();
        index = new SchoolBean.AreaListBean("", "全部");
        if (areaList != null && !areaList.contains(index)) {
            areaList.add(0, index);
        }
        List<SchoolBean.UniversityListBean> universityList = bean.getUniversityList();

        if (isRefresh) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setNewData(universityList);
            mAdapter.loadMoreComplete();
        } else {
            if (!Utils.isEmpty(universityList))
                mAdapter.addData(universityList);
            if (bean.getHasMore() == 1) {
                mAdapter.loadMoreComplete();
            } else {
                mAdapter.loadMoreEnd(false);
            }
        }
        mAdapter.setEnableLoadMore(true);

    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        if (isRefresh) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setEnableLoadMore(false);
        } else {
            mAdapter.loadMoreFail();
            mAdapter.setEnableLoadMore(true);
        }
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        clHeader.setBackgroundColor(Color.parseColor("#252A32"));
        tvTitle.setTextColor(Color.WHITE);
        ivBackBar.setImageResource(R.drawable.back);
        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SchoolListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setEnableLoadMore(false);
        inflateEmptyView(mRecyclerView);
        mTvEmpty.setText(R.string.no_more_content);
        mAdapter.setEmptyView(mEmptyView);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SchoolBean.UniversityListBean item = (SchoolBean.UniversityListBean) adapter.getItem(position);
                String id = item.getUniversity_id();
                GoH5Utils.showSchoolTo(SchoolListActivity.this, id);
            }
        });

    }

    List<String> mOptionsItems = new ArrayList<>();

    private void setWheel() {
        if (areaList == null || areaList.size() <= 0) {
            return;
        }
        mOptionsItems.clear();
        for (int i = 0; i < areaList.size(); i++) {
            mOptionsItems.add(areaList.get(i).getName());
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                String s = areaList.get(options1).getName();
                sort = 0;
                area = areaList.get(options1).getId();
                selectAll();
                tvAll.setText(s);
                getData();
            }
        })
                .setTitleText("选择地区")
                .setTitleSize(16)
                .setContentTextSize(18)
                .build();

        pvOptions.setPicker(mOptionsItems, null, null);
        pvOptions.show();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_school_list;
    }

    @Override
    public String getTitleText() {
        return "院校列表";
    }

    String area = "";
    int sort = 0;

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getData();
    }

    protected void getData() {
        reset(true);
        getDataNet(true, mNextPage, area, sort);
        mSwipeRefreshLayout.setRefreshing(true);
        mAdapter.setEnableLoadMore(false);
    }

    private void loadMore() {
        reset(false);
        getDataNet(false, mNextPage, area, sort);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.rl_all) {
            select(R.id.rl_all);
        } else if (id == R.id.ll_xuefei) {
            select(R.id.ll_xuefei);
        } else if (id == R.id.ll_nandu) {
            select(R.id.ll_nandu);
        } else if (id == R.id.ll_redu) {
            select(R.id.ll_redu);
        }

    }

    private void select(int type) {
        if (type == R.id.rl_all) {
            setWheel();
        } else if (type == R.id.ll_xuefei) {
            selectXueFei();
            sort = 1;
            getData();
        } else if (type == R.id.ll_nandu) {
            selectNanDu();
            sort = 4;
            getData();
        } else if (type == R.id.ll_redu) {
            selectReDu();
            sort = 5;
            getData();
        }
    }

    //选择热度
    private void selectReDu() {

        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
        view4.setVisibility(View.VISIBLE);
        tvAll.setTextColor(Color.parseColor("#333333"));
        tvXuefei.setTextColor(Color.parseColor("#333333"));
        tvNandu.setTextColor(Color.parseColor("#333333"));
        tvRudu.setTextColor(Color.parseColor("#BE9F64"));

    }

    //选择难度
    private void selectNanDu() {
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.VISIBLE);
        view4.setVisibility(View.INVISIBLE);

        tvAll.setTextColor(Color.parseColor("#333333"));
        tvXuefei.setTextColor(Color.parseColor("#333333"));
        tvNandu.setTextColor(Color.parseColor("#BE9F64"));
        tvRudu.setTextColor(Color.parseColor("#333333"));

    }

    //选择费用
    private void selectXueFei() {

        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.VISIBLE);
        view3.setVisibility(View.INVISIBLE);
        view4.setVisibility(View.INVISIBLE);

        tvAll.setTextColor(Color.parseColor("#333333"));
        tvXuefei.setTextColor(Color.parseColor("#BE9F64"));
        tvNandu.setTextColor(Color.parseColor("#333333"));
        tvRudu.setTextColor(Color.parseColor("#333333"));

    }

    //选择地区
    private void selectAll() {

        //先弹出选择器

        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
        view4.setVisibility(View.INVISIBLE);

        tvAll.setTextColor(Color.parseColor("#BE9F64"));
        tvXuefei.setTextColor(Color.parseColor("#333333"));
        tvNandu.setTextColor(Color.parseColor("#333333"));
        tvRudu.setTextColor(Color.parseColor("#333333"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOptionsItems = null;
        areaList = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.cl_252A32).autoDarkModeEnable(true).navigationBarEnable(false).init();
    }
}
