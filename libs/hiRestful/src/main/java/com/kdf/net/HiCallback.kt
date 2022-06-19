package com.kdf.net

/**
 * callbak 回调
 */
interface HiCallback<T> {
    fun onSuccess(response: HiResponse<T>)
    fun onFailed(throwable: Throwable) {}
}