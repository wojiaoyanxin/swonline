package com.sunlands.intl.yingshi.ui.community.presenter;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.ChannelListBean;
import com.sunlands.intl.yingshi.bean.CommThreadBean;
import com.sunlands.intl.yingshi.bean.FriendGroupBean;
import com.sunlands.intl.yingshi.bean.UploadBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.model.CreateMailModel;
import com.xueh.picselect.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/3/6 14:01
 * 备注：
 */
public class CreateMailPresenter extends MvpBasePresenter<IMessageContract.CreateMailView, CreateMailModel> {
    private List<String> mFileList = new ArrayList<>();

    public CreateMailPresenter(IMessageContract.CreateMailView mCreateMailView) {
        super(mCreateMailView);
    }

    @Override
    protected CreateMailModel createModel() {
        return new CreateMailModel();
    }

    @CheckNet
    public void threadSubmit(String content, String title, String toGroupId, String channelId) {
        getModel().threadSubmit(content, title, toGroupId, channelId, mFileList, getView().getLifecycleSubject(), new MVPModelCallbacks<CommThreadBean>() {
            @Override
            public void onSuccess(CommThreadBean data) {
                getView().onThreadSubmitSuccess();
                getView().hideLoading();
            }

            @Override
            public void onException(BaseModel model) {
                ToastUtils.showShort(model.getMsg());
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    @CheckNet
    public void getChannelList() {
        getModel().getChannelList(getView().getLifecycleSubject(), new MVPModelCallbacks<ChannelListBean>() {
            @Override
            public void onSuccess(ChannelListBean data) {
                getView().getChannelListSuccess(data);
            }

            @Override
            public void onException(BaseModel model) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @CheckNet
    public void getFriendGroup() {
        getModel().getFriendGroup(getView().getLifecycleSubject(), new MVPModelCallbacks<FriendGroupBean>() {
            @Override
            public void onSuccess(FriendGroupBean data) {
                getView().getFriendGroupSuccess(data);
            }

            @Override
            public void onException(BaseModel model) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @CheckNet
    public void upload_Files(List<LocalMedia> files, String content, String title, String toGroupId, String channelId) {
        mFileList.clear();
        finalIndex = 0;
        getView().showLoading();
        if(ObjectUtils.isNotEmpty(files)){
            upload(files, new IUploadSuccess() {
                @Override
                public void onUploadSuccess() {
                    threadSubmit(content, title, toGroupId, channelId);
                }
            });
        }else{
            threadSubmit(content, title, toGroupId, channelId);
        }

    }

    int finalIndex = 0;

    public void upload(List<LocalMedia> files, IUploadSuccess uploadSuccess) {

        getModel().upload_Files(new File(files.get(finalIndex).getCompressPath()), getView().getLifecycleSubject(), new MVPModelCallbacks<UploadBean>() {
            @Override
            public void onSuccess(UploadBean data) {
                mFileList.add(data.getFileId());
                if (finalIndex == files.size() - 1) {
                    if (uploadSuccess != null) {
                        uploadSuccess.onUploadSuccess();
                    }
                    return;
                } else {
                    finalIndex++;
                    CreateMailPresenter.this.upload(files, uploadSuccess);
                }
            }

            @Override
            public void onException(BaseModel model) {
                ToastUtils.showShort(model.getMsg());
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    public interface IUploadSuccess {
        void onUploadSuccess();
    }


}
