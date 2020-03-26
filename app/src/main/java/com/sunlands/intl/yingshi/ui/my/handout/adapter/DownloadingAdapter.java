/*
 * Copyright (C) 2016 AriaLyy(https://github.com/AriaLyy/Aria)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sunlands.intl.yingshi.ui.my.handout.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.inf.AbsTaskWrapper;
import com.arialyy.aria.core.inf.IEntity;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.greendao.MyDownLoadInfoDao;
import com.sunlands.intl.yingshi.greendao.db.MyDownLoadInfo;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.ui.my.base.AbsHolder;
import com.sunlands.intl.yingshi.ui.my.base.AbsRVAdapter;
import com.sunlands.intl.yingshi.ui.my.player.SystemAudioPlayer;
import com.sunlands.intl.yingshi.ui.widget.HorizontalProgressBarWithNumber;
import com.sunlands.intl.yingshi.util.SPHelper;
import com.sunlands.sunlands_live_sdk.offline.DownloadInfoMode;
import com.sunlands.sunlands_live_sdk.offline.OfflineManager;
import com.sunlands.sunlands_live_sdk.offline.listener.DownLoadObserver;
import com.sunlands.sunlands_live_sdk.offline.listener.OfflineListener;
import com.sunlands.sunlands_live_sdk.websocket.packet.roomclient.PlatformInitParam;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.sunlands.intl.yingshi.ui.my.handout.view.DownLoadListFragment.KEJIAN;
import static com.sunlands.intl.yingshi.ui.my.handout.view.DownLoadListFragment.SHIPIN;
import static com.sunlands.intl.yingshi.ui.my.handout.view.DownLoadListFragment.YINPIN;
import static com.sunlands.sunlands_live_sdk.offline.listener.Status.STATUS_COMPLETE;
import static com.sunlands.sunlands_live_sdk.offline.listener.Status.STATUS_CONNECTING;
import static com.sunlands.sunlands_live_sdk.offline.listener.Status.STATUS_CONNECT_ERROR;
import static com.sunlands.sunlands_live_sdk.offline.listener.Status.STATUS_DOWNLOADING;
import static com.sunlands.sunlands_live_sdk.offline.listener.Status.STATUS_DOWNLOAD_ERROR;
import static com.sunlands.sunlands_live_sdk.offline.listener.Status.STATUS_NOT_DOWNLOAD;
import static com.sunlands.sunlands_live_sdk.offline.listener.Status.STATUS_PAUSED;

/**
 * Created by Lyy on 2016/9/27.
 * 下载列表适配器
 */
public class DownloadingAdapter extends AbsRVAdapter<MyDownLoadInfo, DownloadingAdapter.SimpleHolder> {

    private Map<String, Integer> mPositions = new ConcurrentHashMap<>();

    public DownloadingAdapter(Context context, List<MyDownLoadInfo> data) {
        super(context, data);
        int i = 0;
        for (MyDownLoadInfo entity : data) {
            mPositions.put(getKey(entity.getDownloadEntity()), i);
            i++;
        }
    }

    private String getKey(DownloadEntity entity) {
        return entity.getUrl();
    }

    @Override
    protected SimpleHolder getViewHolder(View convertView, int viewType) {
        return new SimpleHolder(convertView);
    }


    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    protected int setLayoutId(int type) {
        return R.layout.layout_item_down_loading;
    }

    public synchronized void updateState(DownloadEntity entity) {

        if (entity.getState() == IEntity.STATE_CANCEL | entity.getState() == IEntity.STATE_COMPLETE) {
            mData.remove(indexItem(getKey(entity)));
            mPositions.clear();
            int i = 0;
            for (MyDownLoadInfo entity_1 : mData) {
                mPositions.put(getKey(entity_1.getDownloadEntity()), i);
                i++;
            }
            notifyDataSetChanged();
        } else {
            getKey(entity);
            int position = indexItem(getKey(entity));
            if (position == -1 || position >= mData.size()) {
                return;
            }
            MyDownLoadInfo myDownLoadInfo = mData.get(position);
            myDownLoadInfo.setDownloadEntity(entity);
            mData.set(position, myDownLoadInfo);
            notifyItemChanged(position);
        }
    }

