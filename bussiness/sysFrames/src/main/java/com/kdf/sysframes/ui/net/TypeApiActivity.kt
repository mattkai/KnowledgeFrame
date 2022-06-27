package com.kdf.sysframes.ui.net

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.kdf.hilog.HiLog
import com.kdf.net.thread.bgThreadRun
import com.kdf.sysframes.R
import com.kdf.sysframes.base.BaseVMActivity
import com.kdf.sysframes.data.TypeApiData
import com.kdf.sysframes.databinding.ActivityTypeApiBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TypeApiActivity: BaseVMActivity<TypeApiViewModel>(){

    private lateinit var mDataBinding: ActivityTypeApiBinding

    override fun getViewModel(): Class<TypeApiViewModel> {
        return TypeApiViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView<ActivityTypeApiBinding>(this,
            R.layout.activity_type_api)

        mDataBinding.apply {
            btnListData.setOnClickListener {
                mViewModel.getDataList4()
            }

            mViewModel.mTypeApiData.observeForever { data ->
                tvData.text = data.title
            }

            lifecycleScope.launch {
                mViewModel.shareFlow.collect {
                    HiLog.d( "### ${it.toString()}")
                }
            }

        }

    }

    override fun onResume() {
        super.onResume()
    }


}