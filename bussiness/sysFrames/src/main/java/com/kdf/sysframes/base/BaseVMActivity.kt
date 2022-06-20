package com.kdf.sysframes.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kdf.sysframes.data.MsgEvent

abstract class BaseVMActivity<T: BaseViewModel>: AppCompatActivity() {

    companion object {
        const val EVENT_PAGE_CLOSE = 300 // 页面关闭
        const val EVENT_PAGE_PROMPT = 301 // 页面提示
        const val EVENT_PAGE_DIALOG = 302 // 页面弹出框
    }

    lateinit var mViewModel: T

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
                finish()
                return true
            }
            EVENT_PAGE_PROMPT -> {
                Toast.makeText(this,event.extraObj.toString(),
                    Toast.LENGTH_SHORT).show()
                return true
            }
            EVENT_PAGE_DIALOG -> {
                return true
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        mEventDataList.removeObserver(mEventObserver)
    }

}