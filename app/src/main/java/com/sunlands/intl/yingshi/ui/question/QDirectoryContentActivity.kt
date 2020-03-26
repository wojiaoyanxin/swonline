package com.sunlands.intl.yingshi.ui.question

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sunlands.comm_core.base.KTActivity
import com.sunlands.intl.yingshi.R
import kotlinx.android.synthetic.main.activity_qdirectory_content.*

class QDirectoryContentActivity : KTActivity() {
    override fun initListener() {
    }

    override fun getCreateViewLayoutId()=R.layout.activity_qdirectory_content

    override fun initView(inflateView: View?, savedInstanceState: Bundle?) {
        var list = mutableListOf<String>()
        for (index in 1..100) {
            list.add(index.toString())
        }
        rv_question_content.run {
            layoutManager=LinearLayoutManager(context)
            adapter=object:BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_qdirectorycontent_layout,list){
                override fun convert(p0: BaseViewHolder, p1: String) {
                    if (p1=="1" ){
                        p0.setVisible(R.id.v_q_d_c_top_line,false)
                    }
                }
            }.apply {
                setOnItemClickListener { baseQuickAdapter, view, i ->
                    startActivity(QuestionActivity::class.java)
                }
            }
        }
    }

}
