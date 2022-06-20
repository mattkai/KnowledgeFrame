package com.kdf.sysframes.base

import com.kdf.net.HiRestful
import com.kdf.net.factory.RetrofitCallFactory

object ApiFactory {

    var baseUrl = "http://jsonplaceholder.typicode.com"

    private val hiRestful: HiRestful by lazy {
        val hiRestful = HiRestful(baseUrl,RetrofitCallFactory(baseUrl))
        hiRestful
    }

    fun <T> create(service: Class<T>): T {
        return hiRestful.create(service)
    }

}