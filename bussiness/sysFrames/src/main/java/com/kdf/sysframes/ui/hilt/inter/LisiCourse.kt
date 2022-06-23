package com.kdf.sysframes.ui.hilt.inter

import com.kdf.hilog.HiLog
import javax.inject.Inject

class LisiCourse @Inject constructor(): CourseInfo {

    override fun selectMath() {
        HiLog.d("lisi selectMath")
    }

    override fun selectChinese() {
        HiLog.d("lisi selectChinese")
    }
}