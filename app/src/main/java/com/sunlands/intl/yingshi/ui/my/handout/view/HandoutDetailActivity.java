package com.sunlands.intl.yingshi.ui.my.handout.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.ToastUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.PageBean;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.ui.widget.VerticalSeekBar;
import com.sunlands.intl.yingshi.util.DLog;
import com.sunlands.intl.yingshi.util.SPHelper;
import com.sunlands.intl.yingshi.util.StringUtils;
import com.sunlands.intl.yingshi.util.Utils;

import java.io.File;
import java.util.List;


//我的讲义
public class HandoutDetailActivity extends CommonActivity<Object> {

    TextView error;
    LinearLayout seekbarLl;
    PDFView mPDFView;
    TextView tvCurrentPage;
    VerticalSeekBar seekBar;
    TextView tvTotalPage;
    TextView pageCommon;

    private String mFilePath;
    List<PageBean> datalist;
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

    }
    public static void show(Context context, String filePath, String title) {
        Intent intent = new Intent(context, HandoutDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.Key.KEY_FILE_PATH, filePath);
        intent.putExtra(Constants.Key.KEY_FILE_NAME, title);
        context.startActivity(intent);
    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
        mFilePath = getIntent().getStringExtra(Constants.Key.KEY_FILE_PATH);
        title = getIntent().getStringExtra(Constants.Key.KEY_FILE_NAME);
        title = title.replaceAll("%20", " ");
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        initView();
    }

    protected void initView() {
        pageCommon.getBackground().setAlpha(200);
        tvCurrentPage.getBackground().setAlpha(190);
        seekbarLl.setVisibility(View.GONE);
        seekBar.setOrientation(1);
        seekBar.setSelectColor(Color.parseColor("#e78a3e"));
        seekBar.setUnSelectColor(Color.parseColor("#E3E3E3"));
        seekBar.setmInnerProgressWidth(2);
        seekBar.setThumbSize(5, 5);
        seekBar.setThumb(R.drawable.color_swipe);
        datalist = SPHelper.getDataList();
        currentPage = SPHelper.getCurrentPage(datalist, mFilePath);
        requestPermission();
    }

    private void requestPermission() {
        openPdf(mFilePath);
    }

    private boolean openPdf(String filePath) {
        //  showProgressBar();
        DLog.d(filePath);
        if (StringUtils.isEmpty(filePath)) {
            ToastUtils.showShort("文件地址为空");
            return false;
        }
        File file = new File(filePath.trim());
        if (!file.exists()) {
            ToastUtils.showShort("文件不存在");
            return false;
        }
        show(file);

        return true;
    }

    int currentPage = 0;

    private void show(File file) {

        mPDFView.fromFile(file)
                //是否允许翻页，默认是允许翻页
                .enableSwipe(true)
                //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .swipeHorizontal(false)
                .enableDoubletap(false)
                //设置默认显示第0页
                .defaultPage(currentPage == 1 ? 0 : currentPage)
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
                        seekbarLl.setVisibility(View.VISIBLE);
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_handout_detail;
    }

    @Override
    public String getTitleText() {
        return title;
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

    float currentProgress;

    @Override
    protected void onStop() {
        super.onStop();
        if (isFinishing()) {
            PageBean pageBean = new PageBean();
            pageBean.setPage(currentPage);
            pageBean.setFilePath(mFilePath);
            pageBean.setProgress((int) (currentProgress * 100) + "%");
            for (int i = 0; i < datalist.size(); i++) {
                String filePath = datalist.get(i).getFilePath();
                if (filePath.equals(mFilePath)) {
                    datalist.remove(i);
                }
            }
            datalist.add(pageBean);
            SPHelper.setDataList(datalist);
        }

    }
}
