package com.sunlands.intl.yingshi.ui.community.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.dialog.TwoBtTitleDialog;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityPresenter;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.community.view
 * 创 建 人: xueh
 * 创建日期: 2019/4/11 15:44
 * 备注：
 */
public class CommmunitCollectDialog extends BottomSheetDialog implements BaseViewImpl.OnClickListener {
    private Context mContext;
    private TextView mTv_comm_dialog_collect, tv_comm_dialog_delete, tv_comm_dialog_cancel, tv_comm_dialog_inform;
    String mCollect = "";
    int mUserid, mThreadId;
    private Group mGroup_comm_delete;

    private FragmentManager mFragmentManager;
    private CommunityPresenter mPresenter;

    public void setData(String collect, int userid,int threadId ,FragmentManager manager, CommunityPresenter presonter) {
        this.mCollect = collect;
        this.mUserid = userid;
        this.mThreadId=threadId;
        this.mFragmentManager = manager;
        this.mPresenter = presonter;
        updata();
    }

    public CommmunitCollectDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        init();
    }


    public CommmunitCollectDialog(@NonNull Context context, int theme) {
        super(context, theme);
        mContext = context;
        init();
    }

    protected CommmunitCollectDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
        init();
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.dialog_comm_collect_layout, null);
        setContentView(view);

        mTv_comm_dialog_collect = view.findViewById(R.id.tv_comm_dialog_collect);
        mGroup_comm_delete = view.findViewById(R.id.group_comm_delete);
        tv_comm_dialog_delete = view.findViewById(R.id.tv_comm_dialog_delete);
        tv_comm_dialog_cancel = view.findViewById(R.id.tv_comm_dialog_cancel);
        tv_comm_dialog_inform = view.findViewById(R.id.tv_comm_dialog_inform);

        RxBindingHelper.setOnClickListener(mTv_comm_dialog_collect, this);
        RxBindingHelper.setOnClickListener(tv_comm_dialog_delete, this);
        RxBindingHelper.setOnClickListener(tv_comm_dialog_cancel, this);
        RxBindingHelper.setOnClickListener(tv_comm_dialog_inform, this);

    }

    private void updata() {
        //当前用户是否收藏0 否 1是
        if (mCollect.equals("1")) {
            mTv_comm_dialog_collect.setText("取消收藏");
        } else {
            mTv_comm_dialog_collect.setText("收藏");
        }
        if (mUserid == LoginUserInfoHelper.getInstance().getUserInfo().getUserId()) {
            mGroup_comm_delete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == tv_comm_dialog_delete) {
            //删除
            TwoBtTitleDialog dialog = TwoBtTitleDialog.getInstance("提示", "取消", "确定", "确定删除该内容？删除后将无法恢复");
            dialog.setOnRightClick(new TwoBtTitleDialog.onRightClick() {
                @Override
                public void onRight() {
                    mPresenter.delete(mThreadId);
                }
            });
            dialog.show(mFragmentManager, null);
        } else if (v == tv_comm_dialog_inform) {
            //举报
     //       ActivityUtils.startActivity(ReportActivity.class);
//            if (mICollectListener != null) {
//                mICollectListener.onInform();
//            }

            TwoBtTitleDialog dialog = TwoBtTitleDialog.getInstance("提示", "取消", "确定", "确认举报该内容?");
            dialog.setOnRightClick(new TwoBtTitleDialog.onRightClick() {
                @Override
                public void onRight() {
                    mPresenter.report_Submit(mThreadId);
                }
            });
            dialog.show(mFragmentManager, null);

        } else if (v == mTv_comm_dialog_collect) {
            //收藏
            if (mCollect.equals("1")) {
                mPresenter.unCollect(mThreadId);
            } else {
                mPresenter.collect(mThreadId);
            }
        }
        dismiss();
    }
}
