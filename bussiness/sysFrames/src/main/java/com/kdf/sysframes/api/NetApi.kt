package com.kdf.sysframes.api

import com.kdf.sysframes.api.inters.NetApiService
import com.kdf.sysframes.base.TypeApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface NetApi {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"

        fun createNetApi(): NetApiService {
            val client = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .readTimeout((60 * 1000).toLong(), TimeUnit.MILLISECONDS)
                .connectTimeout((20 * 1000).toLong(), TimeUnit.MILLISECONDS)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetApiService::class.java)
        }
    }

}