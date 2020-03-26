package com.sunlands.intl.yingshi.ui.community.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.sunlands.comm_core.base.DActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.OnItemClick;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.comm_core.statemanager.StateLayout;
import com.sunlands.comm_core.statemanager.state.CoreState;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.comm_core.weight.ViewLoading;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.helper.state.NoSearchDataState;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.adapter.ThreadPaginationAdapter;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityPresenter;
import com.sunlands.intl.yingshi.util.SPHelper;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.subjects.PublishSubject;

public class FindCommunityActivity extends DActivity implements LifecycleObserver, IMessageContract.MailView {
    private TextView mIv_find_comm_back;
    private EditText et_find;
    private CommonAdapter<String> mCommonAdapter;
    public final PublishSubject<Lifecycle.Event> lifecycleSubject = PublishSubject.create();
    private CommunityPresenter mCommunityPresenter;
    private StateLayout mSl_find_comm;
    public int limit = 20;
    private TextView mTv_find_comm_delete_all;
    private RecyclerView mRv_find_comm_list;

    @Override
    public void onClick(View v) {
        if (v == mIv_find_comm_back) {
            finish();
        } else if (v == mTv_find_comm_delete_all) {
            SPUtils.getInstance().put("FindComm", "");
            updata();
        } else if (et_find == v) {
//            updata();
        }

    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_find_community;
    }

    @Override
    public void initDataBeforeView() {
        mCommunityPresenter = new CommunityPresenter(this);
    }

    ThreadPaginationAdapter mPaginationAdapter;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        mIv_find_comm_back = FBIA(R.id.iv_find_comm_back);
        et_find = FBIA(R.id.et_find);
        mRv_find_comm_list = FBIA(R.id.rv_find_comm_list);
        RecyclerView rv_find_comm_content = FBIA(R.id.rv_find_comm_content);
        mSl_find_comm = FBIA(R.id.sl_find_comm);
        mTv_find_comm_delete_all = findViewById(R.id.tv_find_comm_delete_all);
        rv_find_comm_content.setLayoutManager(new LinearLayoutManager(this));
        mRv_find_comm_list.setLayoutManager(new LinearLayoutManager(this));

        mCommonAdapter = new CommonAdapter<String>(this, new ArrayList<>(Arrays.asList(SPHelper.getFindComm().split("#"))), R.layout.item_find_comm_layout) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.tv_find_comm, s);
                holder.setOnClickListener(R.id.iv_find_comm_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SPHelper.deleteFindComm(getDatas().get(holder.getLayoutPosition()));
                        getDatas().remove(holder.getLayoutPosition());
                        updata();
                    }
                });
            }
        };
        mRv_find_comm_list.setAdapter(mCommonAdapter);
        updata();
        mPaginationAdapter = new ThreadPaginationAdapter(this, null, getSupportFragmentManager(), mCommunityPresenter);
        rv_find_comm_content.setAdapter(mPaginationAdapter);
    }

    private void updata() {
        if (mCommonAdapter != null) {
            mCommonAdapter.notifyDataSetChanged();
        }
        if (TextUtils.isEmpty(SPHelper.getFindComm())) {
            mRv_find_comm_list.setVisibility(View.GONE);
            mTv_find_comm_delete_all.setVisibility(View.GONE);
        } else {
            mRv_find_comm_list.setVisibility(View.VISIBLE);
            mTv_find_comm_delete_all.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initDataAfterView() {

    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        getLifecycle().addObserver(this);//添加LifecycleObserver
        et_find.setHint("输入您想搜索的内容标题");
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mTv_find_comm_delete_all, this);
        RxBindingHelper.setOnClickListener(mIv_find_comm_back, this);
        RxBindingHelper.setOnClickListener(et_find, this);
        mCommonAdapter.setOnItemClickListener(new OnItemClick() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                mCommunityPresenter.getPagination(limit, "", mCommonAdapter.getDatas().get(position));
                mRv_find_comm_list.setVisibility(View.GONE);
                mTv_find_comm_delete_all.setVisibility(View.GONE);
            }
        });

        et_find.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    SPHelper.setFindComm(CommonUtils.getStrFromView(et_find));
                    mCommunityPresenter.getPagination(limit, "", CommonUtils.getStrFromView(et_find));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(Lifecycle.Event.ON_DESTROY);
        mCommunityPresenter.onDestroy(this);
        super.onDestroy();
        getLifecycle().removeObserver(this);
    }


    @Override
    public void getPaginationSuccess(MainlBean mMainlBean) {
        if (mMainlBean.getList().size() == 0) {
            mSl_find_comm.showState(NoSearchDataState.STATE);
            mRv_find_comm_list.setVisibility(View.GONE);
        } else {
            mSl_find_comm.showState(CoreState.STATE);
            mPaginationAdapter.setDatas(mMainlBean.getList());
        }
    }

    //刷新
    @Override
    public void onPostSubmitSuccess(String type) {
        mCommunityPresenter.getPagination(limit, "", CommonUtils.getStrFromView(et_find));
    }


    @Override
    public PublishSubject<Lifecycle.Event> getLifecycleSubject() {
        return lifecycleSubject;
    }

    @Override
    public void showLoading() {
        ViewLoading.show(this);
    }

    @Override
    public void hideLoading() {
        ViewLoading.dismiss(this);
    }
}
