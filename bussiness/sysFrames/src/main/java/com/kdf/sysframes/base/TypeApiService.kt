package com.kdf.sysframes.base

import com.kdf.net.HiCall
import com.kdf.net.HiCallback
import com.kdf.net.annotation.GET
import com.kdf.net.annotation.Path
import com.kdf.sysframes.data.TypeApiData

interface TypeApiService {

    @GET("/posts/{id}")
    fun getDataById(@Path("id") id: Int): HiCall<TypeApiData>

}