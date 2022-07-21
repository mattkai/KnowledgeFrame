package com.kdf.more.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.kdf.more.R
import com.umeng.analytics.MobclickAgent

class StaticActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statics)

        findViewById<AppCompatButton>(R.id.btn_mdo).setOnClickListener {
            MobclickAgent.onEvent(applicationContext,"app_click_btn1")
        }

        findViewById<AppCompatButton>(R.id.btn_mdt).setOnClickListener {
            val map = HashMap<String,String>()
            map.put("param_k","2")
            MobclickAgent.onEvent(applicationContext,"app_click_btn2",map)
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