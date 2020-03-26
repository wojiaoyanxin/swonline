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
import com.shangde.lepai.uilib.indicator.commonnavigator.abs.IPagerTitleView
import com.shangde.lepai.uilib.indicator.commonnavigator.titles.SimplePagerTitleView
import com.sunlands.comm_core.base.KTActivity
import com.sunlands.comm_core.utils.CommonUtils
import com.sunlands.intl.yingshi.R
import com.sunlands.intl.yingshi.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_qbank_content.*


class QBankAllContentActivity : KTActivity() {
    var tabs= listOf("章节练习","真题练习","考前模拟")
    override fun initListener() {
    }

    override fun getCreateViewLayoutId() = R.layout.activity_qbank_content

    override fun initView(inflateView: View?, savedInstanceState: Bundle?) {
        initMagicIndicator()
        val mutableListOf = mutableListOf<Fragment>()
        for(index in 0..2){
            mutableListOf.add(QDirectoryFragment.getDirectoryFragment())
        }
        vp_qbankcontent.adapter = ViewPagerAdapter(mutableListOf,supportFragmentManager)
    }

    private fun initMagicIndicator() {
        val magicIndicator = findViewById<View>(R.id.mi_qbankcontent) as MagicIndicator
        magicIndicator.setBackgroundColor(CommonUtils.getColor(R.color.white))
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isSkimOver = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return if (tabs == null) 0 else tabs.size
            }
            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView: SimplePagerTitleView = SimplePagerTitleView(context)
                simplePagerTitleView.text = tabs[index]
                simplePagerTitleView.textSize = 14f
                simplePagerTitleView.normalColor = Color.parseColor("#282828")
                simplePagerTitleView.selectedColor =  Color.parseColor("#282828")
                simplePagerTitleView.setOnClickListener { vp_qbankcontent.currentItem = index }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context)=null
        }
        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, vp_qbankcontent)
    }

}
