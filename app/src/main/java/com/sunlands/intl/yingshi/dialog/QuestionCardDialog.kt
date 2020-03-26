package com.sunlands.intl.yingshi.dialog

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sunlands.comm_core.utils.DrawableUtils
import com.sunlands.intl.yingshi.R
import kotlinx.android.synthetic.main.dialog_question_card_layout.*

/**
 * 当前包名: com.sunlands.intl.yingshi.dialog
 * 创 建 人: xueh
 * 创建日期: 2020/2/26 10:22
 * 备注：
 */
class QuestionCardDialog(context: Context) : BottomSheetDialog(context, R.style.BottomSheetDialog) {
    init {
        setContentView(R.layout.dialog_question_card_layout)
        val listOf = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        rv_dialog_q_card.run {
            layoutManager=GridLayoutManager(context,6)
            adapter=object :BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_questioncard_layout,listOf){
                override fun convert(baseViewHolder: BaseViewHolder, s: String?) {
                    DrawableUtils.setRoundLineBg(baseViewHolder.getView(R.id.tv_item_q_num),R.color.cl_DCF6E9,R.color.cl_1FCD70,R.color.cl_1FCD70)
                    baseViewHolder.setText(R.id.tv_item_q_num,s)
                }
            }
        }
        iv_dialog_q_close.setOnClickListener {
            this.dismiss()
        }
    }
}