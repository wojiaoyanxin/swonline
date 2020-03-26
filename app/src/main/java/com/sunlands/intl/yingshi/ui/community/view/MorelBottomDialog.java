package com.sunlands.intl.yingshi.ui.community.view;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.shangde.lepai.uilib.view.RoundImageView;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.OnItemClickListener;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.PaginationBean;
import com.sunlands.intl.yingshi.bean.ThreadInfoBean;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityContentPresenter;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.OtherUtils;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;


/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.view
 * 创 建 人: xueh
 * 创建日期: 2019/3/8 13:20
 * 备注：
 */
public class MorelBottomDialog extends android.support.design.widget.BottomSheetDialogFragment implements IMessageContract.MailContentView, BaseViewImpl.OnClickListener {
    PaginationBean.ListBean fromListBean;
    private CommunityContentPresenter mMailContentPresenter;

    private RecyclerView mDialog_rv_mail_comment;
    private TextView mDialog_et_mail_input_content;
    //状态
    BottomSheetBehavior behavior;
    private InputMsgDialog mInputMsgDialog;
    private ImageView mDialog_iv_mail_content_send;

    public void setData(PaginationBean.ListBean listBean) {
        this.fromListBean = listBean;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_mail_more_layout, container, false);
        //控制弹出视图高度
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (600 * getContext().getResources().getDisplayMetrics().density));
        dialogView.setLayoutParams(params);
        //设置键盘弹出模式
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        if (getDialog().getWindow() != null)
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        initView(dialogView);
        setCancelable(false);
        return dialogView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //默认完全弹出
        if (getView() != null) {
            View view = (View) getView().getParent();
            behavior = BottomSheetBehavior.from(view);
            behavior.setPeekHeight((int) (600 * getContext().getResources().getDisplayMetrics().density));
//            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//                @Override
//                public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                    //禁止拖拽，
//                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
//                        //设置为收缩状态
//                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                    }
//                }
//
//                @Override
//                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//                }
//            });
        }
    }

    private void initView(View sheetDialogView) {
        mInputMsgDialog = InputMsgDialog.getInstance();
        mMailContentPresenter = new CommunityContentPresenter(this);
        //请求更多评论内容
        mMailContentPresenter.getPagination(0, 200, fromListBean.getPost().getId(), fromListBean.getPost().getThread_id());

        RoundImageView dialog_riv_mail_userhead = sheetDialogView.findViewById(R.id.dialog_riv_mail_userhead);
        mDialog_et_mail_input_content = sheetDialogView.findViewById(R.id.dialog_et_mail_input_content);
        TextView dialog_tv_mail_username = sheetDialogView.findViewById(R.id.dialog_tv_mail_username);
        TextView dialog_tv_mail_more_content_time = sheetDialogView.findViewById(R.id.dialog_tv_mail_more_content_time);
        TextView dialog_tv_mail_content = sheetDialogView.findViewById(R.id.dialog_tv_mail_content);
        mDialog_rv_mail_comment = sheetDialogView.findViewById(R.id.dialog_rv_mail_comment);
        mDialog_iv_mail_content_send = sheetDialogView.findViewById(R.id.dialog_iv_mail_content_send);
        dialog_tv_mail_content.setText(fromListBean.getPost().getContent());
        dialog_tv_mail_more_content_time.setText(fromListBean.getPost().getUpdated_at());
        dialog_tv_mail_username.setText(fromListBean.getUser_info().getNickname());
        GlideUtils.loadImage(getContext(), fromListBean.getUser_info().getHead_img_url(), dialog_riv_mail_userhead);

        mDialog_rv_mail_comment.setLayoutManager(new LinearLayoutManager(getContext()));

        RxBindingHelper.setOnClickListener(mDialog_et_mail_input_content, this);
        RxBindingHelper.setOnClickListener(sheetDialogView.findViewById(R.id.iv_mail_content_more_close), this);
        RxBindingHelper.setOnClickListener(mDialog_iv_mail_content_send, this);

        mInputMsgDialog.setOnSenCallback(new InputMsgDialog.SendMsgCallBack() {
            @Override
            public void sendMsgCallBack(String sendMsgContent) {
                if (!TextUtils.isEmpty(sendMsgContent)) {
                    mMailContentPresenter.postSubmit(fromListBean.getPost().getThread_id() , fromListBean.getPost().getId() , 0, sendMsgContent);
                } else {
                    ToastUtils.showShort("回复内容不能为空");
                }
            }

            @Override
            public void onDissMissDialog(String sendMsgContent) {
                mDialog_et_mail_input_content.setText(sendMsgContent);
            }
        });
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (!isAdded() && null == manager.findFragmentByTag(tag)) {
            FragmentTransaction ft = manager.beginTransaction();
            manager.executePendingTransactions();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void onGetPaginationSuccess(PaginationBean data) {
        CommonAdapter<PaginationBean.ListBean> commonAdapter = new CommonAdapter<PaginationBean.ListBean>(ApplicationsHelper.context(), data.getList(), R.layout.item_mail_content_child_layout) {
            @Override
            public void convert(ViewHolder holder, PaginationBean.ListBean listBean) {
               TextView item_tv_child_comment = holder.getView(R.id.item_tv_child_comment);
                if (!TextUtils.isEmpty(listBean.getRe_user_info().getNickname())) {
//                    item_tv_child_comment.setText(listBean.getRe_user_info().getNickname() + "回复" + listBean.getUser_info().getNickname() + ":" + listBean.getPost().getContent());

//                    SpannableStringBuilder stringBuilder = new SpannableStringBuilder(listBean.getRe_user_info().getNickname() + "回复" + listBean.getUser_info().getNickname() + ":" + listBean.getPost().getContent());
//                    stringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#595F83")), 0, listBean.getRe_user_info().getNickname().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//                    stringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#595F83")), listBean.getRe_user_info().getNickname().length() + 2, listBean.getRe_user_info().getNickname().length() + 2 + listBean.getUser_info().getNickname().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//                    holder.setText(R.id.item_tv_child_comment, stringBuilder);

                    String htmlText2 = "<font color='#595F83'>" + listBean.getUser_info().getNickname() + "</font>" + " 回复" + "<font color='#595F83'>" + listBean.getRe_user_info().getNickname() + "</font>" + ":" + listBean.getPost().getContent();
                    item_tv_child_comment.setText(Html.fromHtml(htmlText2));
                } else {

//                    item_tv_child_comment.setText(listBean.getRe_user_info().getNickname() + "回复" + listBean.getUser_info().getNickname() + ":" + listBean.getPost().getContent());

//                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(listBean.getUser_info().getNickname() + ":" + listBean.getPost().getContent());
//                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#595F83"));
//                    spannableStringBuilder.setSpan(foregroundColorSpan, 0, listBean.getUser_info().getNickname().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//                    holder.setText(R.id.item_tv_child_comment, spannableStringBuilder);
//                    item_tv_child_comment.setText(spannableStringBuilder.toString());

                    String htmlText2 = "<font color='#595F83'>" + listBean.getRe_user_info().getNickname() + "</font>" + ":" + listBean.getPost().getContent();
                    item_tv_child_comment.setText(Html.fromHtml(htmlText2));
                }
            }
        };
        mDialog_rv_mail_comment.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if (TextUtils.isEmpty(data.getList().get(position).getRe_user_info().getNickname())) {
                    showPostDialog(data.getList().get(position).getRe_user_info().getNickname(),
                            data.getList().get(position).getPost().getThread_id() ,
                            data.getList().get(position).getPost().getPost_id() ,
                            data.getList().get(position).getPost().getId()
                    );
                } else {
                    showPostDialog(data.getList().get(position).getUser_info().getNickname(),
                            data.getList().get(position).getPost().getThread_id() ,
                            data.getList().get(position).getPost().getPost_id() ,
                            data.getList().get(position).getPost().getId()
                    );
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void onThreadInfoSuccess(ThreadInfoBean bean) {

    }

    @Override
    public void onPostSubmitSuccess(String type) {
        mDialog_et_mail_input_content.setText("");
        mMailContentPresenter.getPagination(0, 200, fromListBean.getPost().getId(), fromListBean.getPost().getThread_id());
    }
    public final PublishSubject<Lifecycle.Event> lifecycleSubject = PublishSubject.create();
    @Override
    public PublishSubject<Lifecycle.Event> getLifecycleSubject() {
        return lifecycleSubject;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        lifecycleSubject.onNext(Lifecycle.Event.ON_DESTROY);
        if (mMailContentPresenter != null) {
            mMailContentPresenter.onDestroy(this);
            mMailContentPresenter = null;
        }
    }

    BottomSheetDialog mBottomSheetDialog;

    private void showPostDialog(String hint, int threadId, int postId, int rePostId) {
        View sheetDialogView = View.inflate(getActivity(), R.layout.dialog_mail_layout, null);
        EditText rt_mail_item = sheetDialogView.findViewById(R.id.rt_mail_item);
        TextView tv_mail_send = sheetDialogView.findViewById(R.id.tv_mail_send);
        if (mBottomSheetDialog == null) {
            mBottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetEdit);
        }
        mBottomSheetDialog.setContentView(sheetDialogView);
        mBottomSheetDialog.show();
        RxTextView.textChanges(rt_mail_item).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence text) throws Exception {
                if (text.length() > 0) {
                    OtherUtils.setRoundLineBg(tv_mail_send, 11, R.color.cl_D2AA77);
                } else {
                    OtherUtils.setRoundLineBg(tv_mail_send, 11, R.color.cl_dddddd);
                }
            }
        });
        if (!TextUtils.isEmpty(hint)) {
            rt_mail_item.setHint("回复" + hint + ":");
        }
        RxBindingHelper.setOnClickListener(tv_mail_send, new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mS = rt_mail_item.getText().toString().trim().replaceAll(" ", "");
                if (TextUtils.isEmpty(mS)) {
                    ToastUtils.showShort("回复内容不能为空");
                } else {
                    mMailContentPresenter.postSubmit(threadId, postId, rePostId, mS);
                    mBottomSheetDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dialog_iv_mail_content_send) {
            if (!TextUtils.isEmpty(CommonUtils.getStrFromView(mDialog_et_mail_input_content))) {
                mMailContentPresenter.postSubmit(fromListBean.getPost().getThread_id(), fromListBean.getPost().getId() , 0, CommonUtils.getStrFromView(mDialog_et_mail_input_content));
            } else {
                ToastUtils.showShort("回复内容不能为空");
            }
        } else if (v.getId() == R.id.dialog_iv_mail_content_send) {
            MorelBottomDialog.this.dismiss();
        } else if (v == mDialog_et_mail_input_content) {
            if (mInputMsgDialog != null) {
                mInputMsgDialog.show(getChildFragmentManager(), null);
            }
        } else if (v.getId() == R.id.iv_mail_content_more_close) {
            this.dismiss();
        }
    }
}
