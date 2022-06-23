package com.kdf.sysframes.ui.hilt.inter.moudle

import com.kdf.sysframes.ui.hilt.inter.CourseInfo
import com.kdf.sysframes.ui.hilt.inter.HuihuiCourse
import com.kdf.sysframes.ui.hilt.inter.LisiCourse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class CourseMoudle {

    @BindLisiCourse
    @Binds
    abstract fun getCourses(lisiCourse: LisiCourse): CourseInfo

    @BindHuihuiCourse
    @Binds
    abstract fun getCourses2(huihuiCourse: HuihuiCourse): CourseInfo

}