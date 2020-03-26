package com.sunlands.intl.yingshi.ui.classroom.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.multi.ReplayLessonItem;
import com.sunlands.intl.yingshi.bean.multi.ReplaySubjectItem;
import com.sunlands.intl.yingshi.ui.classroom.bean.AllCourseResponse;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;

import java.util.List;

public class ReplayLessonAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {


    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    public ReplayLessonAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_replay_lesson_parent);
        addItemType(TYPE_LEVEL_1, R.layout.item_replay_lesson_child);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {

        switch (helper.getItemViewType()) {

            case TYPE_LEVEL_0:

                final ReplaySubjectItem lv0 = (ReplaySubjectItem) item;
                helper.setText(R.id.tv_subject_name, lv0.getSubjectName())
                        .setText(R.id.tv_lesson_count, String.valueOf(lv0.getLessonCount()))
                        .setText(R.id.tv_un_finished_homework_count, String.valueOf(lv0.getUnFinishHomeworkCount()))
                        .setImageResource(R.id.iv_collapse_expand, !lv0.isExpanded()
                                ? R.drawable.ic_arrow_expand : R.drawable.ic_arrow_collapse);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final ReplayLessonItem child = (ReplayLessonItem) item;
                AllCourseResponse.ListBean.CourseListBean courseBean = child.getData();
                helper.setText(R.id.tv_lesson_title, courseBean.getTitle());
                helper.setText(R.id.tv_lesson_time, courseBean.getTime());

                ImageView imageView = (ImageView) helper.getView(R.id.iv_lesson_cover);
                GlideUtils.loadImage(mContext, courseBean.getPoster_img(), imageView, new RoundedCorners(DensityUtil.dip2px(mContext, 1)));

                String handout = courseBean.getHandout();
                if (TextUtils.isEmpty(handout)) {
                    helper.setTextColor(R.id.download_handout, Color.parseColor("#dbdbdb"));
                    helper.setText(R.id.download_handout, R.string.no_handout);
                    helper.setImageResource(R.id.download_img_state, R.drawable.ic_undownload_handout);
                } else {
                    helper.setText(R.id.download_handout, R.string.download_handout);
                    helper.setTextColor(R.id.download_handout, Color.parseColor("#333333"));
                    helper.setImageResource(R.id.download_img_state, R.drawable.ic_download_handout);
                }

                int status = courseBean.getStatus();

                int classTestStatus = courseBean.getQuiz_info().getHasJoin();
                int isRepeat = courseBean.getQuiz_info().getCanRepeat();
                int isEnd = courseBean.getQuiz_info().getHasEnd();
                int isQuizStart = courseBean.getQuiz_info().getHasBegin();
                int quiz = courseBean.getQuiz();

                ImageView ivQuiz = helper.getView(R.id.iv_quiz);
                ivQuiz.setImageResource(R.drawable.ic_quiz);

                if (quiz == 0) { //暂无作业

                    ivQuiz.setImageResource(R.drawable.ic_quiz_hui);
                    helper.setTextColor(R.id.tv_quiz, Color.parseColor("#dbdbdb"));
                    helper.setText(R.id.tv_quiz, mContext.getString(R.string.no_quiz));

                } else {

                    helper.setText(R.id.tv_quiz, mContext.getString(R.string.quizzes));
                    ivQuiz.setImageResource(R.drawable.ic_quiz);
                    helper.setTextColor(R.id.tv_quiz, Color.parseColor("#333333"));
                }


//                    if (isQuizStart == 0 ) { //未开始
//
//                    ivQuiz.setImageResource(R.drawable.ic_quiz_hui);
//                    helper.setTextColor(R.id.tv_quiz, Color.parseColor("#dbdbdb"));
//                    helper.setText(R.id.tv_quiz, mContext.getString(R.string.wait_start));
//
//                } else if ((isEnd == 1 && classTestStatus == 0)) { //已结束未参加
//                    helper.setText(R.id.tv_quiz, mContext.getString(R.string.un_join));
//                    ivQuiz.setImageResource(R.drawable.ic_quiz_hui);
//                    helper.setTextColor(R.id.tv_quiz, Color.parseColor("#dbdbdb"));
//                } else if (isQuizStart == 1 && classTestStatus == 0) { //已经开始未参加
//                    helper.setText(R.id.tv_quiz, mContext.getString(R.string.quizzes));
//                    ivQuiz.setImageResource(R.drawable.ic_quiz);
//                    helper.setTextColor(R.id.tv_quiz, Color.parseColor("#333333"));
//                } else if ((isRepeat == 1 && classTestStatus == 1 && isEnd == 0)) { //已参加  支持多次参加
//                    helper.setText(R.id.tv_quiz, mContext.getString(R.string.quizzes_analyse));
//                    ivQuiz.setImageResource(R.drawable.ic_quiz);
//                    helper.setTextColor(R.id.tv_quiz, Color.parseColor("#333333"));
//                } else if (isRepeat == 0 && classTestStatus == 1 && isEnd == 0) { //已参加 不 支持多次参加
//                    helper.setText(R.id.tv_quiz, mContext.getString(R.string.quizzes_analyse));
//                    ivQuiz.setImageResource(R.drawable.ic_quiz);
//                    helper.setTextColor(R.id.tv_quiz, Color.parseColor("#333333"));
//                } else if ((isEnd == 1 && classTestStatus == 1)) { //已结束 已参加
//                    helper.setText(R.id.tv_quiz, mContext.getString(R.string.quizzes_analyse));
//                    ivQuiz.setImageResource(R.drawable.ic_quiz);
//                    helper.setTextColor(R.id.tv_quiz, Color.parseColor("#333333"));
//                }

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCourseClickListener != null) {
                            mCourseClickListener.onClickCourse(courseBean);
                        }
                    }
                });

                helper.getView(R.id.ll_handout_download).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCourseClickListener != null) {
                            mCourseClickListener.onClickDownloadHandout(courseBean);
                        }
                    }
                });
                helper.getView(R.id.ll_quiz).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCourseClickListener != null) {
                            mCourseClickListener.onClickQuiz(courseBean);
                        }
                    }
                });

                ImageView view = helper.getView(R.id.class_course_state);

                if (status == 0) { //未开始
                    view.setVisibility(View.VISIBLE);
                    view.setImageResource(R.drawable.ico_waitingbegin);
                } else if (status == 1) {  //正在播放
                    view.setVisibility(View.VISIBLE);
                    view.setImageResource(R.drawable.ico_living);
                } else if (status == 2) {  //回放生成中
                    view.setVisibility(View.GONE);
                } else if (status == 3) {

                    int hasJoin = courseBean.getHasJoin();

                    if (hasJoin == 1) {
                        view.setVisibility(View.GONE);
                        view.setImageResource(R.drawable.ico_signin);
                    } else {
                        view.setVisibility(View.VISIBLE);
                        view.setImageResource(R.drawable.ico_absent);
                    }
                }

                break;
        }
    }

    public interface CourseClickListener {

        void onClickCourse(AllCourseResponse.ListBean.CourseListBean replayCourseBean);

        void onClickDownloadHandout(AllCourseResponse.ListBean.CourseListBean replayCourseBean);

        void onClickQuiz(AllCourseResponse.ListBean.CourseListBean replayCourseBean);
    }

    private CourseClickListener mCourseClickListener;

    public void setOnCourseClickListener(CourseClickListener onCourseClickListener) {
        mCourseClickListener = onCourseClickListener;
    }
}