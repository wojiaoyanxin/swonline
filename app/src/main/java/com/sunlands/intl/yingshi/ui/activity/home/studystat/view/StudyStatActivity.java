package com.sunlands.intl.yingshi.ui.activity.home.studystat.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.github.mikephil.charting.charts.LineChart;
import com.shangde.lepai.uilib.view.RoundImageView;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.comm_core.utils.rx.rxjava.RxJavaUtils;
import com.sunlands.comm_core.utils.rx.rxjava.task.RxUITask;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.StudyStatBean;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.ui.activity.home.studystat.IStudyStatContract;
import com.sunlands.intl.yingshi.ui.activity.home.studystat.presenter.StudyStatPresenter;
import com.sunlands.intl.yingshi.util.GlideUtils;

import java.io.File;
import java.util.HashMap;
import io.reactivex.functions.Consumer;
import okhttp3.internal.platform.Platform;

public class StudyStatActivity extends MvpActivity<StudyStatPresenter> implements IStudyStatContract.IStudyStatView {

    private LineChart mLinchart;
    private TextView mTv_study_title_content, mTv_study_homework_num, mTv_study_participation_num, mTv_study_time,
            mTv_study_type_week, mtv_study_type_all, mTv_study_user_info, mTv_study_time_content, mTv_study_look_class_num;
    private ImageView mIv_study_title_back;
    private RoundImageView mRiv_study_user_head;
    private ImageView mIv_study_share;
    private View mShareView;
    private String shareImgPath = PathUtils.getExternalAppCachePath() + File.separator + "studyshare.png";

    @Override
    public void onClick(View v) {
        if (v == mIv_study_title_back) {
            finish();
        } else if (mIv_study_share == v) {
            showShareDialog();
        } else if (mTv_study_type_week == v) {
            mTv_study_type_week.setTextColor(CommonUtils.getColor(R.color.cl_F9E13F));
            mtv_study_type_all.setTextColor(CommonUtils.getColor(R.color.cl_ffffff));
            getPresenter().getRecordsStat(IStudyStatContract.type.week);
        } else if (mtv_study_type_all == v) {
            mtv_study_type_all.setTextColor(CommonUtils.getColor(R.color.cl_F9E13F));
            mTv_study_type_week.setTextColor(CommonUtils.getColor(R.color.cl_ffffff));
            getPresenter().getRecordsStat(IStudyStatContract.type.year);
        }

    }

    private void onShare(String platform) {
//        if (ImageUtils.save(ImageUtils.view2Bitmap(mShareView == null ? getShareView() : mShareView), new File(PathUtils.getExternalAppCachePath(), "studyshare.png"), Bitmap.CompressFormat.PNG)) {
//            OnekeyShare oks = new OnekeyShare();
//            oks.setImagePath(shareImgPath);
//            oks.setPlatform(platform);
//            oks.setCallback(new PlatformActionListener() {
//                @Override
//                public void onComplete(
//                        Platform mPlatform, int mI, HashMap<String, Object> mHashMap) {
//                    RxJavaUtils.doInUIThread(new RxUITask<String>("分享成功") {
//                        @Override
//                        public void doInUIThread(String s) {
//                            ToastUtils.showShort(s);
//                        }
//                    });
//                    Log.e("StudyStatActivity", "onComplete: ");
//                }
//
//                @Override
//                public void onError(Platform mPlatform, int mI, Throwable mThrowable) {
//                    Log.e("StudyStatActivity", "onError: " + mThrowable.getMessage());
//                }
//
//                @Override
//                public void onCancel(Platform mPlatform, int mI) {
//                    RxJavaUtils.doInUIThread(new RxUITask<String>("取消分享") {
//                        @Override
//                        public void doInUIThread(String s) {
//                            ToastUtils.showShort(s);
//                        }
//                    });
//                }
//            });
//            oks.show(this.getApplicationContext());
//        }
    }

