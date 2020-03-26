package com.sunlands.intl.yingshi.dialog

import android.os.Bundle
import android.view.Gravity
import com.blankj.utilcode.util.ImageUtils
import com.sunlands.comm_core.helper.BaseDialogHelper
import com.sunlands.intl.yingshi.R
import com.sunlands.intl.yingshi.ui.question.QResultActivity
import kotlinx.android.synthetic.main.dialog_qresule_share_layout.*

/**
 * 当前包名: com.sunlands.intl.yingshi.dialog
 * 创 建 人: xueh
 * 创建日期: 2020/3/9 16:19
 * 备注：
 */

class QResultShareDialog : BaseDialogHelper() {
    companion object {
        fun getInstance(): QResultShareDialog {
            return QResultShareDialog().apply {
                setGravity(Gravity.CENTER)
                setDimEnabled(true)
            }
        }
    }

    override fun getLayoutId() = R.layout.dialog_qresule_share_layout
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iv_qshare.setImageBitmap(ImageUtils.getBitmap(QResultActivity.shareImgPath))


        iv_qshare_close.setOnClickListener {
            this.dismiss()
        }
        iv_qshare_wx.setOnClickListener {

        }
        iv_qshare_pyq.setOnClickListener {

        }

    }

    private fun onShare(platform: String) {

    }


}