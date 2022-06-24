package com.kdf.sysframes.manager

import com.tencent.mmkv.MMKV

class DataStoreManager {

    companion object {
        val instance: MMKV by lazy {
            val instance = MMKV.mmkvWithID("mmkv_config",MMKV.MULTI_PROCESS_MODE)
            instance
        }
    }

}