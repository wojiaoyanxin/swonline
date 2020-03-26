package com.sunlands.intl.yingshi.ui.question

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.shangde.lepai.uilib.indicator.MagicIndicator
import com.shangde.lepai.uilib.indicator.ViewPagerHelper
import com.shangde.lepai.uilib.indicator.commonnavigator.CommonNavigator
import com.shangde.lepai.uilib.indicator.commonnavigator.abs.CommonNavigatorAdapter
import com.shangde.lepai.uilib.indicator.commonnavigator.abs.IPagerIndicator
import com.shangde.lepai.uilib.indicator.commonnavigator.abs.IPagerTitleView
import com.shangde.lepai.uilib.indicator.commonnavigator.indicators.LinePagerIndicator
import com.shangde.lepai.uilib.indicator.commonnavigator.titles.SimplePagerTitleView
import com.sunlands.comm_core.base.KTActivity
import com.sunlands.comm_core.utils.CommonUtils
import com.sunlands.intl.yingshi.R
import com.sunlands.intl.yingshi.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_qwrong_collect.*


class QWrongCollectActivity : KTActivity() {
    override fun initListener() {
    }

    override fun getCreateViewLayoutId()=R.layout.activity_qwrong_collect

    override fun initView(inflateView: View?, savedInstanceState: Bundle?) {
        initMagicIndicator()
        val mutableListOf = mutableListOf<Fragment>()
        for(index in 0..1){
            mutableListOf.add(QDirectoryFragment.getDirectoryFragment())
        }
        vp_q_wrong_collect.adapter = ViewPagerAdapter(mutableListOf,supportFragmentManager)
    }
    private fun initMagicIndicator() {
        var tabs= listOf("错题本","收藏题目")
        val magicIndicator = mi_q_wrong_collect as MagicIndicator


        val commonNavigator = CommonNavigator(this)
        magicIndicator.setBackgroundColor(Color.WHITE)
        commonNavigator.scrollPivotX = 0.8f
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return tabs.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView: SimplePagerTitleView = SimplePagerTitleView(context)
                simplePagerTitleView.text = tabs[index]
                simplePagerTitleView.textSize = 18f
                simplePagerTitleView.normalColor = Color.parseColor("#A0A0A0")
                simplePagerTitleView.selectedColor = Color.parseColor("#282828")
                simplePagerTitleView.setOnClickListener { vp_q_wrong_collect.setCurrentItem(index) }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator = LinePagerIndicator(context).apply {
                lineWidth= CommonUtils.dip2px(20f).toFloat()
                lineHeight=CommonUtils.dip2px(3f).toFloat()
                mode= LinePagerIndicator.MODE_EXACTLY
                setColors(Color.parseColor("#FF7224"))
            }
        }

        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, vp_q_wrong_collect)
    }
}
