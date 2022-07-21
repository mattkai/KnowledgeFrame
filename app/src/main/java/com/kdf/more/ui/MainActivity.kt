package com.kdf.more.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kdf.more.databinding.ActivityMainBinding
import com.kdf.more.manager.DataMKUtils
import com.kdf.more.manager.UmengSetting.umengInit
import com.umeng.analytics.MobclickAgent

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        mBinding.apply {
            btnData.setOnClickListener {
                startActivity(Intent(this@MainActivity, StaticActivity::class.java))
            }

            btnGuide.setOnClickListener {
                startActivity(Intent(this@MainActivity,RooikeGuideActivity::class.java))
            }

        }

        if(DataMKUtils.instance.getBoolean("isProvicy",false)) {
            val dialog = AlertDialog.Builder(this)
                .setTitle("隐私政策")
                .setMessage("是否同意获取个人用户信息")
                .setNegativeButton("取消"
                ) { dialog, which -> dialog.dismiss() }
                .setPositiveButton("确定"
                ) { dialog, which ->
                    DataMKUtils.instance.putBoolean("isProvicy",true)
                    dialog.dismiss()
                    Thread.sleep(500)
                    umengInit(applicationContext)
                }.show()
        }
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(applicationContext)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(applicationContext)
    }

}