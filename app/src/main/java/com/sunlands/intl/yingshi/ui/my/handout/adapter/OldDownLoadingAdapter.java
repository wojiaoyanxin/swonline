package com.sunlands.intl.yingshi.ui.my.handout.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.download.DownloadGroupEntity;
import com.arialyy.aria.core.inf.AbsEntity;
import com.arialyy.aria.core.inf.AbsTaskWrapper;
import com.arialyy.aria.core.inf.IEntity;
import com.arialyy.aria.util.CommonUtil;
import com.google.gson.Gson;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.my.base.AbsHolder;
import com.sunlands.intl.yingshi.ui.my.base.AbsRVAdapter;
import com.sunlands.intl.yingshi.ui.widget.HorizontalProgressBarWithNumber;
import com.sunlands.intl.yingshi.util.SPHelper;
import com.tencent.liteav.demo.play.bean.MaterialBean;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yxin
 * @date 2020-03-25 - 12:11
 * @des
 */
public class OldDownLoadingAdapter extends AbsRVAdapter<AbsEntity, OldDownLoadingAdapter.SimpleHolder> {

    private Map<String, Integer> mPositions = new ConcurrentHashMap<>();

    public OldDownLoadingAdapter(Context context, List<AbsEntity> data) {
        super(context, data);
        int i = 0;
        for (AbsEntity entity : data) {
            mPositions.put(getKey(entity), i);
            i++;
        }
    }

    private String getKey(AbsEntity entity) {
        if (entity instanceof DownloadEntity) {
            return ((DownloadEntity) entity).getUrl();
        } else if (entity instanceof DownloadGroupEntity) {
            return ((DownloadGroupEntity) entity).getGroupHash();
        }
        return "";
    }

    @Override
    protected SimpleHolder getViewHolder(View convertView, int viewType) {
        return new SimpleHolder(convertView);
    }

    public void addDownloadEntity(DownloadEntity entity) {
        mData.add(entity);
        mPositions.put(entity.getUrl(), mPositions.size());
    }

    public int getItemViewType(int position) {
        AbsEntity entity = mData.get(position);
        if (entity instanceof DownloadEntity) return 1;
        if (entity instanceof DownloadGroupEntity) return 2;
        return -1;
    }

    @Override
    protected int setLayoutId(int type) {
        return R.layout.layout_item_old_progress;
    }

    public synchronized void updateState(AbsEntity entity) {
        if (entity.getState() == IEntity.STATE_CANCEL | entity.getState() == IEntity.STATE_COMPLETE) {
            mData.remove(entity);
            mPositions.clear();
            int i = 0;
            for (AbsEntity entity_1 : mData) {
                mPositions.put(getKey(entity_1), i);
                i++;
            }
            notifyDataSetChanged();
        } else {
            int position = indexItem(getKey(entity));
            if (position == -1 || position >= mData.size()) {
                return;
            }
            mData.set(position, entity);
            notifyItemChanged(position);
        }
    }

