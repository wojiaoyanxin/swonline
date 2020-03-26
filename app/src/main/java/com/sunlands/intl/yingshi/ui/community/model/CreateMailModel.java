package com.sunlands.intl.yingshi.ui.community.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.ChannelListBean;
import com.sunlands.intl.yingshi.bean.CommThreadBean;
import com.sunlands.intl.yingshi.bean.FriendGroupBean;
import com.sunlands.intl.yingshi.bean.UploadBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;

import java.io.File;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.model
 * 创 建 人: xueh
 * 创建日期: 2019/3/6 14:01
 * 备注：
 */
public class CreateMailModel extends MvpBaseModel<RestApi> implements IMessageContract.CreateMailModel {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }

    @Override
    public void threadSubmit(String content, String title, String toGroupId, String channelId, List<String> img, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<CommThreadBean> mMVPModelCallbacks) {
        deploy(getApi().threadSubmit(content, title, toGroupId, channelId, img), mPublishSubject, mMVPModelCallbacks);
    }

    @Override
    public void upload_Files(File file, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<UploadBean> mMVPModelCallbacks) {
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file_url",file.getAbsolutePath(), requestBody);
        deploy(getApi().upload_Files(body), mPublishSubject, mMVPModelCallbacks);
    }

    @Override
    public void getChannelList(PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<ChannelListBean> mMVPModelCallbacks) {
        deploy(getApi().channel_List(), mPublishSubject, mMVPModelCallbacks);
    }

    @Override
    public void getFriendGroup(PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<FriendGroupBean> mMVPModelCallbacks) {
        deploy(getApi().friend_Group(), mPublishSubject, mMVPModelCallbacks);
    }
}
