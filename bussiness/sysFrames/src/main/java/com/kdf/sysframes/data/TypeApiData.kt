package com.kdf.sysframes.data

import java.io.Serializable

data class TypeApiData(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String
): Serializable {

    override fun toString(): String {
        return "TypeApiData(userId=$userId, id=$id, title='$title', body='$body')"
    }
}