    /**
     * 更新进度
     *
     * @param entity
     */
    public synchronized void setProgress(DownloadEntity entity) {
        String url = entity.getKey();
        int position = indexItem(url);
        if (position == -1 || position >= mData.size()) {
            return;
        }
        MyDownLoadInfo myDownLoadInfo = mData.get(position);
        myDownLoadInfo.setDownloadEntity(entity);
        mData.set(position, myDownLoadInfo);
        notifyItemChanged(position, entity);
    }

    private synchronized int indexItem(String url) {
        Set<String> keys = mPositions.keySet();
        for (String key : keys) {
            if (key.equals(url)) {
                return mPositions.get(key);
            }
        }
        return -1;
    }

    @Override
    protected void bindData(SimpleHolder holder, int position, final MyDownLoadInfo item) {
        handleProgress(holder, item);
    }

    @Override
    protected void bindData(SimpleHolder holder, int position, MyDownLoadInfo item,
                            List<Object> payloads) {
        DownloadEntity entity = (DownloadEntity) payloads.get(0);
        updateSpeed(holder, entity);
    }

    /**
     * 只更新速度
     */
    private void updateSpeed(SimpleHolder holder, final DownloadEntity entity) {
        long size = entity.getFileSize();
        long progress = entity.getCurrentProgress();
        int current = size == 0 ? 0 : (int) (progress * 100 / size);
        holder.state_text.setProgress(current);
        holder.tvState.setText(entity.getConvertSpeed());
    }

    @SuppressLint("SetTextI18n")
    private void handleProgress(SimpleHolder holder, final MyDownLoadInfo entity) {

        DownLoadObserver downLoadObserver = new DownLoadObserver() {
            @Override
            public void onDownLoadInfoChange(DownloadInfoMode downloadInfoMode) {
                int state = downloadInfoMode.getState();
                if (state == STATUS_DOWNLOADING) {
                    if (downloadInfoMode.getTotalSize() > 0) {
                        holder.state_text.setProgress((int) (downloadInfoMode.getFinish() * 100 / downloadInfoMode.getTotalSize()));
                        entity.getDownloadEntity().setFileSize(downloadInfoMode.getTotalSize());
                        entity.getDownloadEntity().save();
                    }
                    return;
                }
                String str = "";
                if (state == STATUS_NOT_DOWNLOAD) {
                    str = "未下载";
                } else if (state == STATUS_CONNECTING) {
                    str = "等待中";
                } else if (state == STATUS_CONNECT_ERROR) {
                    str = "连接错误";
                } else if (state == STATUS_PAUSED) {
                    str = "暂停";
                } else if (state == STATUS_DOWNLOAD_ERROR) {
                    str = "下载失败";
                } else if (state == STATUS_COMPLETE) {
                    str = "已下载";
                    entity.getDownloadEntity().setState(IEntity.STATE_COMPLETE);
                    entity.getDownloadEntity().save();
                    OfflineManager.getInstance().removeObserver(entity.getCourseId() + "");
                }
            }
        };
        String str = "";
        switch (entity.getDownloadEntity().getState()) {
            case IEntity.STATE_WAIT:
                str = "等待中";
                DownloadEntity downloadEntity = Aria.download(this).getDownloadEntity(entity.getDownloadEntity().getUrl());
                if (downloadEntity == null | entity.getDownloadEntity().getUrl().length() < 15) {
                    str = "等待中";
                }
                break;
            case IEntity.STATE_OTHER:
            case IEntity.STATE_FAIL:
                if (SHIPIN.equals(entity.getFileType()) | entity.getDownloadEntity().getUrl().length() < 15) {
                    str = "未下载";
                    sunlandLiveFailed(holder, entity, downLoadObserver);
                } else {
                    str = "下载失败";
                }
                break;
            case IEntity.STATE_STOP:
                str = "暂停";
                break;
            case IEntity.STATE_PRE:
            case IEntity.STATE_POST_PRE:
            case IEntity.STATE_RUNNING:
                long size = entity.getDownloadEntity().getFileSize();
                long progress = entity.getDownloadEntity().getCurrentProgress();
                int current = size == 0 ? 0 : (int) (progress * 100 / size);
                holder.state_text.setProgress(current);
                str = "正在下载";
                break;
            case IEntity.STATE_COMPLETE:
                str = "已下载";
                break;

        }
        holder.tvState.setText(str);
        holder.name.setText(entity.getDownloadEntity().getFileName());
        long size = entity.getDownloadEntity().getFileSize();
        long progress = entity.getDownloadEntity().getCurrentProgress();
        int current = size == 0 ? 0 : (int) (progress * 100 / size);
        holder.state_text.setProgress(current);
        holder.tvFileSize.setText(entity.getDownloadEntity().getConvertFileSize());
        if (entity.getFileType().equals(SHIPIN)) {
            holder.tv_type.setImageResource(R.drawable.ic_sinpin);
        } else if (entity.getFileType().equals(YINPIN)) {
            holder.tv_type.setImageResource(R.drawable.ic_yinpin);
        } else if (entity.getFileType().equals(KEJIAN)) {
            holder.tv_type.setImageResource(R.drawable.ic_kejian);
        } else {
            holder.tv_type.setImageResource(R.drawable.ic_qita);
        }
        String isCheck = entity.getDownloadEntity().getMd5Code();
        setCheckBoxShow(holder.checkBox);
        if (("true").equals(isCheck)) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nowBeanChecked = ("true").equals(entity.getDownloadEntity().getMd5Code()) ? "false" : "true";
                //更新数据
                entity.getDownloadEntity().setMd5Code(nowBeanChecked);
                notifyDataSetChanged();
                //控制总checkedbox 接口
                if (onAllCheckedBoxNeedChangeListener != null) {
                    onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllIsChecked());
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.getVisibility() == View.VISIBLE) {
                    holder.checkBox.performClick();
                    return;
                }
                if (SHIPIN.equals(entity.getFileType())) {
                    sunlandLiveClick(entity, downLoadObserver);
                    return;
                }

