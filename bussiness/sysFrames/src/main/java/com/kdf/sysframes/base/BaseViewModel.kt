package com.kdf.sysframes.base

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kdf.net.thread.mainThreadRun
import com.kdf.sysframes.data.MsgEvent

class BaseViewModel(application: Application): AndroidViewModel(application) {

    private val mContext: Application by lazy {
        application
    }

    private val mEventDataList = MutableLiveData<MsgEvent>()

    fun getEventDataList() = mEventDataList

    fun sendEvent(eventCode: Int, extras: Bundle = Bundle(), extrasObj: Any?) {
        mainThreadRun {
            mEventDataList.value = MsgEvent(eventCode,extras,extrasObj)
        }
    }


}