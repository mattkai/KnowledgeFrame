package com.kdf.sysframes.ui.net

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kdf.sysframes.R
import com.kdf.sysframes.base.BaseVMActivity
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
                mViewModel.getDataList2()
            }
        }

    }

}