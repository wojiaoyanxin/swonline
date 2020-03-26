package com.sunlands.intl.yingshi.ui.question.adapter

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sunlands.intl.yingshi.R


class SectionAdapter(list:List<SectionData>) :
        BaseSectionQuickAdapter<SectionData, BaseViewHolder>(
                R.layout.item_qbank_item_layout,  R.layout.item_qbank_title_layout, list) {
    override fun convertHead(helper: BaseViewHolder, item: SectionData) {

    }

    override fun convert(helper: BaseViewHolder, item: SectionData) {

    }
}
