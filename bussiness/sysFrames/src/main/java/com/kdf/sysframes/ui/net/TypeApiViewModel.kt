package com.kdf.sysframes.ui.net

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.kdf.hilog.HiLog
import com.kdf.net.HiCallback
import com.kdf.net.HiResponse
import com.kdf.sysframes.api.NetApi
import com.kdf.sysframes.base.ApiFactory
import com.kdf.sysframes.base.BaseViewModel
import com.kdf.sysframes.base.TypeApiService
import com.kdf.sysframes.data.TypeApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TypeApiViewModel(context: Application): BaseViewModel(context) {

    /**
     * (1) viewModel + retrofit
     */
    fun getDataList() {
        with(ApiFactory) {
            create(TypeApiService::class.java).getDataById(5)
                .enqueue(object: HiCallback<TypeApiData> {
                override fun onSuccess(response: HiResponse<TypeApiData>) {
                   HiLog.d("${response.toString()}")
                }

                override fun onFailed(throwable: Throwable) {
                    super.onFailed(throwable)
                    HiLog.d("${throwable.message}")
                }
            })
        }

        //sendEvent()
    }

    /**
     * viewModel + 协成
     */
    fun getDataList2() {
        viewModelScope.launch(Dispatchers.Main) {
            launch(Dispatchers.IO) {
                val response = NetApi.createNetApi().getDataById2(5)
                HiLog.d("${response.toString()}")
            }
        }
    }

}