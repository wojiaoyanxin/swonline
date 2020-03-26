package com.sunlands.intl.yingshi.ui.question.adapter

import com.chad.library.adapter.base.entity.SectionEntity


class SectionData : SectionEntity<SectionData> {
    var text: String? = null
    constructor(isHeader: Boolean, header: String) : super(isHeader, header)
    constructor(isHeader: Boolean, header: String, text: String) : super(isHeader, header) {
        this.text = text
    }
}
