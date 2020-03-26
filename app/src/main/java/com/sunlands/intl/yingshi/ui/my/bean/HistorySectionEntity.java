package com.sunlands.intl.yingshi.ui.my.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 文件名: HistorySectionEntity
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/23
 */
public class HistorySectionEntity extends SectionEntity<HistoryBean> {

    public HistorySectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public HistorySectionEntity(HistoryBean emptyBean) {
        super(emptyBean);
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return t == null ? super.hashCode() : Integer.valueOf(t.getCourseId());
    }
}