    private void showShareDialog() {
        BottomSheetDialog shareDialog = new BottomSheetDialog(this);
        View view = inflater.inflate(R.layout.dialog_study_share_laytout, null);
        RxBindingUtils.setViewClicks(view.findViewById(R.id.tv_study_save), new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                Bitmap view2Bitmap = ImageUtils.view2Bitmap(mShareView == null ? getShareView() : mShareView);
                File file = new File(PathUtils.getExternalAppCachePath(), "studyshare.png");
                if (ImageUtils.save(view2Bitmap, file, Bitmap.CompressFormat.PNG)) {
                    MediaStore.Images.Media.insertImage(Utils.getApp().getContentResolver(), view2Bitmap, shareImgPath, null);
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(file));
                    Utils.getApp().sendBroadcast(intent);
                    ToastUtils.showShort("保存成功");
                } else {
                    ToastUtils.showShort("保存失败");
                }
                shareDialog.dismiss();
            }
        });
        RxBindingUtils.setViewClicks(view.findViewById(R.id.tv_study_share_friend), new Consumer<Object>() {
            @Override
            public void accept(Object o) {
              //  onShare(Wechat.NAME);
                shareDialog.dismiss();
            }
        });
        RxBindingUtils.setViewClicks(view.findViewById(R.id.tv_study_share_wechatmoments), new Consumer<Object>() {
            @Override
            public void accept(Object o) {
               // onShare(WechatMoments.NAME);
                shareDialog.dismiss();
            }
        });
        RxBindingUtils.setViewClicks(view.findViewById(R.id.tv_study_share_cancel), new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                shareDialog.dismiss();
            }
        });
        shareDialog.setContentView(view);
        shareDialog.show();
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_study_stat;
    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        mIv_study_title_back = findViewById(R.id.iv_study_title_back);
        mTv_study_title_content = findViewById(R.id.tv_study_title_content);
        mIv_study_share = FBIA(R.id.iv_study_share);
        mLinchart = FBIA(R.id.linchart);
        mTv_study_type_week = FBIA(R.id.tv_study_type_week);
        mtv_study_type_all = FBIA(R.id.tv_study_type_all);
        mRiv_study_user_head = FBIA(R.id.riv_study_user_head);
        mTv_study_time = FBIA(R.id.tv_study_time);
        mTv_study_user_info = FBIA(R.id.tv_study_user_info);
        mTv_study_time_content = FBIA(R.id.tv_study_time_content);
        mTv_study_look_class_num = FBIA(R.id.tv_study_look_class_num);
        mTv_study_homework_num = FBIA(R.id.tv_study_homework_num);
        mTv_study_participation_num = FBIA(R.id.tv_study_participation_num);

    }

    @Override
    public void initDataAfterView() {
        getPresenter().getRecordsStat(IStudyStatContract.type.week);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        mTv_study_title_content.setText("学习统计");
        SpanUtils.with(mTv_study_time)
                .append("0").setForegroundColor(CommonUtils.getColor(R.color.cl_252A3A)).setBold()
                .append("分钟").setForegroundColor(CommonUtils.getColor(R.color.cl_252A3A)).setFontSize(13, true)
                .create();
        mLinchart.setNoDataTextColor(CommonUtils.getColor(R.color.cl_252A3A));
        mLinchart.setNoDataText("暂无数据");
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mIv_study_share, this);
        RxBindingHelper.setOnClickListener(mIv_study_title_back, this);
        RxBindingHelper.setOnClickListener(mTv_study_type_week, this);
        RxBindingHelper.setOnClickListener(mtv_study_type_all, this);
    }

    @Override
    protected StudyStatPresenter createPresenter(IBaseView view) {
        return new StudyStatPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().init();
    }

    StudyStatBean data = new StudyStatBean();

    @Override
    public void onRecordsStatSuccess(StudyStatBean studyStatBean) {
        if (!ObjectUtils.isEmpty(studyStatBean)) {
            data = studyStatBean;
        }
        mShareView = getShareView();
        GlideUtils.loadImage(ApplicationsHelper.context(), TextUtils.isEmpty(data.getAvatar()) ? LoginUserInfoHelper.getInstance().getUserInfo().getAvatar() : data.getAvatar(), mRiv_study_user_head);
        mTv_study_time_content.setText(getStr(data.getBeyondRate()));
        mTv_study_look_class_num.setText(data.getCourseJoinedNum() + "");
        mTv_study_homework_num.setText(data.getHomeworkJoinedNum() + "");
        mTv_study_participation_num.setText(data.getRate() + "%");

        LineChartUtils.setLineChartData(mLinchart, data);
        SpanUtils.with(mTv_study_user_info)
                .append(data.getName()).setForegroundColor(CommonUtils.getColor(R.color.cl_000000)).setBold()
                .append("   ")
                .append(String.format("成为学员第%s天", data.getDays())).setForegroundColor(CommonUtils.getColor(R.color.cl_666666))
                .create();
        //非付费用户没有学习时间默认是0只能放最后
        SpanUtils.with(mTv_study_time)
                .append(data.getStudyLength().substring(0, data.getStudyLength().length() - 2)).setForegroundColor(CommonUtils.getColor(R.color.cl_252A3A)).setBold()
                .append(data.getStudyLength().contains("分钟") ? "分钟" : "小时").setForegroundColor(CommonUtils.getColor(R.color.cl_252A3A)).setFontSize(13, true)
                .create();
    }

    private View getShareView() {
        View view = inflater.inflate(R.layout.activity_study_share, null);
        TextView tv_study_share_content = view.findViewById(R.id.tv_study_share_content);

        TextView tv_study_share_time_content = view.findViewById(R.id.tv_study_time_content);
        tv_study_share_time_content.setText(getStr(data.getBeyondRate()));
        ImageView iv_study_app_icon = view.findViewById(R.id.iv_study_app_icon);
        iv_study_app_icon.setImageBitmap(ImageUtils.toRoundCorner(ImageUtils.drawable2Bitmap(CommonUtils.getDrawable(R.drawable.ic_launcher)), CommonUtils.dip2px(6)));

        GlideUtils.loadImage(ApplicationsHelper.context(), TextUtils.isEmpty(data.getAvatar()) ? LoginUserInfoHelper.getInstance().getUserInfo().getAvatar() : data.getAvatar(), view.findViewById(R.id.riv_study_user_head));
        TextView tv_study_share_user_info = view.findViewById(R.id.tv_study_user_info);
        TextView tv_study_share_time = view.findViewById(R.id.tv_study_time);
        LineChart share_linchart = view.findViewById(R.id.share_linchart);
        SpanUtils.with(tv_study_share_content)
                .append("我正在攻读").setForegroundColor(CommonUtils.getColor(R.color.cl_999999))
                .append(LoginUserInfoHelper.getInstance().getUserInfo().getUniversity().contains("大学")?LoginUserInfoHelper.getInstance().getUserInfo().getUniversity().substring(0, LoginUserInfoHelper.getInstance().getUserInfo().getUniversity().indexOf("大学") + 2):"国际硕士").setForegroundColor(CommonUtils.getColor(R.color.cl_F9E13F))
                .append("学位，有没有兴趣来读个硕士？").setForegroundColor(CommonUtils.getColor(R.color.cl_999999))
                .create();
        SpanUtils.with(tv_study_share_user_info)
                .append(data.getName()).setForegroundColor(CommonUtils.getColor(R.color.cl_000000)).setBold()
                .append("   ")
                .append(String.format("成为学员第%s天", data.getDays())).setForegroundColor(CommonUtils.getColor(R.color.cl_666666))
                .create();
        SpanUtils.with(tv_study_share_time)
                .append(data.getStudyLength().equals("0") ? "0" : data.getStudyLength().substring(0, data.getStudyLength().length() - 2)).setForegroundColor(CommonUtils.getColor(R.color.cl_252A3A)).setBold()
                .append(data.getStudyLength().contains("分钟") ? "分钟" : "小时").setForegroundColor(CommonUtils.getColor(R.color.cl_252A3A)).setFontSize(10, true)
                .create();

        ((TextView) view.findViewById(R.id.tv_study_look_class_num)).setText(data.getCourseJoinedNum() + "");
        ((TextView) view.findViewById(R.id.tv_study_homework_num)).setText(data.getHomeworkJoinedNum() + "");
        ((TextView) view.findViewById(R.id.tv_study_participation_num)).setText(data.getRate() + "%");

        LineChartUtils.setLineChartData(share_linchart, data);

        int width = ScreenUtils.getScreenWidth();
        int height = ScreenUtils.getScreenHeight();
        if (width / 9 * 16 < height) {
            layoutView(view, width, width / 9 * 16);
        } else {
            layoutView(view, width, height);
        }
        return view;
    }

    private void layoutView(View v, int width, int height) {
        v.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        v.measure(measuredWidth, measuredHeight);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
    }

    public String getStr(int beyondRate) {
        if (beyondRate >= 80) {
            return String.format("学习时长超过%s同学，真棒！继续坚持哦！", beyondRate + "%");
        } else if (beyondRate >= 50) {
            return String.format("学习时长超过%s同学，不错！再接再厉哦！", beyondRate + "%");
        } else if (beyondRate > 0 && beyondRate < 50) {
            return String.format("学习时长超过%s同学，加油！还可以更好！", beyondRate + "%");
        } else {
            return String.format("学习时长超过%s同学，要努力啦！", beyondRate + "%");
        }
    }
}
