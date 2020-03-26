package com.sunlands.intl.yingshi.bean.multi;


import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ScoreQueryHeadItem extends AbstractExpandableItem<ScoreQueryItem> implements MultiItemEntity {


    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 0;
    }

}