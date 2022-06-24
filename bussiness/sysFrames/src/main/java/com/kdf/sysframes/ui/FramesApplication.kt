package com.kdf.sysframes.ui

import android.app.Application
import com.google.gson.Gson
import com.kdf.hilog.*
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class FramesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initLog()
        initMMKV()
    }

    private fun initLog() {
        HiLogManager.init(
            object : HiLogConfig() {
                override fun injectJsonParser(): JsonParser {
                    return JsonParser { src: Any? -> Gson().toJson(src) }
                }

                override fun includeThread(): Boolean {
                    return false
                }

                override fun stackTraceDepth(): Int {
                    return 1
                }
            },
            HiConsolePrinter(),
            HiFilePrinter.getInstance(
                this.externalCacheDir?.absolutePath,
                60 * 60 * 1000
            )
        )
    }

    private fun initMMKV() {
        val filePath = "${filesDir.absolutePath}/mmkv_2"
        val mmkvPath = MMKV.initialize(this,filePath)
        HiLog.d("mmkvPath: $mmkvPath")
    }

}