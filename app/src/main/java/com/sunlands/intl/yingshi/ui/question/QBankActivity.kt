package com.sunlands.intl.yingshi.ui.question


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sunlands.comm_core.base.KTActivity
import com.sunlands.comm_core.weight.commtitle.OnTitleListener
import com.sunlands.intl.yingshi.R
import com.sunlands.intl.yingshi.ui.question.adapter.SectionAdapter
import com.sunlands.intl.yingshi.ui.question.adapter.SectionData
import kotlinx.android.synthetic.main.activity_qbank.*

class QBankActivity : KTActivity() {
    override fun initListener() {
        common_title.onTitleBarListener=object :OnTitleListener{
            override fun onLeftClick(v: View?) {
            }

            override fun onRightClick(v: View?) {
                startActivity(QWrongCollectActivity::class.java)
            }

            override fun onTitleClick(v: View?) {

            }
        }
        layer_all.setOnClickListener{
            startActivity(QBankAllContentActivity::class.java)
        }
    }

    override fun getCreateViewLayoutId()= R.layout.activity_qbank
    override fun initView(inflateView: View?, savedInstanceState: Bundle?) {
        val listOf = mutableListOf<SectionData>()
        listOf.add(SectionData(true,""))
        for (index in 1..100) {
            listOf.add(SectionData(index%10==0, index.toString()))
        }
        rv_qbank.run {
            layoutManager=LinearLayoutManager(context)
            adapter= SectionAdapter(listOf).apply {
                setOnItemClickListener { baseQuickAdapter, view, i ->
                    startActivity(QDirectoryActivity::class.java)
                }
            }
        }
    }
}
