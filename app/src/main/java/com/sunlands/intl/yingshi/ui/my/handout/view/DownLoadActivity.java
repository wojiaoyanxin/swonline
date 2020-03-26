package com.sunlands.intl.yingshi.ui.my.handout.view;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.download.DownloadTarget;
import com.arialyy.aria.core.download.DownloadTask;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.dialog.DialogUtils;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.ui.my.view.MyMaterialActivity;
import com.tencent.liteav.demo.play.bean.MaterialBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.arialyy.aria.core.inf.IEntity.STATE_CANCEL;
import static com.arialyy.aria.core.inf.IEntity.STATE_COMPLETE;
import static com.arialyy.aria.core.inf.IEntity.STATE_FAIL;
import static com.arialyy.aria.core.inf.IEntity.STATE_OTHER;
import static com.arialyy.aria.core.inf.IEntity.STATE_POST_PRE;
import static com.arialyy.aria.core.inf.IEntity.STATE_RUNNING;
import static com.arialyy.aria.core.inf.IEntity.STATE_STOP;
import static com.arialyy.aria.core.inf.IEntity.STATE_WAIT;

public class DownLoadActivity extends CommonActivity<MaterialBean> {


    private String courseId;
    private TextView downloadCount;
    private CommonAdapter commonAdapter;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);

        FBIA(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MyMaterialActivity.class);
            }
        });
        downloadCount = findViewById(R.id.download_count);
    }

    @Override
    public void onResume() {
        super.onResume();
        setCounts();
        if (commonAdapter != null) {
            commonAdapter.notifyDataSetChanged();
        }
    }

    private void setCounts() {
        downloadCount.setVisibility(View.GONE);
        List<DownloadEntity> allNotCompleteTask = Aria.download(this).getAllNotCompleteTask();
        List<DownloadEntity> allNotCompleteTask2 = new ArrayList<>();
        if (allNotCompleteTask == null) {
            return;
        }
        for (int i = 0; i < allNotCompleteTask.size(); i++) {
            DownloadEntity downloadEntity = allNotCompleteTask.get(i);
            MaterialBean.ListBean listBean1 = new Gson().fromJson(((DownloadEntity) downloadEntity).getMd5Code(), MaterialBean.ListBean.class);
            if (LoginUserInfoHelper.getInstance().getUserInfo().getUserId() == listBean1.getUserId()) {
                allNotCompleteTask2.add(downloadEntity);
            }
        }
        if (allNotCompleteTask2.size() != 0) {
            downloadCount.setVisibility(View.VISIBLE);
            downloadCount.setText(allNotCompleteTask2.size() + "");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_down_load;
    }

    @Override
    public String getTitleText() {
        return "本期资料";
    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
        courseId = getIntent().getStringExtra(Constants.Key.KEY_COURSE_ID);
        Aria.download(this).register();
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        getDataNet(true, courseId);
    }

    @Override
    public void onSuccess(MaterialBean bean) {

        super.onSuccess(bean);

        List<MaterialBean.ListBean> list = bean.getList();

        if (list == null) {
            return;
        }

        /**
         * 其它状态int STATE_OTHER = -1;
         * 失败状态STATE_FAIL = 0;
         * 完成状态int STATE_COMPLETE = 1;
         * 停止状态int STATE_STOP = 2;
         * 等待状态int STATE_WAIT = 3;
         * 正在执行int STATE_RUNNING = 4;
         * 预处理int STATE_PRE = 5;
         * 预处理完成 int STATE_POST_PRE = 6;
         * 删除任务 int STATE_CANCEL = 7;
         */
        commonAdapter = new CommonAdapter<MaterialBean.ListBean>(this, list, R.layout.materail_list_download_layout) {

            @Override
            public void convert(ViewHolder headholder, MaterialBean.ListBean listBean) {

                headholder.setText(R.id.materail_name, listBean.getFile_name()).setText(R.id.materail_size, listBean.getFile_size())
                        .setText(R.id.materail_time, listBean.getCreated_at());

                ImageView downloadStatesImg = headholder.getView(R.id.download_states);
                DownloadTarget target = Aria.download(this).load(listBean.getUrl());
                DownloadEntity downloadEntity = Aria.download(this).getDownloadEntity(listBean.getUrl());
                /**
                 * 其它状态int STATE_OTHER = -1;  * 失败状态STATE_FAIL = 0;
                 * 完成状态int STATE_COMPLETE = 1;* 停止状态int STATE_STOP = 2;
                 * 等待状态int STATE_WAIT = 3; * 正在执行int STATE_RUNNING = 4;
                 * 预处理int STATE_PRE = 5;   * 预处理完成 int STATE_POST_PRE = 6;  * 删除任务 int STATE_CANCEL = 7;
                 */
                //downloadStatesImg.setVisibility(View.INVISIBLE);

                if (downloadEntity == null) {
                    downloadStatesImg.setVisibility(View.INVISIBLE);
                } else {
                    MaterialBean.ListBean listBean1 = new Gson().fromJson(((DownloadEntity) downloadEntity).getMd5Code(), MaterialBean.ListBean.class);
                    if (LoginUserInfoHelper.getInstance().getUserInfo().getUserId() == listBean1.getUserId()) {
                        if (downloadEntity.getState() == STATE_COMPLETE) {
                            downloadStatesImg.setVisibility(View.VISIBLE);
                            downloadStatesImg.setImageResource(R.drawable.download_success);
                        } else if (downloadEntity.getState() == STATE_CANCEL) { // 删除任务
                            downloadStatesImg.setVisibility(View.INVISIBLE);
                        } else if (downloadEntity.getState() == STATE_FAIL) { //失败
                            downloadStatesImg.setVisibility(View.VISIBLE);
                            downloadStatesImg.setImageResource(R.drawable.download_failed);
                        } else if (downloadEntity.getState() == STATE_OTHER |
                                downloadEntity.getState() == STATE_POST_PRE |
                                downloadEntity.getState() == STATE_RUNNING |
                                downloadEntity.getState() == STATE_WAIT |
                                downloadEntity.getState() == STATE_STOP) {
                            downloadStatesImg.setVisibility(View.VISIBLE);
                            downloadStatesImg.setImageResource(R.drawable.icon_download);
                        }
                    } else {
                        downloadStatesImg.setVisibility(View.INVISIBLE);
                    }
                }
                headholder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DownloadEntity downloadEntity = Aria.download(this).getDownloadEntity(listBean.getUrl());
                        if (downloadEntity == null) {
                            String mFilePath = Constants.CACHE_DIR + File.separator + System.currentTimeMillis() + ".pdf";
                            downloadStatesImg.setVisibility(View.VISIBLE);
                            downloadStatesImg.setImageResource(R.drawable.icon_download);
                            listBean.setFilePath(mFilePath);
                            listBean.setUserId(LoginUserInfoHelper.getInstance().getUserInfo().getUserId());
                            String s = new Gson().toJson(listBean);
                            target.getDownloadEntity().setMd5Code(s);
                            //  target.getDownloadEntity().setFileSize(Long.parseLong(listBean.getFile_size()));
                            Aria.download(this)
                                    .load(listBean.getUrl())
                                    .setFilePath(mFilePath)
                                    .start();
                            setCounts();
                        } else {
                            MaterialBean.ListBean listBean1 = new Gson().fromJson(((DownloadEntity) downloadEntity).getMd5Code(), MaterialBean.ListBean.class);
                            try {
                                boolean running = target.isRunning();
                                if (running == true && listBean1.getUserId() == LoginUserInfoHelper.getInstance().getUserInfo().getUserId()) {
                                    ToastUtils.showShort("该文件已在下载列表中");
                                } else if (downloadEntity != null && downloadEntity.getState() == STATE_COMPLETE && listBean1.getUserId() == LoginUserInfoHelper.getInstance().getUserInfo().getUserId()) {
                                    HandoutDetailActivity.show(mContext, downloadEntity.getDownloadPath(), listBean.getFile_name());
                                } else if (downloadEntity != null && downloadEntity.getState() == STATE_STOP && listBean1.getUserId() == LoginUserInfoHelper.getInstance().getUserInfo().getUserId()) {
                                   // target.resume();
                                    ToastUtils.showShort("该文件已在下载列表中");
                                } else if (downloadEntity != null && downloadEntity.getState() == STATE_WAIT && listBean1.getUserId() == LoginUserInfoHelper.getInstance().getUserInfo().getUserId()) {
                                    // target.resume();
                                    ToastUtils.showShort("该文件已在下载列表中");
                                }  else if (downloadEntity != null && downloadEntity.getState() == STATE_FAIL && listBean1.getUserId() == LoginUserInfoHelper.getInstance().getUserInfo().getUserId()) {
                                    DialogUtils.reDownLoad(mContext, new DialogUtils.onClick() {
                                        @Override
                                        public void sure() {
                                            target.resume();
                                            downloadStatesImg.setImageResource(R.drawable.icon_download);
                                            setCounts();
                                        }
                                    });
                                }else {
                                    String mFilePath = Constants.CACHE_DIR + File.separator + System.currentTimeMillis() + ".pdf";
                                    downloadStatesImg.setVisibility(View.VISIBLE);
                                    downloadStatesImg.setImageResource(R.drawable.icon_download);
                                    listBean.setFilePath(mFilePath);
                                    listBean.setUserId(LoginUserInfoHelper.getInstance().getUserInfo().getUserId());
                                    String s = new Gson().toJson(listBean);
                                    target.getDownloadEntity().setMd5Code(s);
                                    Aria.download(this)
                                            .load(listBean.getUrl())
                                            .setFilePath(mFilePath)
                                            .start();
                                    setCounts();
                                }
                            } catch (Exception e) {
                                ToastUtils.showShort("添加到下载任务失败");
                                e.printStackTrace();
                                notifyDataSetChanged();
                            }
                        }
                    }
                });
            }
        };
        mRecyclerView.setAdapter(commonAdapter);
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        setCounts();
        commonAdapter.notifyDataSetChanged();
    }

    @Download.onTaskFail
    void taskFail(DownloadTask task) {
        setCounts();
        commonAdapter.notifyDataSetChanged();
    }

}
