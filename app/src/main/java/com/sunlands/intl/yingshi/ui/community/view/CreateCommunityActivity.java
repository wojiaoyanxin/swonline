package com.sunlands.intl.yingshi.ui.community.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.OnItemClickListener;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.ChannelListBean;
import com.sunlands.intl.yingshi.bean.FriendGroupBean;
import com.sunlands.intl.yingshi.helper.RecyclerItemDecoration;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.adapter.GridImageAdapter;
import com.sunlands.intl.yingshi.ui.community.presenter.CreateMailPresenter;
import com.sunlands.intl.yingshi.util.OtherUtils;
import com.sunlands.intl.yingshi.util.PicHelpter;
import com.xueh.picselect.lib.PicSelector;
import com.xueh.picselect.lib.config.PicConfig;
import com.xueh.picselect.lib.config.PicMimeType;
import com.xueh.picselect.lib.engine.GlideEngine;
import com.xueh.picselect.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class CreateCommunityActivity extends MvpActivity<CreateMailPresenter> implements IMessageContract.CreateMailView {

    private ImageView mIv_title_back;
    private TextView mTv_title_content, mTv_title_right;
    private EditText mEt_create_title;
    private EditText mEt_create_mail_content;
    public static final String toGroupId = "toGroupId";

    private RecyclerView mRecycler_pic;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter mImageAdapter;
    private ConstraintLayout mCl_group;
    private TextView mTv_create_comm_all, mTv_create_comm_kyzq, mTv_create_comm_gjss, mTv_create_comm_look_type;
    private View mView_create_comm_look;
    private int mCurrentChannel;

    @Override
    public void onClick(View v) {
        if (v == mIv_title_back) {
            finish();
        } else if (v == mTv_title_right) {
            if (!TextUtils.isEmpty(CommonUtils.getStrFromView(mEt_create_mail_content))) {
                mTv_title_right.setEnabled(false);
                getPresenter().upload_Files(selectList, CommonUtils.getStrFromView(mEt_create_mail_content), CommonUtils.getStrFromView(mEt_create_title), mFriendGroupBean.getList().get(FriendGroupPos).getId() + "", mCurrentChannel + "");
            } else {
                ToastUtils.showShort("内容不能为空~");
            }
        } else if (v == mTv_create_comm_all) {
            setTvClick(mTv_create_comm_all);
        } else if (v == mTv_create_comm_kyzq) {
            setTvClick(mTv_create_comm_kyzq);
        } else if (v == mTv_create_comm_gjss) {
            setTvClick(mTv_create_comm_gjss);
        } else if (v == mView_create_comm_look) {
            if (mGroupBottomSheetDialog != null) {
                mGroupBottomSheetDialog.show();
            }
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_create_mail;
    }

    @Override
    public void initDataBeforeView() {
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        mIv_title_back = findViewById(R.id.iv_title_back);
        mTv_title_content = findViewById(R.id.tv_title_content);
        mTv_title_right = findViewById(R.id.tv_title_right);
        mCl_group = findViewById(R.id.cl_group);
        mEt_create_title = findViewById(R.id.et_create_title);
        mEt_create_mail_content = findViewById(R.id.et_create_mail_content);
        mRecycler_pic = findViewById(R.id.recycler_pic);

        mTv_create_comm_all = findViewById(R.id.tv_create_comm_all);
        mTv_create_comm_kyzq = findViewById(R.id.tv_create_comm_kyzq);
        mTv_create_comm_gjss = findViewById(R.id.tv_create_comm_gjss);
        mView_create_comm_look = findViewById(R.id.view_create_comm_look);
        mTv_create_comm_look_type = findViewById(R.id.tv_create_comm_look_type);


        mRecycler_pic.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        mRecycler_pic.addItemDecoration(new RecyclerItemDecoration(8, 3));
        mImageAdapter = new GridImageAdapter(getApplicationContext(), new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                showDialog();
            }
        });
        mRecycler_pic.setAdapter(mImageAdapter);

    }

    @Override
    public void initDataAfterView() {
        if (mCl_group.getChildCount() > 0) {
            for (int i = 0; i < mCl_group.getChildCount(); i++) {
                TextView childView = (TextView) mCl_group.getChildAt(i);
                if (i == 0) {
                    OtherUtils.setTvClickableState(childView, 3, R.color.cl_F9E13F, R.color.cl_333333);
                } else {
                    OtherUtils.setTvNoClickableState(childView, 3, R.color.cl_F7F8FA, R.color.cl_999999);
                }
            }
        }
        getPresenter().getChannelList();
        getPresenter().getFriendGroup();
    }

    private void setTvClick(TextView tv) {
        if (mCl_group.getChildCount() > 0) {
            for (int i = 0; i < mCl_group.getChildCount(); i++) {
                TextView childView = (TextView) mCl_group.getChildAt(i);
                if (childView == tv) {
                    mCurrentChannel = (int) tv.getTag();
                    OtherUtils.setTvClickableState(childView, 3, R.color.cl_F9E13F, R.color.cl_333333);
                } else {
                    OtherUtils.setTvNoClickableState(childView, 3, R.color.cl_F7F8FA, R.color.cl_999999);
                }
            }
        }
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        mTv_title_content.setText("新建贴子");
        mTv_title_right.setText("发送");
        mTv_title_right.setVisibility(View.VISIBLE);
        mEt_create_mail_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    mTv_title_right.setTextColor(Color.parseColor("#4A90E2"));
                }else {
                    mTv_title_right.setTextColor(CommonUtils.getColor(R.color.cl_999999));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        RxBindingHelper.setOnClickListener(mTv_title_right, this);
        RxBindingHelper.setOnClickListener(mTv_create_comm_all, this);
        RxBindingHelper.setOnClickListener(mTv_create_comm_kyzq, this);
        RxBindingHelper.setOnClickListener(mTv_create_comm_gjss, this);
        RxBindingHelper.setOnClickListener(mView_create_comm_look, this);
        mImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    PicHelpter.openExternalPreview(position,selectList);
                }
            }
        });
    }

    @Override
    protected CreateMailPresenter createPresenter(IBaseView view) {
        return new CreateMailPresenter(this);
    }

    @Override
    public void onThreadSubmitSuccess() {
        ToastUtils.showShort("发帖成功");
        this.finish();
    }

    @Override
    public void getChannelListSuccess(ChannelListBean channelListBean) {
        if (mCl_group.getChildCount() > 0) {
            for (int i = 0; i < mCl_group.getChildCount(); i++) {
                TextView childView = (TextView) mCl_group.getChildAt(i);
                childView.setText(channelListBean.getList().get(i).getTitle());
                childView.setTag(channelListBean.getList().get(i).getId());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PicConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    images = PicSelector.obtainMultipleResult(data);
                    selectList.addAll(images);
                    mImageAdapter.setList(selectList);
                    mImageAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    BottomSheetDialog mBottomSheetDialog;

    private void showDialog() {
        View sheetDialogView = View.inflate(this, R.layout.dialog_createbottom_layout, null);
        if (mBottomSheetDialog == null) {
            mBottomSheetDialog = new BottomSheetDialog(this, 0);
        }
        RxBindingHelper.setOnClickListener(sheetDialogView.findViewById(R.id.tv_comm_dialog_photo), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相册
                PicSelector.create(ActivityUtils.getTopActivity()).openGallery(PicMimeType.ofImage()).maxSelectNum(9 - selectList.size()).minSelectNum(1).imageSpanCount(4).compress(true).loadImageEngine(GlideEngine.createGlideEngine()).selectionMode(PicConfig.MULTIPLE).forResult(PicConfig.CHOOSE_REQUEST);
                mBottomSheetDialog.dismiss();
            }
        });
        RxBindingHelper.setOnClickListener(sheetDialogView.findViewById(R.id.tv_comm_dialog_take), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拍照
                PicSelector.create(ActivityUtils.getTopActivity()).openCamera(PicMimeType.ofImage()).maxSelectNum(9 - selectList.size()).minSelectNum(1).imageSpanCount(4).compress(true).loadImageEngine(GlideEngine.createGlideEngine()).selectionMode(PicConfig.MULTIPLE).forResult(PicConfig.CHOOSE_REQUEST);
                mBottomSheetDialog.dismiss();
            }
        });
        RxBindingHelper.setOnClickListener(sheetDialogView.findViewById(R.id.tv_comm_dialog_cancel), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.setContentView(sheetDialogView);
        mBottomSheetDialog.show();
    }


    BottomSheetDialog mGroupBottomSheetDialog;
    FriendGroupBean mFriendGroupBean;

    @Override
    public void getFriendGroupSuccess(FriendGroupBean bean) {
        bean.getList().get(FriendGroupPos).isSelcect = true;
        mTv_create_comm_look_type.setText(bean.getList().get(FriendGroupPos).getGroup_name());
        mFriendGroupBean = bean;
        createGroupDialog(bean);
    }

    int FriendGroupPos = 0;

    public void createGroupDialog(FriendGroupBean bean) {
        View sheetDialogView = View.inflate(this, R.layout.dialog_create_comm_group_layout, null);
        RecyclerView rv_group_list = sheetDialogView.findViewById(R.id.rv_group_list);
        RxBindingHelper.setOnClickListener(sheetDialogView.findViewById(R.id.tv_group_ok), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTv_create_comm_look_type.setText(bean.getList().get(FriendGroupPos).getGroup_name());
                if (mGroupBottomSheetDialog != null) {
                    mGroupBottomSheetDialog.dismiss();
                }
            }
        });
        rv_group_list.setLayoutManager(new LinearLayoutManager(this));
        if (mGroupBottomSheetDialog == null) {
            mGroupBottomSheetDialog = new BottomSheetDialog(this, 0);
        }
        mGroupBottomSheetDialog.setCancelable(false);
        CommonAdapter<FriendGroupBean.ListBean> bottomDialogdapter = new CommonAdapter<FriendGroupBean.ListBean>(this, bean.getList(), R.layout.item_create_comm_group_layout) {
            @Override
            public void convert(ViewHolder holder, FriendGroupBean.ListBean listBean) {
                holder.setText(R.id.tv_group_title, listBean.getGroup_name());
                RadioButton rb_group_select = holder.getView(R.id.rb_group_select);
                if (listBean.isSelcect) {
                    rb_group_select.setChecked(true);
                } else {
                    rb_group_select.setChecked(false);
                }
            }
        };
        rv_group_list.setAdapter(bottomDialogdapter);
        bottomDialogdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                for (FriendGroupBean.ListBean listBean : bottomDialogdapter.getDatas()) {
                    listBean.isSelcect = false;
                }
                bottomDialogdapter.getItem(position).isSelcect = true;
                bottomDialogdapter.notifyDataSetChanged();
                FriendGroupPos = position;
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        mGroupBottomSheetDialog.setContentView(sheetDialogView);
    }
}
