package com.kdf.sysframes.api.inters

import com.kdf.sysframes.data.TypeApiData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.reflect.Type

interface NetApiService {

    @GET("/posts/{id}")
    suspend fun getDataById2(@Path("id") id: Int): TypeApiData

    @GET("/posts/{id}")
    fun getDataById3(@Path("id") id: Int): Observable<TypeApiData>

}