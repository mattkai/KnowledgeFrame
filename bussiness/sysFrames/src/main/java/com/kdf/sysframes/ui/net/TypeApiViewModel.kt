package com.kdf.sysframes.ui.net

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kdf.hilog.HiLog
import com.kdf.net.HiCallback
import com.kdf.net.HiResponse
import com.kdf.sysframes.api.NetApi
import com.kdf.sysframes.base.ApiFactory
import com.kdf.sysframes.base.BaseViewModel
import com.kdf.sysframes.base.TypeApiService
import com.kdf.sysframes.data.Student
import com.kdf.sysframes.data.TypeApiData
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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

    /**
     * (3) viewModel + retrofit + rxjava + livedata
     */
    @SuppressLint("CheckResult")
    fun getDataList3() {
//        NetApi.createNetApi().getDataById3(5)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                HiLog.d(it.toString())
//                mTypeApiData.value = it
//            }

        val mStudent1 = Student("kaikai", arrayListOf("chinese","math","english"))
        val mStudent2 = Student("lili", arrayListOf("chinese","english"))
        val mStudent3 = Student("sanzi", arrayListOf("math","english"))

        val list = arrayListOf<Student>(mStudent1,mStudent2,mStudent3)

        Observable.fromIterable(list)
            .map { t ->
                t.courses
            }
            .subscribe { t ->
                t.forEach { course ->
                    HiLog.d("@@@@@@@@ $course")
                }
            }
    }

    val shareFlow = MutableSharedFlow<TypeApiData>()

    val stateFlow = MutableStateFlow<TypeApiData>(TypeApiData(0,0,"",""))

    fun getDataList4() {
//        viewModelScope.launch {
//            shareFlow.emit("12345")
//            shareFlow.emit("abcdf")
//        }

        viewModelScope.launch(Dispatchers.IO) {
            var response = NetApi.createNetApi().getDataById2(5)
            //shareFlow.emit(response)
            stateFlow.emit(response)
        }
    }


}