package com.kdf.sysframes.ui.net

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kdf.net.thread.bgThreadRun
import com.kdf.sysframes.R
import com.kdf.sysframes.base.BaseVMActivity
import com.kdf.sysframes.data.TypeApiData
import com.kdf.sysframes.databinding.ActivityTypeApiBinding

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
                mViewModel.getDataList3()
            }

            mViewModel.mTypeApiData.observeForever { data ->
                tvData.text = data.title
            }

        }

    }

    override fun onResume() {
        super.onResume()
    }


}