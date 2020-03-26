package com.sunlands.intl.yingshi.ui.my.handout.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTarget;
import com.arialyy.aria.core.download.DownloadTask;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.ui.widget.VerticalSeekBar;
import com.sunlands.intl.yingshi.util.DLog;
import com.sunlands.intl.yingshi.util.Utils;

import java.io.File;

/**
 * 资料详情页
 */

public class MaterialActivity extends CommonActivity<Object> {


    TextView error;
    LinearLayout seekbarLl;
    PDFView mPDFView;
    TextView tvCurrentPage;
    VerticalSeekBar seekBar;
    TextView tvTotalPage;
    TextView pageCommon;
    TextView mTvProgress;
    ConstraintLayout mClProgress;

    float currentProgress;
    int currentPage = 0;

    private String mFileUrl;
    private String mFileName;
    private File mFile;
    private String mFilePath;
    private String CACHE_SUFFIX = "_cache";
    private String filePath;
    private String title;


    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        error = FBIA(R.id.error);
        seekbarLl = FBIA(R.id.seekbar_ll);
        mPDFView = FBIA(R.id.pdfView);
        tvCurrentPage = FBIA(R.id.tv_currentPage);
        seekBar = FBIA(R.id.seekBar);
        tvTotalPage = FBIA(R.id.tv_totalPage);
        pageCommon = FBIA(R.id.page_common);
        mTvProgress = FBIA(R.id.tv_progress);
        mClProgress = FBIA(R.id.cl_progress);

    }

    @Override
    public void initListener() {
        super.initListener();
        seekBar.setOnSlideChangeListener(new VerticalSeekBar.SlideChangeListener() {
            @Override
            public void onStart(VerticalSeekBar slideView, int progress) {

            }

            @Override
            public void onProgress(VerticalSeekBar slideView, int progress) {

                float v = mPDFView.getPageCount() * progress / 1000000;
                mPDFView.jumpTo((int) v, false);

            }

            @Override
            public void onStop(VerticalSeekBar slideView, int progress) {

            }
        });

    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
        mFileUrl = getIntent().getStringExtra(Constants.Key.KEY_FILE_PATH);
        title = getIntent().getStringExtra(Constants.Key.KEY_FILE_NAME);
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();

        if (mFileUrl.length() > 11) {
            filePath = Constants.CACHE_DIR + "/" + mFileUrl.substring(mFileUrl.length() - 10, mFileUrl.length());
            mFileName = mFileUrl.substring(mFileUrl.length() - 10, mFileUrl.length()) + CACHE_SUFFIX;
        } else {
            filePath = Constants.CACHE_DIR + "/" + mFileUrl;
            mFileName = mFileUrl + CACHE_SUFFIX;
        }

        mFilePath = Constants.CACHE_DIR + File.separator + mFileName;
        requestPermission();
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        pageCommon.getBackground().setAlpha(200);
        tvCurrentPage.getBackground().setAlpha(190);
        seekbarLl.setVisibility(View.GONE);
        seekBar.setOrientation(1);
        seekBar.setSelectColor(Color.parseColor("#e78a3e"));
        seekBar.setUnSelectColor(Color.parseColor("#E3E3E3"));
        seekBar.setmInnerProgressWidth(2);
        seekBar.setThumbSize(5, 5);
        seekBar.setThumb(R.drawable.color_swipe);
        hideProgressBar();
        Aria.download(this).register();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_handout_detail;
    }

    @Override
    public String getTitleText() {
        return title;
    }

    private void requestPermission() {

        mFile = new File(filePath);
        if (mFile.exists()) {
            show(mFile);
        } else {
            downloadPdf2(mFileUrl);
        }
    }

    private void show(File mFile) {

        mPDFView.fromFile(mFile)
                //是否允许翻页，默认是允许翻页
                .enableSwipe(true)
                //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .swipeHorizontal(false)
                .enableDoubletap(false)
                //设置默认显示第0页
                .defaultPage(0)
                //设置翻页监听
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        //当前page
                        currentPage = page;
                        if (page == 0) {
                            currentPage = 1;
                        }
                        tvCurrentPage.setText("第 " + (currentPage) + " 页");
                        tvTotalPage.setText(pageCount + "");
                        pageCommon.setText((currentPage) + "/" + pageCount);

                    }
                })
                .onPageScroll(new OnPageScrollListener() {
                    @Override
                    public void onPageScrolled(int page, float positionOffset) {

                        currentProgress = positionOffset;
                      //  seekbarLl.setVisibility(View.VISIBLE);
                        seekBar.setProgress((int) (currentProgress * 1000000));
                        seekBar.setMaxProgress(1000000);
                        if (positionOffset == 0.0) {
                            currentPage = 0;
                        }
                        Utils.getMainThreadHandler().removeCallbacksAndMessages(null);
                        Utils.getMainThreadHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                seekbarLl.setVisibility(View.GONE);
                            }
                        }, 5000);

                    }
                })
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        error.setVisibility(View.VISIBLE);
                    }
                })
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

    private void downloadPdf2(String fileUrl) {

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
    }

    @Download.onTaskRunning()
    void taskRunning(DownloadTask task) {
        DLog.d(task.getTaskName() + ", " + task.getState() + ", progress: " + task.getPercent());
      //  mTvProgress.setText(String.format("%s%s%%", getString(R.string.file_downloading), task.getPercent()));
    }

    private void showProgressBar(int progress) {
        mClProgress.setVisibility(View.VISIBLE);
        mTvProgress.setText( getString(R.string.file_downloading));
    }

    private void hideProgressBar() {
        mClProgress.setVisibility(View.GONE);
    }

}
