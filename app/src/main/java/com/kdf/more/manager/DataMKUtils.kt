package com.kdf.more.manager

import com.tencent.mmkv.MMKV

class DataMKUtils {

    companion object {
        val instance: MMKV by lazy {
            val instance = MMKV.mmkvWithID("kv_config",MMKV.MULTI_PROCESS_MODE)
            instance
        }
    }

}