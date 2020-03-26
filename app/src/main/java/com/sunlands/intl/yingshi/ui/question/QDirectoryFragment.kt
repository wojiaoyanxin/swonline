package com.sunlands.intl.yingshi.ui.question

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sunlands.comm_core.base.KTFragment
import com.sunlands.intl.yingshi.R
import kotlinx.android.synthetic.main.fragment_directory_layout.*

/**
 * 创 建 人: xueh
 * 创建日期: 2020/2/27 12:53
 * 备注：
 */
class QDirectoryFragment : KTFragment() {
    companion object {
        fun getDirectoryFragment(): QDirectoryFragment {
            return QDirectoryFragment().apply {

            }
        }
    }

    override fun initListener() {
    }

    override fun getCreateViewLayoutId() = R.layout.fragment_directory_layout

    override fun initView(inflateView: View?, savedInstanceState: Bundle?) {
        var list = mutableListOf<String>()
        for (index in 1..100) {
            list.add(index.toString())
        }
        rv_directory.run {
            layoutManager = LinearLayoutManager(context)
            adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_directory_layout, list) {
                override fun convert(p0: BaseViewHolder?, p1: String?) {

                }
            }.apply {
                onItemClickListener = BaseQuickAdapter.OnItemClickListener { p0, p1, p2 ->
                    startActivity(QDirectoryContentActivity::class.java)
                }
            }
        }

    }

}