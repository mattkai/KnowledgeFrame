package com.kdf.sysframes.ui.net

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kdf.hilog.HiLog
import com.kdf.net.HiCallback
import com.kdf.net.HiResponse
import com.kdf.net.thread.mainThreadRun
import com.kdf.sysframes.api.NetApi
import com.kdf.sysframes.base.ApiFactory
import com.kdf.sysframes.base.BaseViewModel
import com.kdf.sysframes.base.TypeApiService
import com.kdf.sysframes.data.TypeApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TypeApiViewModel(context: Application): BaseViewModel(context) {

    val mTypeApiData = MutableLiveData<TypeApiData>()

    /**
     * response
     * 【 code msg data 】
     *
     * (1) viewModel + retrofit + livedata
     */
    fun getDataList() {
        ApiFactory.create(TypeApiService::class.java).getDataById(5)
                .enqueue(object: HiCallback<TypeApiData> {
                override fun onSuccess(response: HiResponse<TypeApiData>) {
                    HiLog.d(response.rawData)
                    val gson = Gson()
                    val data = gson.fromJson<TypeApiData>(response.rawData,
                        TypeApiData::class.java)
                    mTypeApiData.value = data
                }

                override fun onFailed(throwable: Throwable) {
                    super.onFailed(throwable)
                    HiLog.d("${throwable.message}")
                }
            })
    }

    /**
     * (2) viewModel + 协成 +livedata
     */
    fun getDataList2() {
        viewModelScope.launch(Dispatchers.IO) {
            var response = NetApi.createNetApi().getDataById2(5)
            withContext(Dispatchers.Main) {
                mTypeApiData.value = response
            }
        }
    }

}