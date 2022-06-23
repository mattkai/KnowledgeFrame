package com.kdf.sysframes.ui

import android.app.Application
import com.google.gson.Gson
import com.kdf.hilog.HiConsolePrinter
import com.kdf.hilog.HiFilePrinter
import com.kdf.hilog.HiLogConfig
import com.kdf.hilog.HiLogManager

class FramesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initLog()
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

}