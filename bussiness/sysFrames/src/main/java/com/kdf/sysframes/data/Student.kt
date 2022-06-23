package com.kdf.sysframes.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Student (
    var namer: String,
    var courses: List<String>
): Serializable,Parcelable {

}
