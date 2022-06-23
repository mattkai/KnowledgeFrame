package com.kdf.sysframes.ui.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kdf.sysframes.R
import com.kdf.sysframes.data.HiltxDoin
import com.kdf.sysframes.databinding.ActivityHiltBinding
import com.kdf.sysframes.ui.hilt.inter.CourseInfo
import com.kdf.sysframes.ui.hilt.inter.moudle.BindHuihuiCourse
import com.kdf.sysframes.ui.hilt.inter.moudle.BindLisiCourse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltExActivity: AppCompatActivity() {

    lateinit var mDataBinding: ActivityHiltBinding
    @Inject
    lateinit var hiltxDoin : HiltxDoin
    @BindHuihuiCourse
    @Inject
    lateinit var courseInfo: CourseInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_hilt)

        hiltxDoin.adders = "Shanghai"
        mDataBinding.apply {
            tvHilt.text = hiltxDoin.adders
        }

        courseInfo.selectChinese()
        courseInfo.selectMath()

    }

}