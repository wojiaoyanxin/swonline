package com.sunlands.intl.yingshi.ui.community.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.OnItemClickListener;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.PaginationBean;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityContentPresenter;
import com.sunlands.intl.yingshi.ui.community.view.MorelBottomDialog;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.OtherUtils;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.adapter
 * 创 建 人: xueh
 * 创建日期: 2019/3/7 13:57
 * 备注：
 */
public class CommunityContentAdapter extends CommonAdapter<PaginationBean.ListBean> {
    private Context mContext;
    private FragmentManager mFragmentManager;
    private CommunityContentPresenter mPresenter;
    public CommunityContentAdapter(Context context, List<PaginationBean.ListBean> datas, FragmentManager manager, CommunityContentPresenter presenter) {
        super(context, datas, R.layout.item_mail_content_layout);
        this.mContext = context;
        this.mFragmentManager=manager;
        this.mPresenter=presenter;
    }

    @Override
    public void convert(ViewHolder holder, PaginationBean.ListBean listBean) {
        holder.setText(R.id.item_tv_mail_username, listBean.getUser_info().getNickname());
        holder.setText(R.id.item_tv_mail_content, listBean.getPost().getContent());
        GlideUtils.loadImage(ApplicationsHelper.context(), listBean.getUser_info().getHead_img_url(), holder.getView(R.id.item_riv_mail_userhead));
        holder.setText(R.id.item_mail_time, listBean.getPost().getCreated_at());
        RecyclerView item_rv_mail_comment = holder.getView(R.id.item_rv_mail_comment);
        item_rv_mail_comment.setLayoutManager(new LinearLayoutManager(ApplicationsHelper.context()));
        CommonAdapter<PaginationBean.ListBean.CommentListBean> childAdapter = new CommonAdapter<PaginationBean.ListBean.CommentListBean>(ApplicationsHelper.context(), listBean.getComment_list(), R.layout.item_mail_content_child_layout) {
            @Override
            public void convert(ViewHolder childholder, PaginationBean.ListBean.CommentListBean commentListBean) {
                if (!TextUtils.isEmpty(commentListBean.getRe_user_info().getNickname())) {
                    SpannableStringBuilder stringBuilder = new SpannableStringBuilder(commentListBean.getUser_info().getNickname() + "回复" + commentListBean.getRe_user_info().getNickname() + ":" + commentListBean.getPost().getContent());
                    stringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#595F83")), 0, commentListBean.getRe_user_info().getNickname().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    stringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#595F83")), commentListBean.getRe_user_info().getNickname().length() + 2, commentListBean.getRe_user_info().getNickname().length() + 2 + commentListBean.getUser_info().getNickname().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    childholder.setText(R.id.item_tv_child_comment, stringBuilder);
                } else {
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(commentListBean.getUser_info().getNickname() + ":" + commentListBean.getPost().getContent());
                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#595F83"));
                    spannableStringBuilder.setSpan(foregroundColorSpan, 0, commentListBean.getUser_info().getNickname().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    childholder.setText(R.id.item_tv_child_comment, spannableStringBuilder);
                }
            }
        };
        if (Integer.valueOf(listBean.comment_num) > 2) {
            holder.getView(R.id.item_tv_see_more).setVisibility(View.VISIBLE);
            holder.getView(R.id.view_see_more_line).setVisibility(View.VISIBLE);
            holder.setOnClickListener(R.id.item_tv_see_more, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MorelBottomDialog morelBottomDialog = new MorelBottomDialog();
                    morelBottomDialog.setData(listBean);
                    morelBottomDialog.show(mFragmentManager, "tag");
                }
            });
        } else {
            holder.getView(R.id.view_see_more_line).setVisibility(View.GONE);
            holder.getView(R.id.item_tv_see_more).setVisibility(View.GONE);
        }
        item_rv_mail_comment.setAdapter(childAdapter);
        childAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if (TextUtils.isEmpty(listBean.getComment_list().get(position).getRe_user_info().getNickname())) {
                    showDialog(listBean.getComment_list().get(position).getRe_user_info().getNickname(),
                            listBean.getComment_list().get(position).getPost().getThread_id(),
                            listBean.getComment_list().get(position).getPost().getPost_id(),
                            listBean.getComment_list().get(position).getPost().getId()
                    );
                } else {
                    showDialog(listBean.getComment_list().get(position).getUser_info().getNickname(),
                            listBean.getComment_list().get(position).getPost().getThread_id(),
                            listBean.getComment_list().get(position).getPost().getPost_id(),
                            listBean.getComment_list().get(position).getPost().getId()
                    );
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        holder.setOnClickListener(R.id.item_iv_comment, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复
                showDialog(listBean.getUser_info().getNickname(), listBean.getPost().getThread_id(), listBean.getPost().getId(), 0);
            }
        });
    }

    BottomSheetDialog mBottomSheetDialog;

    private void showDialog(String hint, int threadId, int postId, int rePostId) {
        View sheetDialogView = View.inflate(mContext, R.layout.dialog_mail_layout, null);
        EditText rt_mail_item = sheetDialogView.findViewById(R.id.rt_mail_item);
        TextView tv_mail_send = sheetDialogView.findViewById(R.id.tv_mail_send);
        if (mBottomSheetDialog == null) {
            mBottomSheetDialog = new BottomSheetDialog(mContext, R.style.BottomSheetEdit);
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
                        mPresenter.postSubmit(threadId, postId, rePostId, mS);
                        mBottomSheetDialog.dismiss();
                }
            }
        });
    }
}
