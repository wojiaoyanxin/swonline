package com.sunlands.intl.yingshi.ui.activity.home.mythesisn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTarget;
import com.arialyy.aria.core.download.DownloadTask;
import com.github.barteksc.pdfviewer.PDFView;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.greendao.db.PaperDbBean;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.util.DLog;
import com.sunlands.intl.yingshi.util.StringUtils;
import java.io.File;


public class PaperAnalysisActivity extends CommonActivity<Object> {

    PDFView mPDFView;
    TextView mTvProgress;
    ConstraintLayout mClProgress;
    ConstraintLayout mClEmptyView;
    TextView mTvEmpty;

    private PagerBean mPagerBean;
    private String mFileUrl;
    private String mFileName;
    private File mFile;
    private String mFilePath;
    private String CACHE_SUFFIX = "_cache";
    private String filePath;
    private int type;


    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);

        mPDFView = FBIA(R.id.pdfView);
        mTvProgress = FBIA(R.id.tv_progress);
        mClProgress = FBIA(R.id.cl_progress);
        mClEmptyView = FBIA(R.id.cl_empty_view);
        mTvEmpty = FBIA(R.id.tv_empty);


    }

    public static void show(Context context, PagerBean pagerBean, int type) {
        Intent intent = new Intent(context, PaperAnalysisActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.Key.KEY_PAGER_BEAN, pagerBean);
        intent.putExtra(Constants.Key.KEY_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
        mPagerBean = (PagerBean) getIntent().getParcelableExtra(Constants.Key.KEY_PAGER_BEAN);
        type = getIntent().getIntExtra(Constants.Key.KEY_TYPE, 0);
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        if (mFileUrl.length() > 11) {
            filePath = Constants.CACHE_DIR + "/" + mFileUrl.substring(mFileUrl.length() - 10, mFileUrl.length());
            mFileName = mFileUrl.substring(mFileUrl.length() - 10, mFileUrl.length()) + CACHE_SUFFIX;
        } else {
            mFileName = mFileUrl + CACHE_SUFFIX;
        }
        mFilePath = Constants.CACHE_DIR + File.separator + mFileName;
        requestPermission();
    }


    protected void init() {
        hideProgressBar();
        Aria.download(this).register();
    }


    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        init();
    }

    private void requestPermission() {

        if (TextUtils.isEmpty(mFileUrl)) {
            downloadPdf2(mFileUrl);
            return;
        }

        mFile = new File(filePath);
        if (mFile.exists()) {
            show(mFile);
        } else {
            downloadPdf2(mFileUrl);
        }
    }

    private void downloadPdf2(String fileUrl) {
        if (StringUtils.isEmpty(mFileUrl)) {
            mClEmptyView.setVisibility(View.VISIBLE);
            mTvEmpty.setText(R.string.no_more_content);
            return;
        }
        DownloadTarget target = Aria.download(this).load(fileUrl);
        showProgressBar(target.getPercent());
        Aria.download(this)
                .load(fileUrl)
                .setFilePath(mFilePath).start();
    }

    @Download.onPre
    void onPre(DownloadTask task) {
        DLog.d(task.getTaskName() + ", " + task.getState());
    }

    @Download.onTaskStart
    void taskStart(DownloadTask task) {
        DLog.d(task.getTaskName() + ", " + task.getState());
        showProgressBar(0);
    }

    @Download.onTaskResume
    void taskResume(DownloadTask task) {
        DLog.d(task.getTaskName() + ", " + task.getState());
    }

    @Download.onTaskStop
    void taskStop(DownloadTask task) {
        DLog.d(task.getTaskName() + ", " + task.getState());
    }

    @Download.onTaskCancel
    void taskCancel(DownloadTask task) {
        DLog.d(task.getTaskName() + ", " + task.getState());
    }

    @Download.onTaskFail
    void taskFail(DownloadTask task) {
        DLog.d(task.getTaskName() + ", " + task.getState());
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        DLog.d(task.getTaskName() + ", " + task.getState());
        hideProgressBar();
        filePath = task.getDownloadPath();
        DLog.d("getDownloadPath: " + filePath);
        if (filePath.contains(CACHE_SUFFIX)) {
            filePath = filePath.replace(CACHE_SUFFIX, "");
            File fileCache = new File(task.getDownloadPath());
            File file = new File(filePath);
            boolean b = fileCache.renameTo(file);
        }
        DLog.d("getDownloadPath: " + filePath);
        //  openPdf(filePath);
        show(new File(filePath));
        PaperDbBean paperDbBean = new PaperDbBean();
        paperDbBean.setThesisId(mPagerBean.getThesisId());
        paperDbBean.setFilePath(filePath);
        DbHelper.getInstance().getPaperDbBeanDao().deleteByKey(mPagerBean.getThesisId());
        DbHelper.getInstance().getPaperDbBeanDao().insert(paperDbBean);
    }

    @Download.onTaskRunning()
    void taskRunning(DownloadTask task) {
        DLog.d(task.getTaskName() + ", " + task.getState() + ", progress: " + task.getPercent());
        mTvProgress.setText(String.format("%s%s%%", getString(R.string.file_downloading), task.getPercent()));
    }

    private void showProgressBar(int progress) {
        mClProgress.setVisibility(View.VISIBLE);
        mTvProgress.setText(String.format("%s%s%%", getString(R.string.file_downloading), progress));
        mPDFView.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mClProgress.setVisibility(View.GONE);
        mPDFView.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_paper_analysis;
    }

    @Override
    public String getTitleText() {
        if (type == 1) {
            mFileUrl = mPagerBean.getFileUrl();//pdf论文地址
            return getResources().getString(R.string.my_paper);
        } else {
            mFileUrl = mPagerBean.getEditUrl();//pdf解析地址
            return getResources().getString(R.string.activity_paper_analysis);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!TextUtils.isEmpty(mFileUrl)) {
            Aria.download(this).load(mFileUrl).stop();
        }
    }


    public void show(File file) {

        mPDFView.fromFile(file)
                //是否允许翻页，默认是允许翻页
                .enableSwipe(true)
                //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .swipeHorizontal(false)
                .enableDoubletap(false)
                //设置默认显示第0页
                .defaultPage(0)
                // 渲染风格（就像注释，颜色或表单）
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                // 改善低分辨率屏幕上的渲染
                .enableAntialiasing(true)
                // 页面间的间距。定义间距颜色，设置背景视图
                .spacing(5)
                .load();
    }

}
