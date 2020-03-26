package com.sunlands.intl.yingshi.ui.question

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.Utils
import com.sunlands.comm_core.base.BaseActivity
import com.sunlands.comm_core.base.KTActivity
import com.sunlands.comm_core.utils.CommonUtils
import com.sunlands.comm_core.utils.rx.rxjava.RxJavaUtils
import com.sunlands.comm_core.utils.rx.rxjava.task.RxUITask
import com.sunlands.comm_core.weight.ViewLoading
import com.sunlands.comm_core.weight.commtitle.OnTitleListener
import com.sunlands.intl.yingshi.R
import com.sunlands.intl.yingshi.dialog.QResultShareDialog
import kotlinx.android.synthetic.main.activity_qresult.*
import java.io.File


class QResultActivity : KTActivity() {
    companion object{
        val shareImgPath = PathUtils.getExternalAppCachePath() + File.separator + "qbankshare.png"
    }

    override fun initListener() {
        common_title.onTitleBarListener = object : OnTitleListener {
            override fun onLeftClick(v: View?) {
                this@QResultActivity.finish()
            }

            override fun onRightClick(v: View?) {
                val view2Bitmap = ImageUtils.view2Bitmap(if (mShareView == null) getShareView() else mShareView)
                val file = File(PathUtils.getExternalAppCachePath(), "qbankshare.png")
                if (ImageUtils.save(view2Bitmap, file, Bitmap.CompressFormat.PNG)) {
                    MediaStore.Images.Media.insertImage(Utils.getApp().contentResolver, view2Bitmap, shareImgPath, null)
                    val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                    intent.data = Uri.fromFile(file)
                    Utils.getApp().sendBroadcast(intent)

                    QResultShareDialog.getInstance().show(supportFragmentManager,null)
                }
            }

            override fun onTitleClick(v: View?) {
            }
        }
    }

    var mShareView: View? = null
    override fun getCreateViewLayoutId() = R.layout.activity_qresult

    override fun initView(inflateView: View?, savedInstanceState: Bundle?) {
        mShareView = getShareView()
    }

    override fun onResume() {
        super.onResume()
        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().init()
    }

    fun getShareView(): View {
        val view = inflater.inflate(R.layout.qbank_share_layout, null).apply {
            findViewById<TextView>(R.id.tv_qbank_share_name).text = "ssss"
            findViewById<TextView>(R.id.tv_qbank_share_content).text = "再接再厉"
            findViewById<TextView>(R.id.tv_qbank_share_answer_num).text = "1"
            findViewById<TextView>(R.id.tv_qbank_share_wrong_num).text = "1"

        }
        layoutView(view, CommonUtils.dip2px(375f), CommonUtils.dip2px(667f))
        return view
    }

    private fun layoutView(v: View, width: Int, height: Int) {
        v.layout(0, 0, width, height)
        val measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY)
        val measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        v.measure(measuredWidth, measuredHeight)
        v.layout(0, 0, v.measuredWidth, v.measuredHeight)
    }
}