                if (entity.getDownloadEntity().getState() == IEntity.STATE_RUNNING) {
                    stop(entity);
                } else if (entity.getDownloadEntity().getState() == IEntity.STATE_COMPLETE) {
                    Intent intent = new Intent(mContext, SystemAudioPlayer.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", (Serializable) entity.getDownloadEntity());//序列化,要注意转化(Serializable)
                    try {
                        if (SystemAudioPlayer.service.getAudioPath().equals(entity.getDownloadEntity().getDownloadPath())) {
                            intent.putExtra("notification", true);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    intent.putExtras(bundle);//发送数据
                    mContext.startActivity(intent);
                } else {
                    start(entity);
                }
                notifyDataSetChanged();
            }
        });
        if (SHIPIN.equals(entity.getFileType())) {
            sunlandLiveState(holder, entity, downLoadObserver);
        }
    }

    private void sunlandLiveClick(MyDownLoadInfo entity, DownLoadObserver downLoadObserver) {
        int state = OfflineManager.getInstance().getDownloadState(entity.getCourseId() + "");
        if (state == STATUS_NOT_DOWNLOAD) {
            // str = "未下载";
            startDownLoad(entity, downLoadObserver);
        } else if (state == STATUS_CONNECTING) {
            // str = "等待中";
        } else if (state == STATUS_CONNECT_ERROR) {
            // str = "连接错误";
        } else if (state == STATUS_DOWNLOADING) {
            //  str = "正在下载";
            OfflineManager.getInstance().pauseDownload(entity.getCourseId() + "");
        } else if (state == STATUS_PAUSED) {
            //  str = "暂停";
            startDownLoad(entity, downLoadObserver);
        } else if (state == STATUS_DOWNLOAD_ERROR) {
            //  str = "下载失败";
            startDownLoad(entity, downLoadObserver);
        } else if (state == STATUS_COMPLETE) {
            // str = "已下载";
        }
    }

    private void sunlandLiveState(SimpleHolder holder, MyDownLoadInfo entity, DownLoadObserver downLoadObserver) {
        String str = "";
        DownloadInfoMode downloadInfoMode = OfflineManager.getInstance().getDownLoadInfo(entity.getCourseId() + "");
        int state = OfflineManager.getInstance().getDownloadState(entity.getCourseId() + "");
        if (downloadInfoMode != null) {
            int current00 = (int) (downloadInfoMode.getFinish() * 100 / downloadInfoMode.getTotalSize());
            if (state == STATUS_NOT_DOWNLOAD) {
                str = "未下载";
            } else if (state == STATUS_CONNECTING) {
                str = "等待中";
            } else if (state == STATUS_CONNECT_ERROR) {
                str = "连接错误";
            } else if (state == STATUS_DOWNLOADING) {
                holder.state_text.setProgress(current00);
                startDownLoad(entity, downLoadObserver);
            } else if (state == STATUS_PAUSED) {
                str = "暂停";
            } else if (state == STATUS_DOWNLOAD_ERROR) {
                str = "下载失败";
            } else if (state == STATUS_COMPLETE) {
                str = "已下载";
            }
        }
    }

    private void sunlandLiveFailed(SimpleHolder holder, MyDownLoadInfo entity, DownLoadObserver downLoadObserver) {

        String str = "";
        DownloadInfoMode downloadInfoMode = OfflineManager.getInstance().getDownLoadInfo(entity.getCourseId() + "");
        int state = OfflineManager.getInstance().getDownloadState(entity.getCourseId() + "");
        if (state == STATUS_NOT_DOWNLOAD) {
            str = "未下载";
            startDownLoad(entity, downLoadObserver);
        } else if (state == STATUS_CONNECTING) {
            str = "等待中";
        } else if (state == STATUS_CONNECT_ERROR) {
            str = "连接错误";
        } else if (state == STATUS_DOWNLOADING) {
            str = "正在下载";
            startDownLoad(entity, downLoadObserver);
        } else if (state == STATUS_PAUSED) {
            str = "暂停";
        } else if (state == STATUS_DOWNLOAD_ERROR) {
            str = "下载失败";
        } else if (state == STATUS_COMPLETE) {
            str = "已下载";
        }
        if (downloadInfoMode != null) {
            int current00 = (int) (downloadInfoMode.getFinish() * 100 / downloadInfoMode.getTotalSize());
            holder.state_text.setProgress(current00);
        }
    }

    private void startDownLoad(MyDownLoadInfo entity, DownLoadObserver downLoadObserver) {
        OfflineManager.getInstance().removeObserver(entity.getCourseId() + "");
        OfflineManager.getInstance().addDownLoadObserver(entity.getCourseId() + "", downLoadObserver);
        PlatformInitParam platformInitParam = new PlatformInitParam("c761caf9e20e89cb2f89b57d6d2e3d65",
                1, entity.getCourseId(),
                227 + "", "严新",
                1, "https://education-1254383113.file.myqcloud.com/115586469379429.png", 1583387980);
        OfflineManager.getInstance().startDownload(platformInitParam, new OfflineListener() {
            @Override
            public void success() {
                Log.e("TAG", "成功");
            }

            @Override
            public void fail(String s, Exception e) {
                Log.e("TAG", s + "==" + e.getMessage());
            }
        });
        MyDownLoadInfo unique = DbHelper.getInstance().getMyDownLoadInfoDao().queryBuilder()
                .where(MyDownLoadInfoDao.Properties.UserId.eq(LoginUserInfoHelper.getInstance().getUserInfo().getUserId()))
                .where(MyDownLoadInfoDao.Properties.ProduceId.eq(entity.getProduceId()))
                .where(MyDownLoadInfoDao.Properties.CourseId.eq(entity.getCourseId()))
                .where(MyDownLoadInfoDao.Properties.FileId.eq(entity.getFileId()))
                .build().unique();
        if (unique == null) {
            DbHelper.getInstance().getMyDownLoadInfoDao().insert(entity);
        }
    }

    private void cancel(MyDownLoadInfo entity) {
        switch (entity.getDownloadEntity().getTaskType()) {
            case AbsTaskWrapper.D_HTTP:
                Aria.download(getContext()).load(entity.getDownloadEntity()).cancel(true);
                break;
        }
    }

    private void start(MyDownLoadInfo entity) {

        switch (entity.getDownloadEntity().getTaskType()) {
            case AbsTaskWrapper.D_HTTP:
                String mFilePath = Constants.CACHE_DIR + File.separator + (entity).getDownloadEntity().getFileName();
                Aria.download(getContext()).load(entity.getDownloadEntity()).setFilePath(mFilePath).start();
                //添加到数据库
                MyDownLoadInfo unique = DbHelper.getInstance().getMyDownLoadInfoDao().queryBuilder()
                        .where(MyDownLoadInfoDao.Properties.UserId.eq(LoginUserInfoHelper.getInstance().getUserInfo().getUserId()))
                        .where(MyDownLoadInfoDao.Properties.ProduceId.eq(entity.getProduceId()))
                        .where(MyDownLoadInfoDao.Properties.CourseId.eq(entity.getCourseId()))
                        .where(MyDownLoadInfoDao.Properties.FileId.eq(entity.getFileId()))
                        .build().unique();
                if (unique == null) {
                    DbHelper.getInstance().getMyDownLoadInfoDao().insert(entity);
                }
                break;
        }
    }

    private void stop(MyDownLoadInfo entity) {
        switch (entity.getDownloadEntity().getTaskType()) {
            case AbsTaskWrapper.D_HTTP:
                Aria.download(getContext()).load(entity.getDownloadEntity()).stop();
                break;
        }
    }

    class SimpleHolder extends AbsHolder {

        ImageView tv_type;
        HorizontalProgressBarWithNumber state_text;
        TextView tvState;
        TextView name;
        CheckBox checkBox;
        TextView tvFileSize;

        SimpleHolder(View itemView) {
            super(itemView);
            state_text = itemView.findViewById(R.id.progressBar);
            tv_type = itemView.findViewById(R.id.tv_type);
            name = itemView.findViewById(R.id.name);
            checkBox = itemView.findViewById(R.id.checkbox);
            tvFileSize = itemView.findViewById(R.id.tv_file_size);
            tvState = itemView.findViewById(R.id.tv_state);
        }
    }

    OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener;

    OnCheckHasGoodsListener onCheckHasGoodsListener;

    public void setOnCheckHasGoodsListener(OnCheckHasGoodsListener onCheckHasGoodsListener) {
        this.onCheckHasGoodsListener = onCheckHasGoodsListener;
    }


    public void setOnAllCheckedBoxNeedChangeListener(OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener) {
        this.onAllCheckedBoxNeedChangeListener = onAllCheckedBoxNeedChangeListener;
    }

    public interface OnAllCheckedBoxNeedChangeListener {
        void onCheckedBoxNeedChange(boolean allParentIsChecked);
    }

    public interface OnCheckHasGoodsListener {
        void onCheckHasGoods(boolean isHasGoods);
    }

    //checkBox的显示和隐藏

    private void setCheckBoxShow(CheckBox checkBox) {

        boolean selectAllShow = SPHelper.getSelectAllDownLoad();
        if (selectAllShow == true) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.GONE);
        }
    }

    //全选  取消全选
    public void setupAllChecked(boolean b) {
        for (int i = 0; i < mData.size(); i++) {
            MyDownLoadInfo absEntity = mData.get(i);
            absEntity.getDownloadEntity().setMd5Code(b == true ? "true" : "false");
        }
        notifyDataSetChanged();
    }

    public boolean dealAllIsChecked() {
        for (int i = 0; i < mData.size(); i++) {
            MyDownLoadInfo absEntity = mData.get(i);
            if (!("true").equals(absEntity.getDownloadEntity().getMd5Code())) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }

    public void remove() {
        for (int i = mData.size() - 1; i >= 0; i--) {//倒过来遍历  remove
            MyDownLoadInfo absEntity = mData.get(i);
            String isCheck = (absEntity.getDownloadEntity().getMd5Code());
            if (("true").equals(isCheck) ? true : false) {
                mData.remove(i);
                cancel(absEntity);
                try {
                    DbHelper.getInstance().getMyDownLoadInfoDao().delete(absEntity);
                    OfflineManager.getInstance().deleteDownload(absEntity.getCourseId() + "");
                } catch (Exception x) {

                }
            }

        }
        if (mData != null && mData.size() > 0) {
            onCheckHasGoodsListener.onCheckHasGoods(true);//
        } else {
            onCheckHasGoodsListener.onCheckHasGoods(false);//
        }
        notifyDataSetChanged();//
    }

    public boolean isCheck() {

        for (int i = mData.size() - 1; i >= 0; i--) {//倒过来遍历  remove
            MyDownLoadInfo absEntity = mData.get(i);
            if (("true").equals(absEntity.getDownloadEntity().getMd5Code()) ? true : false) {
                return true;
            }
        }

        return false;
    }

}