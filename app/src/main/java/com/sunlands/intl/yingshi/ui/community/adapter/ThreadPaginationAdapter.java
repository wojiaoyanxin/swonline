package com.sunlands.intl.yingshi.ui.community.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.OnItemClick;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.bean.MyCollectBean;
import com.sunlands.intl.yingshi.ui.community.other.NineGridLayoutManager;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityPresenter;
import com.sunlands.intl.yingshi.ui.community.view.CommmunitCollectDialog;
import com.sunlands.intl.yingshi.ui.community.view.CommunityContentActivity;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.OtherUtils;

import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.community.adapter
 * 创 建 人: xueh
 * 创建日期: 2019/4/12 16:09
 * 备注：
 */
public class ThreadPaginationAdapter extends CommonAdapter<MainlBean.ListBean> {
    MyCollectBean.UserInfoBeanX mInfoBean;

    public MyCollectBean.UserInfoBeanX getInfoBean() {
        return mInfoBean;
    }

    public void setUserInfo(MyCollectBean.UserInfoBeanX userInfo) {
        this.mInfoBean = userInfo;
    }

    private CommunityPresenter mP;
    private FragmentManager mFragmentManager;

    public ThreadPaginationAdapter(Context context, List<MainlBean.ListBean> datas, FragmentManager manager, CommunityPresenter presonter) {
        super(context, datas, R.layout.item_mail_layout);
        this.mFragmentManager = manager;
        this.mP = presonter;
    }

    private CommunityPresenter getPresenter() {
        return mP;
    }

    @Override
    public void convert(ViewHolder holder, MainlBean.ListBean mListBean) {
        if (mInfoBean != null) {
            holder.setText(R.id.tv_mail_name, mInfoBean.getNickname());
            GlideUtils.loadImage(ApplicationsHelper.context(), mInfoBean.getHead_img_url(), holder.getView(R.id.iv_mail_head));
        } else {
            holder.setText(R.id.tv_mail_name, mListBean.getUser_info().getNickname());
            GlideUtils.loadImage(ApplicationsHelper.context(), mListBean.getUser_info().getHead_img_url(), holder.getView(R.id.iv_mail_head));
        }
        holder.setText(R.id.tv_mail_content, mListBean.getThread().getContent());
        holder.setText(R.id.tv_mail_time, mListBean.getThread().getPublished_at().trim());
        if (TextUtils.isEmpty(mListBean.getThread().getTitle())) {
            holder.getView(R.id.tv_mail_title).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.tv_mail_title).setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_mail_title, mListBean.getThread().getTitle());
        }
        if (mListBean.getThread().getIs_stuck() == 1) {
            holder.getView(R.id.iv_mail_zhiding).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.iv_mail_zhiding).setVisibility(View.GONE);
        }
        holder.setText(R.id.tv_mail_message, mListBean.getExtend().getComment_num() + "");
        holder.setText(R.id.tv_mail_share, mListBean.getThread().getShared_num() + "");
        holder.setText(R.id.tv_mail_like, mListBean.getThread().getLiked_num() + "");
        holder.setText(R.id.tv_mail_read_num, mListBean.getThread().getView_num() + "浏览");
        OtherUtils.setClickableState(holder.getView(R.id.tv_mail_read_num), 3, R.color.cl_EAEAEA);
        OtherUtils.setRoundLineBg(holder.getView(R.id.tv_vocation), 3, R.color.cl_74A9E6);
        if (mListBean.getExtend().getIs_liked().equals("0")) {
            holder.setImageResource(R.id.iv_mail_islike, R.drawable.iv_mail_no_like);
        } else {
            holder.setImageResource(R.id.iv_mail_islike, R.drawable.iv_mail_like);
        }

        //贴子中的图片
        RecyclerView rv_img_list = holder.getView(R.id.rv_img_list);
        if (mListBean.getThread().getImg_num() == 0) {
            rv_img_list.setVisibility(View.GONE);
        } else {
            rv_img_list.setVisibility(View.VISIBLE);
            if (mListBean.getImage().size() == 1) {
                RequestOptions options = (new RequestOptions()).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop();
                Glide.with(mContext).asBitmap()
                        .load(mListBean.getImage()
                                .get(0)).apply(options).into(new SimpleTarget<Bitmap>(480, 800) {
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        Bitmap scale = ImageUtils.scale(bitmap, bitmap.getWidth(), bitmap.getHeight() / 4);
                        int width = scale.getWidth();
                        int height = scale.getHeight();
                        rv_img_list.setLayoutManager(new NineGridLayoutManager(ApplicationsHelper.context(), width, height));
                    }
                });
            } else {
                rv_img_list.setLayoutManager(new NineGridLayoutManager(ApplicationsHelper.context()));
            }
            rv_img_list.setAdapter(new CommonAdapter<String>(ApplicationsHelper.context(), mListBean.getImage().size() > 3 ? mListBean.getImage().subList(0, 3) : mListBean.getImage(), R.layout.item_comm_img_layout) {
                @Override
                public void convert(ViewHolder childholder, String s) {
                    GlideUtils.loadImage(ApplicationsHelper.context(), s, childholder.getView(R.id.iv_comm_img_item));
                    childholder.itemView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return holder.itemView.onTouchEvent(event);
                        }
                    });
                }
            });
            rv_img_list.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return holder.itemView.onTouchEvent(event);
                }
            });
        }
        RxBindingHelper.setOnClickListener(holder.getView(R.id.iv_mail_more), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommmunitCollectDialog collectDialog = new CommmunitCollectDialog(ActivityUtils.getTopActivity());
                collectDialog.setData(mListBean.getExtend().getIs_collect(), mListBean.getUser_info() == null ? 0 : mListBean.getUser_info().getId(), mListBean.getThread().getId(), mFragmentManager, getPresenter());
                collectDialog.show();
            }
        });
        RxBindingHelper.setOnClickListener(holder.getView(R.id.iv_mail_islike), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 0 不喜欢  1喜欢
                if (mListBean.getExtend().getIs_liked().equals("0")) {
                    getPresenter().threadLiked((ImageView) v, mListBean.getThread().getId());
                } else {
                    getPresenter().unLiked((ImageView) v, mListBean.getThread().getId());
                }

            }
        });
        this.setOnItemClickListener(new OnItemClick() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                //具体内容页面
                Intent intent = new Intent(mContext, CommunityContentActivity.class);
                intent.putExtra(CommunityContentActivity.MAIL_INFO, getDatas().get(position).getThread());
                ActivityUtils.startActivity(intent);
            }
        });
    }
}
