package com.sunlands.intl.yingshi.ui.question.adapter

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.question.adapter
 * 创 建 人: xueh
 * 创建日期: 2020/3/10 13:39
 * 备注：
 */
 class QuestionEntity: MultiItemEntity{
    var title=""
    var content=""
    var type=0
    override fun getItemType(): Int {
        return type
    }
}

 class QuestionItemEntity: MultiItemEntity{
     var content= mutableListOf<String>()
     var type=0
     override fun getItemType(): Int {
         return type
     }
 }