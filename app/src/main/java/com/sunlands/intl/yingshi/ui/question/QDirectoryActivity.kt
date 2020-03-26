package com.sunlands.intl.yingshi.ui.question

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.shangde.lepai.uilib.indicator.MagicIndicator
import com.shangde.lepai.uilib.indicator.ViewPagerHelper
import com.shangde.lepai.uilib.indicator.commonnavigator.CommonNavigator
import com.shangde.lepai.uilib.indicator.commonnavigator.abs.CommonNavigatorAdapter
import com.shangde.lepai.uilib.indicator.commonnavigator.abs.IPagerTitleView
import com.shangde.lepai.uilib.indicator.commonnavigator.indicators.LinePagerIndicator
import com.shangde.lepai.uilib.indicator.commonnavigator.titles.CommonPagerTitleView
import com.sunlands.comm_core.base.KTActivity
import com.sunlands.intl.yingshi.R
import com.sunlands.intl.yingshi.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_qdirectory.*


class QDirectoryActivity : KTActivity() {
    var tabs= listOf("章节练习","真题练习","考前模拟")
    var icons= listOf(R.drawable.ic_q_i_chapter_exercises,R.drawable.ic_q_i_practice_exercise,R.drawable.ic_q_i_exam_simulation)

    override fun initListener() {
    }

    override fun getCreateViewLayoutId() = R.layout.activity_qdirectory

    override fun initView(inflateView: View?, savedInstanceState: Bundle?) {
        initMagicIndicator()
        val mutableListOf = mutableListOf<Fragment>()
        for(index in 0..2){
            mutableListOf.add(QDirectoryFragment.getDirectoryFragment())
        }
        vp_q_directory.adapter = ViewPagerAdapter(mutableListOf,supportFragmentManager)
    }

    private fun initMagicIndicator() {
        val magicIndicator = magic_indicator as MagicIndicator
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                val commonPagerTitleView = CommonPagerTitleView(context)
                val customLayout: View = LayoutInflater.from(context).inflate(R.layout.q_indicator_layout, null)
                customLayout.findViewById<TextView>(R.id.tv_q_indicator_title).text=tabs[index]
                customLayout.findViewById<ImageView>(R.id.iv_tv_indicator).setImageResource(icons[index])
                commonPagerTitleView.setContentView(customLayout)
                commonPagerTitleView.setOnClickListener { vp_q_directory.currentItem = index }
                return commonPagerTitleView
            }

            override fun getCount() = tabs.size
            override fun getIndicator(context: Context?) = LinePagerIndicator(context).apply {
                setColors(Color.parseColor("#FF7224"))
            }

        }
        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, vp_q_directory)
    }
}
