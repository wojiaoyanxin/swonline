package com.sunlands.intl.yingshi.ui.community.presenter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.model.CommunitylModel;
import com.sunlands.intl.yingshi.ui.community.view.CommunityContentActivity;

import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/2/25 15:31
 * 备注：
 */
public class CommunityPresenter extends MvpBasePresenter<IMessageContract.MailView, CommunitylModel> {
    public CommunityPresenter(IMessageContract.MailView mMailView) {
        super(mMailView);
    }

    @Override
    protected CommunitylModel createModel() {
        return new CommunitylModel();
    }

    @CheckNet
    public void getMailListInfo(int limit, String tab) {
        getPagination(limit, tab, "");
    }

    @CheckNet
    public void getPagination(int limit, String tab, String keyword) {
        getView().showLoading();
        getModel().getMailInfo(limit, tab, keyword, getView().getLifecycleSubject(), new MVPModelCallbacks<MainlBean>() {
            @Override
            public void onSuccess(MainlBean data) {
                getView().getPaginationSuccess(data);
                getView().hideLoading();
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }


    @CheckNet
    public void threadLiked(ImageView view, int threadId) {
        getView().showLoading();
        getModel().threadLiked(threadId, getView().getLifecycleSubject(), new MVPModelCallbacks<EmptyBean>() {
            @Override
            public void onSuccess(EmptyBean data) {
                getView().hideLoading();
                startZoomOutAnimation(view);
                ToastUtils.showShort("点赞成功");
                getView().onPostSubmitSuccess("点赞成功");

            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    @CheckNet
    public void unLiked(ImageView view, int threadId) {
        getView().showLoading();
        getModel().unLiked(threadId, getView().getLifecycleSubject(), new MVPModelCallbacks<EmptyBean>() {
            @Override
            public void onSuccess(EmptyBean data) {
                startZoomInAnimation(view);
                getView().hideLoading();
                getView().onPostSubmitSuccess("取消点赞");
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    //贴子收藏
    @CheckNet
    public void collect(int threadId) {
        getView().showLoading();
        getModel().collect(threadId, getView().getLifecycleSubject(), new MVPModelCallbacks<EmptyBean>() {
            @Override
            public void onSuccess(EmptyBean data) {
                ToastUtils.showShort("已收藏，请前往\"我的贴子\"查看");
                getView().hideLoading();
                getView().onPostSubmitSuccess("收藏");

            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    //贴子取消收藏
    @CheckNet
    public void unCollect(int threadId) {
        getView().showLoading();
        getModel().unCollect(threadId, getView().getLifecycleSubject(), new MVPModelCallbacks<EmptyBean>() {
            @Override
            public void onSuccess(EmptyBean data) {
                getView().hideLoading();
                getView().onPostSubmitSuccess("取消收藏");
                ToastUtils.showShort("已取消收藏");
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    //贴子举报
    @CheckNet
    public void report_Submit(int threadId) {
        getView().showLoading();
        getModel().report_Submit(threadId, getView().getLifecycleSubject(), new MVPModelCallbacks<List<EmptyBean>>() {
            @Override
            public void onSuccess(List<EmptyBean> data) {
                getView().hideLoading();
//                getView().onPostSubmitSuccess();
                ToastUtils.showShort("您的举报平台已收到，确认有不良内容后会予以处理");
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    //贴子删除
    @CheckNet
    public void delete(int threadId) {
        getView().showLoading();
        getModel().delete(threadId, getView().getLifecycleSubject(), new MVPModelCallbacks<EmptyBean>() {
            @Override
            public void onSuccess(EmptyBean data) {
                getView().hideLoading();
                ToastUtils.showShort("删除成功");
                getView().onPostSubmitSuccess("帖子删除");
                ActivityUtils.finishActivity(CommunityContentActivity.class);
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
                ToastUtils.showShort(model.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    /**
     * 缩小动画
     *
     * @param view 被缩放的目标
     */
    public static void startZoomInAnimation(ImageView view) {
        ObjectAnimator scaleAnimationX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.2f, 0.2f, 1.0f);
        ObjectAnimator scaleAnimationY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.2f, 0.2f, 1.0f);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleAnimationX, scaleAnimationY);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setImageResource(R.drawable.iv_mail_no_like);
            }
        });
        set.start();
    }

    /**
     * 放大动画
     *
     * @param view 被放大的目标
     */
    public static void startZoomOutAnimation(ImageView view) {
        ObjectAnimator scaleAnimationX = ObjectAnimator.ofFloat(view, "scaleX", 0.2f, 0.8f, 1.2f, 1.0f);
        ObjectAnimator scaleAnimationY = ObjectAnimator.ofFloat(view, "scaleY", 0.2f, 0.8f, 1.2f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleAnimationX, scaleAnimationY);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setImageResource(R.drawable.iv_mail_like);
            }
        });
        set.start();
    }
}
