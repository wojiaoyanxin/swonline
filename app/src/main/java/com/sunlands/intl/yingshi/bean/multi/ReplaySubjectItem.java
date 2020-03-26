package com.sunlands.intl.yingshi.bean.multi;


import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ReplaySubjectItem extends AbstractExpandableItem<ReplayLessonItem> implements MultiItemEntity {


    private String subjectName;
    private String subjectId;
    private int lessonCount;
    private int unFinishHomeworkCount;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public ReplaySubjectItem(String subjectName, int lessonCount, int unFinishHomeworkCount) {
        this.subjectName = subjectName;
        this.lessonCount = lessonCount;
        this.unFinishHomeworkCount = unFinishHomeworkCount;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public int getUnFinishHomeworkCount() {
        return unFinishHomeworkCount;
    }

    public void setUnFinishHomeworkCount(int unFinishHomeworkCount) {
        this.unFinishHomeworkCount = unFinishHomeworkCount;
    }
}