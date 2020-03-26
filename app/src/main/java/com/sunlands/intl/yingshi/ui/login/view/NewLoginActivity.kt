package com.sunlands.intl.yingshi.ui.login.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.sunlands.intl.yingshi.R
import com.sunlands.intl.yingshi.bean.event.EventMessage
import com.sunlands.intl.yingshi.common.CommonActivity
import com.sunlands.intl.yingshi.constant.RestApi
import com.sunlands.intl.yingshi.greendao.db.LoginInfo
import com.sunlands.intl.yingshi.groovy.CheckNet
import com.sunlands.intl.yingshi.util.Utils.putFirstLoadTime
import com.sunlands.intl.yingshi.web.WebViewActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class NewLoginActivity : CommonActivity<Any>() {


    override fun initView(inflateView: View?, savedInstanceState: Bundle?) {
        super.initView(inflateView, savedInstanceState)
        //设置播放加载路径
        mVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.splash))
        //循环播放
        mVideoView!!.setOnCompletionListener { mVideoView!!.start() }
        mVideoView!!.resume()//prepareAsync
        //播放
        mVideoView!!.start()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getTitleText(): String? {
        return null
    }

    override fun onBackPressed() {
        KeyboardUtils.hideSoftInput(this)
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        hideLoading()
        putFirstLoadTime()
        Handler().postDelayed({ KeyboardUtils.hideSoftInput(this@NewLoginActivity) }, 500)
        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().init()
        if (mVideoView == null) return
        if (mVideoView != null || mVideoView!!.isPlaying) {
            mVideoView!!.start()
        }
    }


    override fun onPause() {
        super.onPause()
        hideLoading()
        mVideoView!!.pause()
    }

    override fun initListener() {
        super.initListener()
        tv_weixin_login.setOnClickListener { weixinLogin() }
        tv_rigster.setOnClickListener { ActivityUtils.startActivity(RegisterActivity::class.java) }
        tv_phone_login.setOnClickListener { ActivityUtils.startActivity(PhoneLoginActivity::class.java) }
        tv_privacy?.setOnClickListener {
            WebViewActivity.goActivity(RestApi.PRIVACY, "")
        }
        tv_protocal?.setOnClickListener {
            WebViewActivity.goActivity(RestApi.PROTOCAL, "")
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(eventMessage: EventMessage) {
        if (eventMessage.getEventType() == EventMessage.EVENT_FINISH) {
            if (mVideoView != null) {
                mVideoView!!.pause()
                mVideoView!!.stopPlayback()
                finish()
            }
        }
    }

    @CheckNet
    private fun weixinLogin() {

//        val weiXin = ShareSDK.getPlatform(Wechat.NAME)
//        if (!weiXin.isClientValid) {
//            ToastUtils.showShort("微信未安装,请先安装微信")
//            return
//        }
//        showProgressDialog()
//        weiXin.removeAccount(true)
//        weiXin.platformActionListener = object : PlatformActionListener {
//            override fun onComplete(platform: Platform, i: Int, hashMap: HashMap<String, Any>?) {
//                runOnUiThread {
//                    if (hashMap != null && !TextUtils.isEmpty(hashMap["unionid"].toString())) {
//                        val unionid = hashMap["unionid"].toString()
//                        val headimgurl = hashMap["headimgurl"].toString()
//                        val sex = if (hashMap["sex"].toString() == "1") "男" else "女"
//                        PwdLoginModel().threadLogin(unionid, PublishSubject.create<Lifecycle.Event>(), object : MVPModelCallbacks<LoginInfo> {
//                            override fun onSuccess(data: LoginInfo) {
//                                LoginInOutHelper.loginIn(data)
//                                startActivity(Intent(this@NewLoginActivity, MainActivity::class.java))
//                                hideLoading()
//                            }
//
//                            override fun onException(model: BaseModel<*>) {
//                                hideLoading()
//                                if (model.code == 3003 || model.code == 3001) {
//                                    val intent = Intent(this@NewLoginActivity, BindPhoneActivity::class.java)
//                                    intent.putExtra(BindPhoneActivity.KEY_UNIONID, unionid)
//                                    intent.putExtra(BindPhoneActivity.KEY_SEX, sex)
//                                    intent.putExtra(BindPhoneActivity.KEY_HEADURL, headimgurl)
//                                    startActivity(intent)
//                                } else {
//                                    ToastUtils.showShort(model.msg)
//                                }
//                            }
//
//                            override fun onError(e: Throwable) {
//                                hideLoading()
//                            }
//                        })
//                    } else {
//                        //                            ToastUtils.showToast("登录失败！");
//                        hideLoading()
//                        weiXin.removeAccount(true)
//                    }
//                }
//            }
//
//            override fun onError(platform: Platform, i: Int, throwable: Throwable) {
//                runOnUiThread {
//                    weiXin.removeAccount(true)
//                    hideLoading()
//                }
//            }
//
//            override fun onCancel(platform: Platform, i: Int) {
//                runOnUiThread {
//                    ToastUtils.showShort("取消授权")
//                    hideLoading()
//                }
//            }
//        }
//        weiXin.SSOSetting(false)
//        weiXin.showUser(null)
    }

    companion object {

        fun show(context: Context) {
            val intent = Intent(context, NewLoginActivity::class.java)
            context.startActivity(intent)
        }
    }

}