    /**
     * 更新进度
     */
    public synchronized void setProgress(AbsEntity entity) {
        String url = entity.getKey();
        int position = indexItem(url);
        if (position == -1 || position >= mData.size()) {
            return;
        }

        mData.set(position, entity);
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
    protected void bindData(SimpleHolder holder, int position, final AbsEntity item) {
        handleProgress(holder, item);
    }

    @Override
    protected void bindData(SimpleHolder holder, int position, AbsEntity item,
                            List<Object> payloads) {
        AbsEntity entity = (AbsEntity) payloads.get(0);
        updateSpeed(holder, entity);
    }

    /**
     * 只更新速度
     */
    private void updateSpeed(SimpleHolder holder, final AbsEntity entity) {
        long size = entity.getFileSize();
        long progress = entity.getCurrentProgress();
        int current = size == 0 ? 0 : (int) (progress * 100 / size);
        holder.speed.setText(entity.getConvertSpeed());
        //holder.fileSize.setText(covertCurrentSize(progress) + "/" + CommonUtil.formatFileSize(size));
        //  holder.fileSize.setText(CommonUtil.formatFileSize(size));
        holder.progress.setProgress(current);
    }

    @SuppressLint("SetTextI18n")
    private void handleProgress(SimpleHolder holder, final AbsEntity entity) {
        String str = "";
        int color = android.R.color.holo_green_light;
        switch (entity.getState()) {
            case IEntity.STATE_WAIT:
                str = "等待中";
                holder.speed.setText("等待中");
                holder.speed.setTextColor(Color.parseColor("#C7A17D"));
                break;
            case IEntity.STATE_OTHER:
            case IEntity.STATE_FAIL:
                str = "下载失败";
                holder.speed.setText("下载失败");
                holder.speed.setTextColor(Color.parseColor("#C7A17D"));
                break;
            case IEntity.STATE_STOP:
                str = "恢复";
                color = android.R.color.holo_blue_light;
                holder.speed.setText("已暂停");
                holder.speed.setTextColor(Color.parseColor("#C7A17D"));
                break;
            case IEntity.STATE_PRE:
            case IEntity.STATE_POST_PRE:
            case IEntity.STATE_RUNNING:
                str = "暂停";
                color = android.R.color.holo_red_light;
                holder.speed.setText(entity.getConvertSpeed());
                holder.speed.setTextColor(Color.parseColor("#999999"));
                break;
            case IEntity.STATE_COMPLETE:
                str = "完成";
                holder.progress.setProgress(100);
                break;
        }
        MaterialBean.ListBean listBean = new Gson().fromJson(((DownloadEntity) entity).getMd5Code(), MaterialBean.ListBean.class);
        long size = entity.getFileSize();
        long progress = entity.getCurrentProgress();
        int current = size == 0 ? 0 : (int) (progress * 100 / size);
        holder.bt.setText(str);
        holder.bt.setTextColor(getColor(color));
        holder.progress.setProgress(current);
        BtClickListener listener = new BtClickListener(entity);
        holder.bt.setOnClickListener(listener);
        holder.name.setText(listBean.getFile_name());
        // holder.fileSize.setText(CommonUtil.formatFileSize(size));
        holder.fileSize.setText(listBean.getFile_size());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entity.getState() == IEntity.STATE_RUNNING) {
                    stop(entity);
                } else {
                    start(entity);
                }
                notifyDataSetChanged();
            }
        });
        String isCheck = ((DownloadEntity) entity).getStr();
        setCheckBoxShow(holder.checkBox);
        if (("true").equals(isCheck)) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nowBeanChecked = ("true").equals(((DownloadEntity) entity).getStr()) ? "false" : "true";
                //更新数据
                ((DownloadEntity) entity).setStr(nowBeanChecked);
                notifyDataSetChanged();
                //控制总checkedbox 接口
                if (onAllCheckedBoxNeedChangeListener != null) {
                    onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllIsChecked());
                }
            }
        });
    }


    private String covertCurrentSize(long currentSize) {
        if (currentSize < 0) return "0";
        return CommonUtil.formatFileSize(currentSize);
    }

    private int getColor(int color) {
        return Resources.getSystem().getColor(color);
    }


    /**
     * 按钮事件
     */
    private class BtClickListener implements View.OnClickListener {

        private AbsEntity entity;

        BtClickListener(AbsEntity entity) {
            this.entity = entity;
        }

        @Override
        public void onClick(View v) {
            switch (entity.getState()) {
                case IEntity.STATE_WAIT:
                case IEntity.STATE_OTHER:
                case IEntity.STATE_FAIL:
                case IEntity.STATE_STOP:
                case IEntity.STATE_PRE:
                case IEntity.STATE_POST_PRE:
                    start(entity);
                    break;
                case IEntity.STATE_RUNNING:
                    stop(entity);
                    break;
                case IEntity.STATE_COMPLETE:
                    break;
            }
        }
    }

    private void cancel(AbsEntity entity) {
        switch (entity.getTaskType()) {
            case AbsTaskWrapper.D_FTP:
                Aria.download(getContext())
                        .loadFtp((DownloadEntity) entity)
                        .cancel(true);
                break;
            case AbsTaskWrapper.D_FTP_DIR:
                break;
            case AbsTaskWrapper.D_HTTP:
                Aria.download(getContext()).load((DownloadEntity) entity).cancel(true);
                break;
            case AbsTaskWrapper.DG_HTTP:
                Aria.download(getContext()).load((DownloadGroupEntity) entity).cancel(true);
                break;
        }
    }

    private void start(AbsEntity entity) {

        switch (entity.getTaskType()) {

            case AbsTaskWrapper.D_FTP:
                //Aria.download(getContext()).loadFtp((DownloadEntity) entity).login("lao", "123456").start();
                Aria.download(getContext()).loadFtp((DownloadEntity) entity).charSet("GBK").start();
                break;
            case AbsTaskWrapper.D_FTP_DIR:
                break;
            case AbsTaskWrapper.D_HTTP:
                Aria.download(getContext()).load((DownloadEntity) entity).start();
                break;
            case AbsTaskWrapper.DG_HTTP:
                Aria.download(getContext()).loadGroup((DownloadGroupEntity) entity).start();
                break;
        }
    }

    private void stop(AbsEntity entity) {
        switch (entity.getTaskType()) {
            case AbsTaskWrapper.D_FTP:
                //Aria.download(getContext()).loadFtp((DownloadEntity) entity).login("lao", "123456").stop();
                Aria.download(getContext()).loadFtp((DownloadEntity) entity).charSet("GBK").stop();
                break;
            case AbsTaskWrapper.D_FTP_DIR:
                break;
            case AbsTaskWrapper.D_HTTP:
                Aria.download(getContext()).load((DownloadEntity) entity).stop();
                break;
            case AbsTaskWrapper.DG_HTTP:
                Aria.download(getContext()).loadGroup((DownloadGroupEntity) entity).stop();
                break;
        }
    }

    class SimpleHolder extends AbsHolder {

        HorizontalProgressBarWithNumber progress;
        Button bt;
        TextView speed;
        TextView fileSize;
        TextView cancel;
        TextView name;
        CheckBox checkBox;

        SimpleHolder(View itemView) {
            super(itemView);
            progress = itemView.findViewById(R.id.progressBar);
            bt = itemView.findViewById(R.id.bt);
            speed = itemView.findViewById(R.id.speed);
            fileSize = itemView.findViewById(R.id.fileSize);
            cancel = itemView.findViewById(R.id.del);
            name = itemView.findViewById(R.id.name);
            checkBox = itemView.findViewById(R.id.checkbox);
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
            DownloadEntity absEntity = (DownloadEntity) mData.get(i);
            absEntity.setStr(b == true ? "true" : "false");
        }
        notifyDataSetChanged();
    }

    public boolean dealAllIsChecked() {
        for (int i = 0; i < mData.size(); i++) {
            AbsEntity absEntity = mData.get(i);
            if (!("true").equals(((DownloadEntity) absEntity).getStr())) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }

    public void remove() {

        for (int i = mData.size() - 1; i >= 0; i--) {//倒过来遍历  remove

            AbsEntity absEntity = mData.get(i);
            String isCheck = absEntity.getStr();
            if (("true").equals(isCheck) ? true : false) {
                mData.remove(i);
                cancel(absEntity);
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
            AbsEntity absEntity = mData.get(i);
            if (("true").equals(absEntity.getStr()) ? true : false) {
                return true;
            }
        }

        return false;

    }

}
