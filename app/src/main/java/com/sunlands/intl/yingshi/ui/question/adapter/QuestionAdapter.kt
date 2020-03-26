package com.sunlands.intl.yingshi.ui.question.adapter

import android.widget.Switch
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sunlands.intl.yingshi.R
import com.sunlands.intl.yingshi.constant.Constants
import com.sunlands.intl.yingshi.ui.question.view.CheckableConstraintLayout
import io.github.kexanie.library.MathView

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.question.adapter
 * 创 建 人: xueh
 * 创建日期: 2020/3/10 13:38
 * 备注：
 */
class QuestionAdapter(list: List<QuestionEntity>) : BaseMultiItemQuickAdapter<QuestionEntity, BaseViewHolder>(list) {
    init {
        addItemType(Constants.QUES_TYPE_TIANKONG, R.layout.item_question_type_tiankong_layout)
        addItemType(Constants.QUES_TYPE_DANXUAN, R.layout.item_question_type_options)
    }

    override fun convert(childViewHolder: BaseViewHolder, item: QuestionEntity) {
        when (childViewHolder.itemViewType) {
            Constants.QUES_TYPE_TIANKONG -> {
                childViewHolder.getView<CheckableConstraintLayout>(R.id.ccl_options_item).run {
                    setOnClickListener {

                    }
                }
                childViewHolder.getView<TextView>(R.id.tv_q_tiankong_num).text = getTiankongNum(childViewHolder.adapterPosition)
            }

            Constants.QUES_TYPE_DANXUAN -> {
                childViewHolder.getView<MathView>(R.id.mv_options_item).text = item.title
                childViewHolder.getView<CheckableConstraintLayout>(R.id.ccl_options_item).run {
                    isChecked = false
                    setOnClickListener {
                        toggle()
                    }
                }

            }
        }
    }

    fun getTiankongNum(pos: Int): String {
        var no = ""
        when (pos) {
            1 -> no = "填空一"
            2 -> no = "填空二"
            3 -> no = "填空三"
            4 -> no = "填空四"
            5 -> no = "填空五"
            6 -> no = "填空六"
            7 -> no = "填空七"
            8 -> no = "填空八"
            9 -> no = "填空九"
        }
        return no
    }
}