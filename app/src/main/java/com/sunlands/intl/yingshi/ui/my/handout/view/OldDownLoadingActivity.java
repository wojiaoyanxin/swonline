package com.sunlands.intl.yingshi.ui.my.handout.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.download.DownloadTask;
import com.arialyy.aria.core.inf.AbsEntity;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.dialog.DialogUtils;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.ui.my.handout.adapter.OldDownLoadingAdapter;
import com.sunlands.intl.yingshi.util.SPHelper;
import com.tencent.liteav.demo.play.bean.MaterialBean;

import java.util.ArrayList;
import java.util.List;

import static com.arialyy.aria.core.inf.IEntity.STATE_COMPLETE;

public class OldDownLoadingActivity extends CommonActivity<Object> {

    private OldDownLoadingAdapter mAdapter;

    private List<AbsEntity> mData = new ArrayList<>();

    TextView tvHeaderRight;
    TextView selectAll;
    TextView delete;
    LinearLayout bottomView;
    ConstraintLayout noHandoutLayout;

    @Override
    public void initListener() {
        super.initListener();
        RxBindingUtils.setOnClickListener(tvHeaderRight, this);
        RxBindingUtils.setOnClickListener(selectAll, this);
        RxBindingUtils.setOnClickListener(delete, this);
        mAdapter.setOnCheckHasGoodsListener(new OldDownLoadingAdapter.OnCheckHasGoodsListener() {
            @Override
            public void onCheckHasGoods(boolean isHasGoods) {
                if (isHasGoods == false) {
                    tvHeaderRight.setVisibility(View.GONE);
                    bottomView.setVisibility(View.GONE);
                    noHandoutLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        mAdapter.setOnAllCheckedBoxNeedChangeListener(new OldDownLoadingAdapter.OnAllCheckedBoxNeedChangeListener() {
            @Override
            public void onCheckedBoxNeedChange(boolean allIsChecked) {
                enableDeleteBtn();
                if (allIsChecked == false) {
                    selectAll.setText("全选");
                } else {
                    selectAll.setText("取消全选");
                }
            }
        });
    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
        SPHelper.isSelectAllDownLoad(false);
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        noHandoutLayout = FBIA(R.id.no_handout_layout);
        tvHeaderRight = FBIA(R.id.tv_header_right);
        selectAll = FBIA(R.id.select_all);
        delete = FBIA(R.id.delete);
        bottomView = FBIA(R.id.bottom_view);
        findViewById(R.id.all_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CommonUtils.hasNetWorkConection()) {
                    ToastUtils.showShort("请检查网络设置");
                    return;
                }
                Aria.download(this).stopAllTask();
            }
        });
        findViewById(R.id.all_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CommonUtils.hasNetWorkConection()) {
                    ToastUtils.showShort("请检查网络设置");
                    return;
                }
                Aria.download(this).resumeAllTask();
            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        int id = v.getId();
        if (id == R.id.tv_header_right) {
            String rightText = tvHeaderRight.getText().toString();

            if ("编辑".equals(rightText)) {
                tvHeaderRight.setText("取消");
                SPHelper.isSelectAllDownLoad(true);
                bottomView.setVisibility(View.VISIBLE);
            } else {
                SPHelper.isSelectAllDownLoad(false);
                tvHeaderRight.setText("编辑");
                bottomView.setVisibility(View.GONE);
            }

            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        } else if (id == R.id.select_all) {
            String all = selectAll.getText().toString();
            if (all.equals("全选")) {
                selectAll();
            } else {
                cancelAll();
            }
            enableDeleteBtn();
        } else if (id == R.id.delete) {//先判断是否有选中的

            boolean check = mAdapter.isCheck();

            if (check == false) {
                return;
            }

            DialogUtils.deleteFile(this, new DialogUtils.onClick() {
                @Override
                public void sure() {
                    mAdapter.remove();
                }
            });
        }
    }

    //是否置灰删除按钮
    public void enableDeleteBtn() {
        boolean check = mAdapter.isCheck();
        if (check == true) {
            delete.setTextColor(Color.parseColor("#E44747"));
            delete.setBackgroundResource(R.drawable.delete);
        } else {
            delete.setTextColor(Color.parseColor("#8d8d8d"));
            delete.setBackgroundResource(R.drawable.delete_un_enable);
        }
    }

    //全选
    public void selectAll() {
        selectAll.setText("取消全选");
        assert mAdapter != null;
        mAdapter.setupAllChecked(true);
    }

    //取消全选
    public void cancelAll() {
        selectAll.setText("全选");
        assert mAdapter != null;
        mAdapter.setupAllChecked(false);
    }


    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        tvHeaderRight.setText("编辑");
        noHandoutLayout.setVisibility(View.GONE);
        hide();
        Aria.download(this).register();
        List<AbsEntity> temps = Aria.download(this).getTotalTaskList();
        if (temps != null && !temps.isEmpty()) {
            for (int i = 0; i < temps.size(); i++) {
                AbsEntity absEntity = temps.get(i);
                MaterialBean.ListBean listBean = new Gson().fromJson(((DownloadEntity) absEntity).getMd5Code(), MaterialBean.ListBean.class);
                Aria.download(this).load(((DownloadEntity) absEntity).getUrl()).setExtendField("false");
                if (absEntity.getState() != STATE_COMPLETE && listBean.getUserId() == LoginUserInfoHelper.getInstance().getUserInfo().getUserId()) {
                    mData.add(absEntity);
                }
            }
        }
        mAdapter = new OldDownLoadingAdapter(this, mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        cancelAll();
    }

    private void hide() {
        List<DownloadEntity> allNotCompleteTask = Aria.download(this).getAllNotCompleteTask();
        if (allNotCompleteTask == null) {
            tvHeaderRight.setVisibility(View.GONE);
            noHandoutLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_old_down_loading;
    }


    @Override
    public String getTitleText() {
        return "下载列表";
    }

    @Download.onPre
    void onPre(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onWait
    void onWait(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskStart
    void taskStart(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskResume
    void taskResume(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskStop
    void taskStop(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskCancel
    void taskCancel(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskFail
    void taskFail(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        hide();
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskRunning()
    void taskRunning(DownloadTask task) {
        mAdapter.setProgress(task.getEntity());
    }

}

