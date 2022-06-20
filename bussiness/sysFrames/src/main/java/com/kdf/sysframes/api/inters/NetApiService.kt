package com.kdf.sysframes.api.inters

import com.kdf.sysframes.data.TypeApiData
import retrofit2.http.GET
import retrofit2.http.Path

interface NetApiService {

    @GET("/posts/{id}")
    suspend fun getDataById2(@Path("id") id: Int): TypeApiData

}