package com.sunlands.intl.yingshi.ui.community;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.ibase.IBaseModel;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.ChannelListBean;
import com.sunlands.intl.yingshi.bean.CommThreadBean;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.bean.FriendGroupBean;
import com.sunlands.intl.yingshi.bean.FriendInfoBean;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.bean.MsgListBean;
import com.sunlands.intl.yingshi.bean.MyCollectBean;
import com.sunlands.intl.yingshi.bean.PaginationBean;
import com.sunlands.intl.yingshi.bean.PostSubmitBean;
import com.sunlands.intl.yingshi.bean.SysMsgBean;
import com.sunlands.intl.yingshi.bean.ThreadInfoBean;
import com.sunlands.intl.yingshi.bean.UploadBean;

import java.io.File;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore
 * 创 建 人: xueh
 * 创建日期: 2019/2/18 17:18
 * 备注：
 */
public interface IMessageContract {
    interface ConmmunityType {
        String view = "view";
        String recommend = "recommend";
    }

    interface View extends IBaseView {
        void onMsgListSuccess(MsgListBean mListBean);
    }

    interface Model extends IBaseModel {
        void getMsg_List(PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MsgListBean> mMVPModelCallbacks);
    }

    interface SysView extends IBaseView {
        void onSysMsgListSuccess(SysMsgBean mListBean);

        void onClearMsgSuccess();
    }

    interface SysModel extends IBaseModel {
        void getClearMsg(PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<String> mMVPModelCallbacks);

        void getSysMsg(int page, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<SysMsgBean> mMVPModelCallbacks);
    }

    interface FriendView extends IBaseView {
        void onFriendInfoSuccess(FriendInfoBean mInfoBean);
    }

    interface FriendModel extends IBaseModel {
        void getFriendInfo(String userid, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<FriendInfoBean> mMVPModelCallbacks);
    }


    interface MailView extends IBaseView {
        void getPaginationSuccess(MainlBean mMainlBean);
        void onPostSubmitSuccess(String type);
    }

    interface MailModel extends IBaseModel {
        void getMailInfo(int limit, String tab, String keyword,PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MainlBean> mMVPModelCallbacks);

        void threadLiked(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mMVPModelCallbacks);

        void unLiked(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mMVPModelCallbacks);

        void collect(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mMVPModelCallbacks);

        void unCollect(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mMVPModelCallbacks);

        void report_Submit(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<List<EmptyBean>> mMVPModelCallbacks);

        void delete(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mMVPModelCallbacks);
    }

    interface CreateMailView extends IBaseView {
        void onThreadSubmitSuccess();

        void getChannelListSuccess(ChannelListBean channelListBean);

        void getFriendGroupSuccess(FriendGroupBean bean);
    }

    interface CreateMailModel extends IBaseModel {
        void threadSubmit(String content, String title, String toGroupId, String channelId, List<String> img, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<CommThreadBean> mMVPModelCallbacks);

        void upload_Files(File file, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<UploadBean> mMVPModelCallbacks);

        void getChannelList(PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<ChannelListBean> mMVPModelCallbacks);

        void getFriendGroup(PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<FriendGroupBean> mMVPModelCallbacks);
    }

    interface MailContentView extends IBaseView {
        void onThreadInfoSuccess(ThreadInfoBean bean);

        void onPostSubmitSuccess(String type);

        void onGetPaginationSuccess(PaginationBean bean);
    }

    interface MailContentModel extends IBaseModel {
        void postSubmit(int threadId, int postId, int rePostId, String content, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<PostSubmitBean> mMVPModelCallbacks);

        void getThreadInfo(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<ThreadInfoBean> mMVPModelCallbacks);

        void getPagination(int start, int limit, int postId, int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<PaginationBean> mMVPModelCallbacks);
    }


    interface IMasterView extends IBaseView {
        void getPaginationSuccess(MainlBean mMainlBean);
    }

    interface IMasterModel extends IBaseModel {
        void getThread_Pagination(int limit, String tab, int channelId, String keyword, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MainlBean> mMVPModelCallbacks);

    }

    interface IMyCollect extends IBaseView {
        void onMineSuccess(MyCollectBean data);
    }

    interface IMyCollectModel extends IBaseModel {
        void mine_Thread(int start, int limit,String viewId , PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MyCollectBean> mMVPModelCallbacks);

        void mine_Collect(int start, int limit, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MyCollectBean> mMVPModelCallbacks);

    }
}
