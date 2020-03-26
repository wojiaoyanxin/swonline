package com.sunlands.intl.yingshi.ui.community.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.mul.BaseMulTypeAdapter;
import com.sunlands.comm_core.rvadapter.mul.IMulTypeHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MsgListBean;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.presenter.MessageCorePresenter;

import java.util.Vector;

/**
 * 通讯录
 */
public class FriendListActivity extends MvpActivity<MessageCorePresenter> implements BaseViewImpl.OnClickListener, IMessageContract.View {

    private ImageView iv_title_back;
    private TextView mTv_title_content;
    private RecyclerView mRv_message;
    private BaseMulTypeAdapter mMulTypeAdapter;

    @Override
    protected MessageCorePresenter createPresenter(IBaseView view) {
        return new MessageCorePresenter(this);
    }

    @Override
    public void onClick(View v) {
        if (v == iv_title_back) {
            this.finish();
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_message_core;
    }

    @Override
    public void initDataBeforeView() {
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        iv_title_back = findViewById(R.id.iv_title_back);
        mTv_title_content = findViewById(R.id.tv_title_content);
        mRv_message = findViewById(R.id.rv_message);

    }

    @Override
    public void initDataAfterView() {
        getPresenter().getMsgList();
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        mTv_title_content.setText("通讯录");
        mRv_message.setLayoutManager(new LinearLayoutManager(ApplicationsHelper.context()));
        mMulTypeAdapter = new BaseMulTypeAdapter(this, mulTypeHelpers);
        mRv_message.setAdapter(mMulTypeAdapter);
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(iv_title_back, this);
        RxBindingHelper.setOnClickListener(mTv_title_content, this);
    }

    Vector<IMulTypeHelper> mulTypeHelpers = new Vector<>();

    @Override
    public void onMsgListSuccess(MsgListBean mListBean) {
        mulTypeHelpers.clear();
        mulTypeHelpers.add(new MsgListBean.titleBean("班级好友"));
        for (MsgListBean.ListBean.StudentBean studentBean : mListBean.getList().getStudent()) {
            mulTypeHelpers.add(studentBean);
        }
        mulTypeHelpers.add(new MsgListBean.titleBean("老师"));
        for (MsgListBean.ListBean.TeacherBean teacherBean : mListBean.getList().getTeacher()) {
            mulTypeHelpers.add(teacherBean);
        }
        mMulTypeAdapter.notifyDataSetChanged();
        mRv_message.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRv_message.smoothScrollToPosition(0);
            }
        },200);
    }
}

