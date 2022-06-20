package com.kdf.sysframes.data

import android.os.Parcelable
import androidx.lifecycle.LiveData
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class TypeApiData(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String
): Serializable,Parcelable {
    override fun toString(): String {
        return "TypeApiData(userId=$userId, id=$id, title='$title', body='$body')"
    }
}
