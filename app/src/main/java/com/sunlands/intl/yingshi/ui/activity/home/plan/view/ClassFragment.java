package com.sunlands.intl.yingshi.ui.activity.home.plan.view;

import android.annotation.SuppressLint;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.base.BaseHeaderAdapter;
import com.sunlands.intl.yingshi.bean.multi.PinnedHeaderEntity;
import com.sunlands.intl.yingshi.common.MyFragment;
import com.sunlands.intl.yingshi.ui.activity.ExamTransitionActivity;
import com.sunlands.intl.yingshi.ui.activity.home.plan.adapter.ClassLessonsAdapter;
import com.sunlands.intl.yingshi.ui.activity.home.plan.adapter.ClassOtherAdapter;
import com.sunlands.intl.yingshi.ui.activity.home.plan.bean.PlanBean;
import com.sunlands.intl.yingshi.util.EnterPlayerUtils;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yanxin on 2019/3/11.
 */

@SuppressLint("ValidFragment")
public class ClassFragment extends MyFragment<PlanBean> {

    int type;
    public List<String> mDates = new ArrayList<>();

    public ClassFragment() {

    }

    public ClassFragment(int i) {
        type = i;
    }

    @Override
    public BaseQuickAdapter getAdapter() {

        if (type == 1) {
            return new ClassLessonsAdapter(null);
        }
        return new ClassOtherAdapter(null);
    }


    @Override
    public void setEmptyView(String tv, int imageId) {
        if (type == 1) {
             inflateStateView("暂无课程", R.drawable.no_class);
        } else if (type == 2) {
             inflateStateView("暂无作业", R.drawable.no_class);
        } else if (type == 3) {
             inflateStateView("暂无考试", R.drawable.no_class);
        } else if (type == 4) {
            inflateStateView("暂无论文", R.drawable.no_class);
        }
    }

    @Override
    public void onSuccess(PlanBean bean) {
        super.onSuccess(bean);
        List<PinnedHeaderEntity<PlanBean.DateListBean.ListBean>> pinnedHeaderEntities = convertData(bean);
        setData(pinnedHeaderEntities, false);
    }


    private List<PinnedHeaderEntity<PlanBean.DateListBean.ListBean>> convertData(PlanBean listBean) {
        List<PinnedHeaderEntity<PlanBean.DateListBean.ListBean>> pinnedHeaderEntities = new ArrayList<>();
        if (Utils.isEmpty(listBean.getDateList())) {
            return null;
        }
        for (PlanBean.DateListBean bean : listBean.getDateList()) {
            String subjectName = bean.getDate();
            List<PlanBean.DateListBean.ListBean> list = bean.getList();
            for (int i = 0; i < list.size(); i++) {
                if (!mDates.contains(subjectName)) {
                    pinnedHeaderEntities.add(new PinnedHeaderEntity<>(
                            list.get(i), BaseHeaderAdapter.TYPE_HEADER, subjectName));
                }
                pinnedHeaderEntities.add(new PinnedHeaderEntity<>(
                        list.get(i), BaseHeaderAdapter.TYPE_DATA, subjectName));
                PlanBean.DateListBean.ListBean listBean1 = list.get(i);
                listBean1.setShow(true);
                if (i == list.size() - 1) {
                    listBean1.setShow(false);
                }
                //0 全圆角   // 1 上圆角   //2 下圆角   //3 没有圆角

                if (list.size() == 1) { //只有一个
                    listBean1.setLayout(0);
                } else if (i == list.size() - 1) {  //最后一个
                    listBean1.setLayout(2);
                } else { //中间位置
                    if (i == 0) {
                        listBean1.setLayout(1);
                    } else {
                        listBean1.setLayout(3);
                    }
                }
                mDates.add(subjectName);
            }
        }
        return pinnedHeaderEntities;
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataOnNet(type);
    }

    @Override
    public void load() {
        super.load();
        if (mDates != null) {
            mDates.clear();
        }
        getDataOnNet(type);
    }

    @Override
    public void initListener() {
        super.initListener();
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PinnedHeaderEntity<PlanBean.DateListBean.ListBean> item = (PinnedHeaderEntity<PlanBean.DateListBean.ListBean>) adapter.getItem(position);

                if (type == 1) {
                    EnterPlayerUtils.enterClass(mContext, Integer.parseInt(item.getData().getCourseId()));
                } else if (type == 2) {
                    ExamTransitionActivity.show(mContext, item.getData().getTaskId() + "", item.getData().getCourseId());
                } else if (type == 4) {
                    int isEnd = item.getData().getIsEnd();
                    int isRepeat = item.getData().getIsRepeat();
                    int isStart = item.getData().getIsStart();
                    int hasJoin = item.getData().getHasJoin();
                    if (isStart == 0) {//未开始
                        // ToastUtils.showShort("考试未到开始时间");
                    } else if ((isEnd == 1 && hasJoin == 0)) {
                        //  ToastUtils.showShort("已过论文提交日期，不可提交");
                    } else if ((isEnd == 0 && hasJoin == 0)) {
                        ToastUtils.showShort("请前往思维教育官网提交论文");
                    } else if ((isEnd == 1 && hasJoin == 1)) { //已结束 已参加

                    } else if ((isEnd == 0 && hasJoin == 1)) { //未结束 已参加
                        ToastUtils.showShort("请前往思维教育官网提交论文");
                    } else {
                        ToastUtils.showShort("请前往思维教育官网提交论文");
                    }
                } else if (type == 3) {
                    int isEnd = item.getData().getIsEnd();
                    int isRepeat = item.getData().getIsRepeat();
                    int isStart = item.getData().getIsStart();
                    int hasJoin = item.getData().getHasJoin();
                    if (isStart == 0) {//未开始
                        // ToastUtils.showShort("考试未到开始时间");
                    } else if ((isEnd == 1 && hasJoin == 0)) { //已结束未参加
                        // ToastUtils.showShort("考试已结束");
                    } else if (isStart == 1 && hasJoin == 0) { //已经开始未参加
                        ToastUtils.showShort("请前往思维教育官网参加考试");
                        //   GoH5Utils.showExamTo(mContext, 0, item.getData().getExamId());
                    } else if ((isRepeat == 1 && hasJoin == 1)) { //已参加  支持多次参加
                        ToastUtils.showShort("请前往思维教育官网参加考试");
                    } else if ((isRepeat == 0 && hasJoin == 1)) { //已参加 不 支持多次参加
                        ToastUtils.showShort("请前往思维教育官网参加考试");
                    } else if ((isEnd == 1 && hasJoin == 1)) { //已结束 已参加
                        ToastUtils.showShort("请前往思维教育官网参加考试");
                    } else {
                        ToastUtils.showShort("请前往思维教育官网参加考试");
                    }
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_class_home;
    }
}
