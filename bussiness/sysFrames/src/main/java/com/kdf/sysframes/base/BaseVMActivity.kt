package com.kdf.sysframes.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kdf.sysframes.data.MsgEvent

abstract class BaseVMActivity<T: BaseViewModel>: AppCompatActivity() {

    companion object {
        const val EVENT_PAGE_CLOSE = 300
    }

    private lateinit var mViewModel: T

    abstract fun getViewModel() : Class<T>

    private lateinit var mEventDataList: MutableLiveData<MsgEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(getViewModel())
        mEventDataList = mViewModel.getEventDataList()
        mEventDataList.observeForever(mEventObserver)
    }

    private val mEventObserver = Observer<MsgEvent> {
        dealEvent(it)
    }

    /**
     * 统一管理 Activity 公共方法
     * 页面关闭、页面toast、页面dialog
     */
    open fun dealEvent(event: MsgEvent?): Boolean {
        when(event?.eventCode) {
            EVENT_PAGE_CLOSE -> {

            }
        }
        return false
    }

}