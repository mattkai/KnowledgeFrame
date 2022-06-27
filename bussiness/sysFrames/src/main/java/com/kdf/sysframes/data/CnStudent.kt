package com.kdf.sysframes.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "t_student")
data class CnStudent(
    @PrimaryKey
    var id: Long,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var age: Int,
    @ColumnInfo
    var sex: Int
): Parcelable {

    override fun toString(): String {
        return "CnStudent(id=$id, name='$name', age=$age, sex=$sex)"
    }
}
