package com.kdf.net.utils

import com.google.gson.Gson
import com.kdf.hilog.HiLog
import java.lang.reflect.Type

/**
 * Gson 工具类
 *
 */
class GsonUtil {

    companion object {

        /**
         * 获取 json 对象
         */
        fun <T> fromJson(json: String, classOfT: Class<T>): T? {
            try {
                return Gson().fromJson(json, classOfT)
            } catch (e: Exception) {
                HiLog.e(GsonUtil::class.qualifiedName, "error", e)
            }
            return null
        }

        /**
         * 获取 json 对象
         */
        fun <T> fromJson(json: String, typeOfT: Type): T? {
            try {
                return Gson().fromJson(json, typeOfT)
            } catch (e: Exception) {
                HiLog.e(GsonUtil::class.qualifiedName, json)
                HiLog.e(GsonUtil::class.qualifiedName, "error", e)
            }
            return null
        }

        /**
         * 对象 转 json 字符串
         */
        fun toJson(data: Any): String? {
            try {
                return Gson().toJson(data)
            } catch (e: Exception) {
                HiLog.e(GsonUtil::class.qualifiedName, "data = $data")
                HiLog.e(GsonUtil::class.qualifiedName, "error", e)
            }

            return null
        }
    }
}