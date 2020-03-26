package com.sunlands.intl.yingshi.ui.my.handout.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.HandoutBean;
import com.sunlands.intl.yingshi.bean.PageBean;
import com.sunlands.intl.yingshi.bean.multi.HandoutItem;
import com.sunlands.intl.yingshi.bean.multi.SubjectItem;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.dialog.DialogUtils;
import com.sunlands.intl.yingshi.greendao.MyDownLoadInfoDao;
import com.sunlands.intl.yingshi.greendao.db.HandoutDbBean;
import com.sunlands.intl.yingshi.greendao.db.MyDownLoadInfo;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.ui.my.handout.adapter.MyBaseExpandableListAdapter;
import com.sunlands.intl.yingshi.ui.my.handout.entity.MyHashMap;
import com.sunlands.intl.yingshi.util.SPHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyHandoutActivity extends CommonActivity<Object> {

    TextView tvHeaderRight;
    TextView selectAll;
    TextView delete;
    LinearLayout bottomView;
    ExpandableListView expandableListView;
    ConstraintLayout noHandoutLayout;
    View llDownLoading;

    //定义父列表项List数据集合
    List<Map<String, Object>> parentMapList = new ArrayList<Map<String, Object>>();
    //定义子列表项List数据集合
    List<List<Map<String, Object>>> childMapList_list = new ArrayList<List<Map<String, Object>>>();
    MyBaseExpandableListAdapter myBaseExpandableListAdapter;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        tvHeaderRight = FBIA(R.id.tv_header_right);
        selectAll = FBIA(R.id.select_all);
        delete = FBIA(R.id.delete);
        bottomView = FBIA(R.id.bottom_view);
        expandableListView = FBIA(R.id.id_elv_listview);
        noHandoutLayout = FBIA(R.id.no_handout_layout);
        llDownLoading = FBIA(R.id.ll_downloading);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingUtils.setOnClickListener(tvHeaderRight, this);
        RxBindingUtils.setOnClickListener(selectAll, this);
        RxBindingUtils.setOnClickListener(delete, this);
        RxBindingUtils.setOnClickListener(llDownLoading, this);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
    }

    public static void show(Context context, String subjectId, String name) {
        Intent intent = new Intent(context, MyHandoutActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(Constants.Key.KEY_SUBJECT_ID, subjectId);
        intent.putExtra(Constants.Key.KEY_SUBJECT_NAME, name);
        context.startActivity(intent);
    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
        SPHelper.isSelectAllShow(false);
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getData();
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        tvHeaderRight.setText("编辑");
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        mSwipeRefreshLayout.setEnabled(false);
        tvHeaderRight.setVisibility(View.GONE);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_handout;
    }

    @Override
    public String getTitleText() {
        return "课程资料";
    }

    protected void getData() {
        cancelAll();
        getMyHandout();
    }

    public void onGetMyHandoutSuccess(List<HandoutBean> multiItemEntities) {
        List<PageBean> dataList = SPHelper.getDataList();
        tvHeaderRight.setVisibility(View.GONE);
        noHandoutLayout.setVisibility(View.GONE);
        if (bottomView.getVisibility() == View.VISIBLE) {
            mSwipeRefreshLayout.setEnabled(false);
        } else {
            mSwipeRefreshLayout.setEnabled(true);
        }
        mSwipeRefreshLayout.setRefreshing(false);
        parentMapList.clear();
        childMapList_list.clear();

        for (int i = 0; i < multiItemEntities.size(); i++) {
            HandoutBean handoutBean = multiItemEntities.get(i);
//            //提供父列表的数据
            Map<String, Object> parentMap = new HashMap<String, Object>();
            parentMap.put("parentName", handoutBean.getSubjectItem());
            parentMapList.add(parentMap);
            //提供当前父列的子列数据
            List<Map<String, Object>> childMapList = new ArrayList<Map<String, Object>>();
            List<HandoutDbBean> handoutDbBeans = handoutBean.getmHandoutDbBeans();
            for (int i1 = 0; i1 < handoutDbBeans.size(); i1++) {
                MyHashMap<String, Object> childMap = new MyHashMap<String, Object>();
                HandoutDbBean mHandoutDbBean = handoutDbBeans.get(i1);
                HandoutItem handoutItem = new HandoutItem(mHandoutDbBean.getCourseName()
                        , "",
                        "", mHandoutDbBean.getFileName());
                handoutItem.setSubjectId(mHandoutDbBean.getSubjectId());
                handoutItem.setCourseId(mHandoutDbBean.getCourseId());
                handoutItem.setFileUrl("");
                handoutItem.setSid(0);
                handoutItem.setProgress("");
                for (int i2 = 0; i2 < dataList.size(); i2++) {
                    PageBean pageBean = dataList.get(i2);
                    if (handoutItem.getPath().equals(pageBean.getFilePath())) {
                        if (TextUtils.isEmpty(pageBean.getFilePath())) {
                            handoutItem.setProgress("观看至0%");
                        } else {
                            handoutItem.setProgress("观看至" + pageBean.getProgress());
                        }
                    }
                }
                childMap.put("childName", handoutItem);
                if (childMapList.size() > 0) {
                    for (Map<String, Object> stringObjectMap : childMapList) {
                        if (stringObjectMap.toString().equals(childMap.toString())) {
                            childMapList.remove(childMap);
                        } else {
                            childMapList.add(childMap);
                        }
                    }
                } else {
                    childMapList.add(childMap);
                }

            }
            childMapList_list.add(childMapList);
        }

        myBaseExpandableListAdapter = new MyBaseExpandableListAdapter(MyHandoutActivity.this, parentMapList, childMapList_list, expandableListView);
        expandableListView.setAdapter(myBaseExpandableListAdapter);
        myBaseExpandableListAdapter.setOnAllCheckedBoxNeedChangeListener(new MyBaseExpandableListAdapter.OnAllCheckedBoxNeedChangeListener() {
            @Override
            public void onCheckedBoxNeedChange(boolean allParentIsChecked) {

                enableDeleteBtn();
                if (allParentIsChecked == false) {
                    selectAll.setText("全选");
                } else {
                    selectAll.setText("取消全选");
                }
            }
        });
        myBaseExpandableListAdapter.setOnCheckHasGoodsListener(new MyBaseExpandableListAdapter.OnCheckHasGoodsListener() {
            @Override
            public void onCheckHasGoods(boolean isHasGoods) {
                if (isHasGoods == true) {
                    return;
                }
                mSwipeRefreshLayout.setEnabled(false);
                tvHeaderRight.setVisibility(View.GONE);
                bottomView.setVisibility(View.GONE);
                noHandoutLayout.setVisibility(View.GONE);
                SPHelper.isSelectAllShow(false);
                tvHeaderRight.setText("编辑");
                if (myBaseExpandableListAdapter != null) {
                    myBaseExpandableListAdapter.notifyDataSetChanged();
                }
            }
        });
        myBaseExpandableListAdapter.setOnClickLookListener(new MyBaseExpandableListAdapter.OnClickLookListener() {
            @Override
            public void onClickLook(String s, String s1) {
                if (bottomView.getVisibility() == View.VISIBLE) {
                    return;
                }
                HandoutDetailActivity.show(MyHandoutActivity.this, s, s1);
            }
        });
        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }

    }

    public void getMyHandout() {

        DbHelper.getInstance().getMyDownLoadInfoDao().detachAll();
        List<MyDownLoadInfo> listCompleteTask = new ArrayList<>();
        listCompleteTask.clear();
        List<MyDownLoadInfo> listAll = DbHelper.getInstance().getMyDownLoadInfoDao().queryBuilder()
                .where(MyDownLoadInfoDao.Properties.UserId.eq(LoginUserInfoHelper.getInstance().getUserInfo().getUserId()))
                .orderDesc(MyDownLoadInfoDao.Properties.Id)
                .list();

        List<DownloadEntity> allCompleteTask = Aria.download(this).getAllCompleteTask();

        if (allCompleteTask != null) {
            for (DownloadEntity downloadEntity : allCompleteTask) {
                for (MyDownLoadInfo myDownLoadInfo : listAll) {
                    if (downloadEntity.getUrl().equals(myDownLoadInfo.getDownloadEntity().getUrl())) {
                        listCompleteTask.add(myDownLoadInfo);
                    }
                }
            }
        }
        //获取产品包下的所有讲义
        List<HandoutDbBean> mHandoutDbBeans = new ArrayList<>();
        for (int i = 0; i < listCompleteTask.size(); i++) {
            MyDownLoadInfo myAllFileDbBean = listCompleteTask.get(i);
            mHandoutDbBeans.add(new HandoutDbBean(1l, 1l, myAllFileDbBean.getSchoolId() + "", myAllFileDbBean.getCourseId() + "",
                    myAllFileDbBean.getSchoolName(), "", myAllFileDbBean.getDownloadEntity().getDownloadPath(), myAllFileDbBean.getCourseName(),
                    myAllFileDbBean.getDownloadEntity().getUrl(), myAllFileDbBean.getFileId(), myAllFileDbBean.getUserId()));
        }

        //重新分组
        HashMap<String, ArrayList<HandoutDbBean>> map = new HashMap<>();
        for (HandoutDbBean bean : mHandoutDbBeans) {
            if (map.containsKey(bean.getCourseName())) {
                map.get(bean.getCourseName()).add(bean);
            } else {
                ArrayList<HandoutDbBean> list2 = new ArrayList<>();
                list2.add(bean);
                map.put(bean.getCourseName(), list2);
            }
        }

        int count = map.size();

        if (count == 0) {
            noHandoutLayout.setVisibility(View.VISIBLE);
        }
        ArrayList<HandoutBean> handoutDbBeans = new ArrayList<>();
        handoutDbBeans.clear();
        for (Map.Entry<String, ArrayList<HandoutDbBean>> entry : map.entrySet()) {
            SubjectItem subjectItem = new SubjectItem(entry.getKey(), "已下载：" + entry.getValue().size());
            HandoutBean handoutBean = new HandoutBean();
            handoutBean.setSubjectItem(subjectItem);
            handoutBean.setmHandoutDbBeans(entry.getValue());
            handoutDbBeans.add(handoutBean);
        }

        ArrayList<HandoutBean> handoutDbBeans1 = new ArrayList<>();
        for (int i1 = 0; i1 < listCompleteTask.size(); i1++) {
            String courseName = listCompleteTask.get(i1).getSchoolName();
            for (int i = 0; i < handoutDbBeans.size(); i++) {
                HandoutBean handoutBean = handoutDbBeans.get(i);
                String title = handoutBean.getSubjectItem().title;
                if (title.equals(courseName)) {
                    handoutDbBeans1.add(handoutBean);
                }
            }
        }
        //   Collections.reverse(handoutDbBeans1);
        for (int i = 0; i < handoutDbBeans1.size() - 1; i++) {
            for (int j = handoutDbBeans1.size() - 1; j > i; j--) {
                if (handoutDbBeans1.get(j).getSubjectItem().title.equals(handoutDbBeans1.get(i).getSubjectItem().title)) {
                    handoutDbBeans1.remove(j);
                }
            }
        }
        onGetMyHandoutSuccess(handoutDbBeans1);
        handoutDbBeans1.clear();
        mHandoutDbBeans.clear();
        map.clear();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.ll_downloading) { //点击跳转正在下载页面

            startActivity(DownLoadingActivity.class);
        } else if (id == R.id.tv_header_right) {
            String rightText = tvHeaderRight.getText().toString();
            if ("编辑".equals(rightText)) {
                tvHeaderRight.setText("取消");
                SPHelper.isSelectAllShow(true);
                bottomView.setVisibility(View.VISIBLE);
                mSwipeRefreshLayout.setEnabled(false);
            } else {
                SPHelper.isSelectAllShow(false);
                tvHeaderRight.setText("编辑");
                bottomView.setVisibility(View.GONE);
                mSwipeRefreshLayout.setEnabled(true);
            }
            if (myBaseExpandableListAdapter != null) {
                myBaseExpandableListAdapter.notifyDataSetChanged();
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

            boolean check = myBaseExpandableListAdapter.isCheck();

            if (check == false) {
                return;
            }

            DialogUtils.deleteFile(this, new DialogUtils.onClick() {
                @Override
                public void sure() {
                    myBaseExpandableListAdapter.removeGoods();
                    getMyHandout();
                }
            });
        }
    }

    //是否置灰删除按钮
    public void enableDeleteBtn() {
        boolean check = myBaseExpandableListAdapter.isCheck();
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
        myBaseExpandableListAdapter.setupAllChecked(true);
    }

    //取消全选
    public void cancelAll() {

        selectAll.setText("全选");
        if (myBaseExpandableListAdapter != null) {
            myBaseExpandableListAdapter.setupAllChecked(false);
        }
    }

}
