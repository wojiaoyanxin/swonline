package com.sunlands.intl.yingshi.ui.classroom.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.classroom.bean.LessonCalendarResponse;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;

import java.util.List;

public class LessonCalendarAdapter extends BaseQuickAdapter<LessonCalendarResponse.LessonCalendarBean, BaseViewHolder> {
    public LessonCalendarAdapter(List<LessonCalendarResponse.LessonCalendarBean> data) {
        super(R.layout.item_lesson_calendar);
    }

    @Override
    protected void convert(BaseViewHolder helper, LessonCalendarResponse.LessonCalendarBean item) {

        String handout = item.getHandout();
        if (TextUtils.isEmpty(handout)) {
            helper.setTextColor(R.id.download_handout, Color.parseColor("#dbdbdb"));
            helper.setText(R.id.download_handout, R.string.no_handout);
            helper.setImageResource(R.id.download_img_state, R.drawable.ic_undownload_handout);
        } else {
            helper.setText(R.id.download_handout, R.string.download_handout);
            helper.setTextColor(R.id.download_handout, Color.parseColor("#333333"));
            helper.setImageResource(R.id.download_img_state, R.drawable.ic_download_handout);
        }
        helper.setText(R.id.tv_lesson_title, item.getTitle());
        helper.setText(R.id.tv_lesson_time, item.getStartTime() + " - " + item.getEndTime());
        ImageView lessonCover = (ImageView) helper.getView(R.id.iv_lesson_cover);
        GlideUtils.loadImage(mContext, item.getPoster(), lessonCover, new RoundedCorners(DensityUtil.dip2px(mContext, 1)));
        int classTestStatus = item.getClassTestStatus();
        int isRepeat = item.getIsRepeat();
        int isEnd = item.getIsQuizEnd();
        int isQuizStart = item.getIsQuizStart();
        String quiz = item.getQuiz();
        ImageView ivQuiz = helper.getView(R.id.iv_quiz);
        ivQuiz.setImageResource(R.drawable.ic_quiz);
        if (TextUtils.isEmpty(quiz) | "0".equals(quiz)) { //暂无作业
            ivQuiz.setImageResource(R.drawable.ic_quiz_hui);
            helper.setTextColor(R.id.tv_quiz, Color.parseColor("#dbdbdb"));
            helper.setText(R.id.tv_quiz, mContext.getString(R.string.no_quiz));
        } else {
            helper.setText(R.id.tv_quiz, mContext.getString(R.string.quizzes));
            helper.setTextColor(R.id.tv_quiz, Color.parseColor("#333333"));
            ivQuiz.setImageResource(R.drawable.ic_quiz);
        }


//            if (isQuizStart == 0) { //未开始
//            ivQuiz.setImageResource(R.drawable.ic_quiz_hui);
//            helper.setTextColor(R.id.tv_quiz, Color.parseColor("#dbdbdb"));
//            helper.setText(R.id.tv_quiz, mContext.getString(R.string.wait_start));
//        } else if ((isEnd == 1 && classTestStatus == 0)) { //已结束未参加
//            helper.setText(R.id.tv_quiz, mContext.getString(R.string.un_join));
//            ivQuiz.setImageResource(R.drawable.ic_quiz_hui);
//            helper.setTextColor(R.id.tv_quiz, Color.parseColor("#dbdbdb"));
//        } else if (isQuizStart == 1 && classTestStatus == 0) { //已经开始未参加
//            helper.setText(R.id.tv_quiz, mContext.getString(R.string.quizzes));
//            helper.setTextColor(R.id.tv_quiz, Color.parseColor("#333333"));
//            ivQuiz.setImageResource(R.drawable.ic_quiz);
//        } else if ((isRepeat == 1 && classTestStatus == 1 && isEnd == 0)) { //已参加  支持多次参加
//            helper.setText(R.id.tv_quiz, mContext.getString(R.string.quizzes_analyse));
//            helper.setTextColor(R.id.tv_quiz, Color.parseColor("#333333"));
//            ivQuiz.setImageResource(R.drawable.ic_quiz);
//        } else if (isRepeat == 0 && classTestStatus == 1 && isEnd == 0) { //已参加 不 支持多次参加
//            helper.setText(R.id.tv_quiz, mContext.getString(R.string.quizzes_analyse));
//            helper.setTextColor(R.id.tv_quiz, Color.parseColor("#333333"));
//            ivQuiz.setImageResource(R.drawable.ic_quiz);
//        } else if ((isEnd == 1 && classTestStatus == 1)) { //已结束 已参加
//            helper.setText(R.id.tv_quiz, mContext.getString(R.string.quizzes_analyse));
//            helper.setTextColor(R.id.tv_quiz, Color.parseColor("#333333"));
//            ivQuiz.setImageResource(R.drawable.ic_quiz);
//        }

        helper.addOnClickListener(R.id.ll_watch_replay);
        helper.addOnClickListener(R.id.ll_handout_download);
        helper.addOnClickListener(R.id.ll_quiz);
        helper.addOnClickListener(R.id.ll_tag);

        ImageView view = helper.getView(R.id.class_course_state);
        int status = item.getPlayStatus();

        if (status == 0) { //未开始
            view.setVisibility(View.VISIBLE);
            view.setImageResource(R.drawable.ico_waitingbegin);
        } else if (status == 1) {  //正在播放
            view.setVisibility(View.VISIBLE);
            view.setImageResource(R.drawable.ico_living);
        } else if (status == 2) {  //回放生成中
            view.setVisibility(View.GONE);
        } else if (status == 3) {
            int hasJoin = item.getHasJoin();
            if (hasJoin == 1) {
                view.setVisibility(View.GONE);
                view.setImageResource(R.drawable.ico_signin);
            } else {
                view.setVisibility(View.VISIBLE);
                view.setImageResource(R.drawable.ico_absent);
            }
        }

    }
}
