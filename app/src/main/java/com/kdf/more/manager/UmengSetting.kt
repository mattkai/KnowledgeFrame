package com.kdf.more.manager

import android.content.Context
import com.umeng.commonsdk.UMConfigure

object UmengSetting {

    public fun umengInit(mcontext: Context){
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(
            mcontext, "62c5864205844627b5dba91a", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
            ""
        )
    }

}