package com.sunlands.intl.yingshi.bean;

/**
 * 文件名: AnswerCardTabItem
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/6
 */
public class AnswerCardTabItem {

    private int indexStart;
    private int indexEnd;
    private boolean selected;

    public int getIndexStart() {
        return indexStart;
    }

    public void setIndexStart(int indexStart) {
        this.indexStart = indexStart;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int indexEnd) {
        this.indexEnd = indexEnd;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
