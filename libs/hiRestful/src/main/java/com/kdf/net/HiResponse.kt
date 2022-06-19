package com.kdf.net

/**
 * 响应报文
 */
open class HiResponse<T> {

    companion object {
        const val SUCCESS: Int = 2000
        const val CACHE_SUCCESS: Int = 304
        const val RC_NEED_LOGIN = 5003               //请先登录
        const val RC_AUTH_TOKEN_EXPIRED = 1004    //访问Token过期，请重新设置
        const val RC_AUTH_TOKEN_INVALID = 4031   //访问Token不正确，请重新设置
        const val SYSTEM_ERROR = 6000
    }

    var rawData: String? = null//原始数据
    var code = 0//业务状态码
    var data: T? = null//业务数据
    var errorData: Map<String, String>? = null //错误状态下的数据

    var msg: String? = null//错误信息


    fun successful(): Boolean {
        return code == SUCCESS || code == CACHE_SUCCESS
    }
}