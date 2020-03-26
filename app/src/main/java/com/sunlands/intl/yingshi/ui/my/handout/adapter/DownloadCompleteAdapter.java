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
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.inf.AbsTaskWrapper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.greendao.db.MyDownLoadInfo;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.ui.my.base.AbsHolder;
import com.sunlands.intl.yingshi.ui.my.base.AbsRVAdapter;
import com.sunlands.intl.yingshi.ui.my.handout.view.HandoutDetailActivity;
import com.sunlands.intl.yingshi.ui.my.player.SystemAudioPlayer;
import com.sunlands.intl.yingshi.util.SPHelper;
import com.sunlands.sunlands_live_sdk.offline.OfflineManager;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.sunlands.intl.yingshi.ui.my.handout.view.DownLoadListFragment.KEJIAN;
import static com.sunlands.intl.yingshi.ui.my.handout.view.DownLoadListFragment.SHIPIN;
import static com.sunlands.intl.yingshi.ui.my.handout.view.DownLoadListFragment.YINPIN;

/**
 * Created by Lyy on 2016/9/27.
 * 已经下载列表适配器
 */
public class DownloadCompleteAdapter extends AbsRVAdapter<MyDownLoadInfo, DownloadCompleteAdapter.SimpleHolder> {

    private Map<String, Integer> mPositions = new ConcurrentHashMap<>();

    public DownloadCompleteAdapter(Context context, List<MyDownLoadInfo> data) {
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
        return R.layout.layout_item_download_complete;
    }

    @Override
    protected void bindData(SimpleHolder holder, int position, final MyDownLoadInfo item) {
        handleProgress(holder, item);
    }


    @SuppressLint("SetTextI18n")
    private void handleProgress(SimpleHolder holder, final MyDownLoadInfo entity) {

        holder.name.setText(entity.getDownloadEntity().getFileName());
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

                if (entity.getFileType().equals(SHIPIN)) {

                } else if (entity.getFileType().equals(YINPIN)) {
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
                } else if (entity.getFileType().equals(KEJIAN)) {
                    HandoutDetailActivity.show(mContext, entity.getDownloadEntity().getDownloadPath(), entity.getDownloadEntity().getFileName());
                } else {

                }
            }
        });
    }

    private void cancel(MyDownLoadInfo entity) {
        switch (entity.getDownloadEntity().getTaskType()) {
            case AbsTaskWrapper.D_HTTP:
                Aria.download(getContext()).load(entity.getDownloadEntity()).cancel(true);
                break;
        }
    }

    class SimpleHolder extends AbsHolder {

        ImageView tv_type;
        TextView name;
        CheckBox checkBox;
        ImageView iv_dot;

        SimpleHolder(View itemView) {
            super(itemView);
            tv_type = itemView.findViewById(R.id.tv_type);
            name = itemView.findViewById(R.id.name);
            checkBox = itemView.findViewById(R.id.checkbox);
            iv_dot = itemView.findViewById(R.id.iv_dot);
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