package com.sunlands.intl.yingshi.helper;

import android.content.Context;
import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelSuccessCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.CourseEnterResponse;
import com.sunlands.intl.yingshi.constant.RestApi;

import io.reactivex.subjects.PublishSubject;


public class EnterClassRequestHelper extends MvpBaseModel<RestApi> {

    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }

    public void enterClass(Context context, String courseId, enterClassCallBack enterClassCallBack) {

        deploy(getApi().courseEnter(courseId), PublishSubject.create(), new MVPModelSuccessCallbacks<CourseEnterResponse>() {

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                enterClassCallBack.enterFailed(e.getMessage());
            }

            @Override
            public void onException(BaseModel model) {
                super.onException(model);
                enterClassCallBack.enterFailed(model.getMsg());
            }

            @Override
            public void onSuccess(CourseEnterResponse data) {
                enterClassCallBack.enterSuccess(data);
            }
        }, true);

    }


   public interface enterClassCallBack {


        void enterSuccess(CourseEnterResponse courseEnterResponse);

        void enterFailed(String msg);

    }

}
