package com.sunlands.intl.yingshi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.intl.yingshi.bean.ExamInfoBean;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.util.GoH5Utils;

import java.util.List;

/**
 * 考试承载页面
 */

public class ExamTransitionActivity extends CommonActivity<ExamInfoBean> {

    TextView examXuzhi;
    TextView examName;
    TextView endTime;
    TextView objectiveScore;
    TextView subjectiveScore;
    TextView totalScore;
    TextView duration;
    TextView repeat;
    TextView dateTime;
    TextView useTime;
    TextView objectiveScorebBottom;
    TextView subjectiveScoreBottom;
    TextView totalScoreBottom;
    TextView enterQuestion;
    TextView enterJiexi;
    private String examId;
    private String courseId;
    private int isRepeat;
    private int hasBegin;
    private int hasEnd;
    private int hasJoined;
    private int isResult;
    private View tvjl;
    private View lljl;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        examName = FBIA(R.id.exam_name);
        examXuzhi = FBIA(R.id.exam_xuzhi);
        endTime = FBIA(R.id.endTime);
        objectiveScore = FBIA(R.id.objectiveScore);
        subjectiveScore = FBIA(R.id.subjectiveScore);
        duration = FBIA(R.id.duration);
        totalScore = FBIA(R.id.totalScore);
        repeat = FBIA(R.id.repeat);
        dateTime = FBIA(R.id.dateTime);
        useTime = FBIA(R.id.useTime);
        objectiveScorebBottom = FBIA(R.id.objectiveScore_bottom);
        subjectiveScoreBottom = FBIA(R.id.subjectiveScore_bottom);
        totalScoreBottom = FBIA(R.id.totalScore_bottom);
        enterQuestion = FBIA(R.id.enter_question);
        enterJiexi = FBIA(R.id.enter_jiexi);
        tvjl = FBIA(R.id.tv_jilu);
        lljl = FBIA(R.id.ll_jilu);
    }


    void show() {
        tvjl.setVisibility(View.VISIBLE);
        lljl.setVisibility(View.VISIBLE);
    }

    void hide() {
        tvjl.setVisibility(View.GONE);
        lljl.setVisibility(View.GONE);
    }


    @Override
    public void initListener() {
        super.initListener();
        RxBindingUtils.setOnClickListener(enterQuestion, this);
        RxBindingUtils.setOnClickListener(enterJiexi, this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v == enterQuestion) {
            GoH5Utils.showExamTo(this, courseId, examId, hasBegin, hasJoined, hasEnd, isRepeat, isResult);
        } else if (v == enterJiexi) {
            //     GoH5Utils.showExamTo(this, courseId, examId, hasBegin, hasJoined, hasEnd, isRepeat, isResult);
            if (isResult == 0) {
                ToastUtils.showShort("暂无解析");
                return;
            }
            GoH5Utils.showHomeTo(this, 1, courseId, examId);
        }
    }

    @Override
    public void onSuccess(ExamInfoBean bean) {
        super.onSuccess(bean);

        enterJiexi.setVisibility(View.VISIBLE);
        enterQuestion.setVisibility(View.VISIBLE);

        if (bean == null) {
            return;
        }

        examName.setText("考试名称：" + bean.getTitle());
        endTime.setText(bean.getEndTime());
        objectiveScore.setText(bean.getObjectiveScore());
        subjectiveScore.setText(bean.getSubjectiveScore());
        totalScore.setText(bean.getTotalScore());
        duration.setText(bean.getDuration());
        repeat.setText(bean.getRepeat());

        hide();

        if (bean.getExamScore() != null) {

            if (bean.getExamScore().getDateTime() != null) {
                show();
            }
            dateTime.setText(bean.getExamScore().getDateTime() == null ? "-" : bean.getExamScore().getDateTime());
            useTime.setText(bean.getExamScore().getUseTime() == null ? "-" : bean.getExamScore().getUseTime());
            objectiveScorebBottom.setText(bean.getExamScore().getObjectiveScore() == null ? "-" : bean.getExamScore().getObjectiveScore());
            subjectiveScoreBottom.setText(bean.getExamScore().getSubjectiveScore() == null ? "-" : bean.getExamScore().getSubjectiveScore());
            totalScoreBottom.setText(bean.getExamScore().getTotalScore() == null ? "-" : bean.getExamScore().getTotalScore());
        }

        List<String> notice = bean.getNotice();

        if (notice != null) {

            StringBuilder s = new StringBuilder("");
            for (int i = 0; i < notice.size(); i++) {
                s.append("* " + notice.get(i) + "\n\n");
            }
            examXuzhi.setText(s);
        }

        isRepeat = bean.getIsRepeat();
        hasBegin = bean.getHasBegin();
        hasEnd = bean.getHasEnd();
        hasJoined = bean.getHasJoined();
        isResult = bean.getIsResult();

        GoH5Utils.setViewState(enterQuestion, enterJiexi, hasEnd, isRepeat, hasBegin, hasJoined, isResult);

    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        enterJiexi.setVisibility(View.GONE);
        enterQuestion.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataNet(true, examId);
    }

    public static void show(Context context, String examId, String courseId) {
        Intent intent = new Intent(context, ExamTransitionActivity.class);
        intent.putExtra(Constants.Key.EXAM_ID, examId);
        intent.putExtra(Constants.Key.KEY_COURSE_ID, courseId);
        context.startActivity(intent);
    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
        examId = getIntent().getStringExtra(Constants.Key.EXAM_ID);
        courseId = getIntent().getStringExtra(Constants.Key.KEY_COURSE_ID);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exam_transition;
    }

    @Override
    public String getTitleText() {
        return "考试";
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        enterJiexi.setVisibility(View.GONE);
        enterQuestion.setVisibility(View.GONE);
    }
}
