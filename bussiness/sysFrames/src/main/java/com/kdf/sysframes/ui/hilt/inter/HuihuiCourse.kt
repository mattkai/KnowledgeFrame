package com.kdf.sysframes.ui.hilt.inter

import com.kdf.hilog.HiLog
import javax.inject.Inject

class HuihuiCourse @Inject constructor(): CourseInfo {

    override fun selectMath() {
        HiLog.d("Huihui selectMath")
    }

    override fun selectChinese() {
        HiLog.d("Huihui selectChinese")
    }
}