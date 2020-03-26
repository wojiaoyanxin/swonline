package com.sunlands.intl.yingshi.ui.question


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import com.blankj.utilcode.util.SpanUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sunlands.comm_core.base.KTActivity
import com.sunlands.comm_core.utils.CommonUtils
import com.sunlands.comm_core.weight.RecycleViewItemLine
import com.sunlands.intl.yingshi.R
import com.sunlands.intl.yingshi.constant.Constants
import com.sunlands.intl.yingshi.dialog.QuestionCardDialog
import com.sunlands.intl.yingshi.ui.question.adapter.QuestionAdapter
import com.sunlands.intl.yingshi.ui.question.adapter.QuestionEntity
import com.sunlands.intl.yingshi.ui.question.adapter.QuestionItemEntity
import com.sunlands.intl.yingshi.ui.question.view.CheckableConstraintLayout
import io.github.kexanie.library.MathView
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : KTActivity() {
    var currentIndex = 0
    override fun initListener() {
        iv_question_card.setOnClickListener {
            QuestionCardDialog(this@QuestionActivity).show()
        }
    }

    override fun getCreateViewLayoutId() = R.layout.activity_question
    val listOf = listOf(QuestionEntity().apply {
        title = "题目一"
        type = Constants.QUES_TYPE_TIANKONG
    })

    override fun initView(inflateView: View?, savedInstanceState: Bundle?) {
        pb_question.max = listOf.size
        onPageChange()
        rv_question.run {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rv_question.layoutManager = linearLayoutManager
            PagerSnapHelper().attachToRecyclerView(this)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val first = (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
                    val last = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
                    if (currentIndex != (first + last) / 2) {
                        currentIndex = (first + last) / 2
                    }
                    onPageChange()
                }
            })


            adapter = object : BaseQuickAdapter<QuestionEntity, BaseViewHolder>(R.layout.item_question_content, listOf) {
                override fun convert(viewHolder: BaseViewHolder, p1: QuestionEntity) {
                    val childRecyclerView = viewHolder.getView<RecyclerView>(R.id.rv_question_item)
                    childRecyclerView.run {
                        layoutManager = LinearLayoutManager(context)
                        addItemDecoration(RecycleViewItemLine(context, LinearLayoutManager.VERTICAL, 15, R.color.white))

                        var item = mutableListOf<QuestionEntity>()
                        for (index in 1..4) {
                            item.add(QuestionEntity().apply {
                                content = "content${index}"
                                type = Constants.QUES_TYPE_TIANKONG
                            })
                        }
                        adapter = QuestionAdapter(item)
//                        adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_question_type_options, itemlistOf) {
//                            override fun convert(childViewHolder: BaseViewHolder, child: String) {
//                                childViewHolder.getView<CheckableConstraintLayout>(R.id.ccl_options_item).run {
//                                    isChecked = false
//                                    setOnClickListener {
//                                        toggle()
//                                        if (child == "a") {
//                                            setRightState()
//                                        } else {
//                                            setErrorState()
//                                        }
//                                        (childRecyclerView.adapter as BaseQuickAdapter<String, BaseViewHolder>).run {
//                                            if (footerLayoutCount ==0){
//                                                addFooterView(View.inflate(context, R.layout.footer_question_parsing, null).apply {
//                                                })
//                                            }
//                                        }
//                                    }
//                                }
//                                childViewHolder.getView<MathView>(R.id.mv_options_item).text = child
//                            }
//                        }
                                .apply {
                                    addHeaderView(View.inflate(context, R.layout.header_question_title, null).apply {
                                        findViewById<MathView>(R.id.mv_question_title).text = "题目"
                                    })
                                }
                    }

                }
            }
        }
    }

    private fun onPageChange() {
        SpanUtils.with(tv_question_no)
                .append("${currentIndex + 1}").setForegroundColor(CommonUtils.getColor(R.color.cl_FF7224)).setFontSize(20, true)
                .append("/${listOf.size}").setForegroundColor(CommonUtils.getColor(R.color.cl_BBBBBB)).setFontSize(16, true)
                .create()
        pb_question.progress = currentIndex + 1
    }
}
