package com.kdf.net.factory

import com.kdf.hilog.HiLog
import com.kdf.net.*
import com.kdf.net.MethodParser.Companion.SPECIAL_JSON_ARRAY
import com.kdf.net.utils.GsonUtil
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*
import java.lang.IllegalStateException
import okhttp3.MultipartBody

class RetrofitCallFactory(baseUrl: String) : HiCall.Factory {
    private var hiConvert: HiConvert
    private var apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()

        apiService = retrofit.create(ApiService::class.java)
        hiConvert = GsonConvert()
    }

    override fun newCall(request: HiRequest): HiCall<Any> {
        return RetrofitCall(request)
    }

    internal inner class RetrofitCall<T>(val request: HiRequest) : HiCall<T> {
        override fun execute(): HiResponse<T> {
            val realCall: Call<ResponseBody> = createRealCall(request)
            val response: Response<ResponseBody> = realCall.execute()
            return parseResponse(response)
        }


        override fun enqueue(callback: HiCallback<T>) {
            val realCall: Call<ResponseBody> = createRealCall(request)
            realCall.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    callback.onFailed(throwable = t)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    try {
                        val response: HiResponse<T> = parseResponse(response)
                        callback.onSuccess(response)
                    } catch (e: Exception) {
                        callback.onFailed(e)
                    }

                }

            })
        }

        private fun parseResponse(response: Response<ResponseBody>): HiResponse<T> {
            var rawData: String? = null
            if (response.isSuccessful) {
                val body: ResponseBody? = response.body()
                if (body != null) {
                    rawData = body.string()
                }
            } else {
                val body: ResponseBody? = response.errorBody()
                if (body != null) {
                    rawData = body.string()
                }
            }
            return hiConvert.convert(rawData!!, request.returnType!!)
        }

        private fun createRealCall(request: HiRequest): Call<ResponseBody> {
            when (request.httpMethod) {
                HiRequest.METHOD.GET -> {
                    return apiService.get(
                        request.headers,
                        request.endPointUrl(),
                        request.parameters
                    )
                }
                HiRequest.METHOD.POST -> {
                    if (request.multipart) {
                        return apiService.postFile(
                            request.headers,
                            request.endPointUrl(),
                            request.multipartBody
                        )
                    } else {
                        val requestBody: RequestBody = buildRequestBody(request)
                        return apiService.post(request.headers, request.endPointUrl(), requestBody)
                    }

                }
                HiRequest.METHOD.PUT -> {
                    val requestBody: RequestBody = buildRequestBody(request)
                    return apiService.put(request.headers, request.endPointUrl(), requestBody)
                }
                HiRequest.METHOD.DELETE -> {
                    return apiService.delete(request.headers, request.endPointUrl())
                }
                else -> {
                    throw IllegalStateException("hirestful only support GET POST for now ,url=" + request.endPointUrl())
                }
            }

        }

        private fun buildRequestBody(request: HiRequest): RequestBody {
            val parameters: MutableMap<String, Any>? = request.parameters
            val builder = FormBody.Builder()
            val requestBody: RequestBody

            var jsonStr = ""
            if (request.formPost) {
                if (parameters != null) {
                    for ((key, value) in parameters) {

                        builder.add(key, value.toString())
                    }
                }
            } else {
                if (parameters?.containsKey(SPECIAL_JSON_ARRAY) == true) {
                    jsonStr =
                        parameters.get(SPECIAL_JSON_ARRAY)?.let { GsonUtil.toJson(it) }.toString()
                } else {
                    jsonStr = JSONObject(parameters?.let { GsonUtil.toJson(it) }).toString()
                }
            }
            if (request.formPost) {
                requestBody = builder.build()
            } else {
                //bugfix: reuqest-header   content-type="application/json; charset=utf-8"
                requestBody = RequestBody.create(
                    MediaType.parse("application/json;charset=utf-8"),
                    jsonStr
                )
                HiLog.i("request json: $jsonStr")
            }
            return requestBody
        }
    }


    interface ApiService {

        @GET
        fun get(
            @HeaderMap headers: MutableMap<String, String>?, @Url url: String,
            @QueryMap(encoded = true) params: MutableMap<String, Any>?
        ): Call<ResponseBody>

        @POST
        fun post(
            @HeaderMap headers: MutableMap<String, String>?, @Url url: String,
            @Body body: RequestBody?
        ): Call<ResponseBody>

        @POST
        fun postFile(
            @HeaderMap headers: MutableMap<String, String>?, @Url url: String,
            @Body multipartBody: MultipartBody?
        ): Call<ResponseBody>

        @PUT
        fun put(
            @HeaderMap headers: MutableMap<String, String>?,
            @Url url: String,
            @Body body: RequestBody?
        ): Call<ResponseBody>

        @DELETE//不可以携带requestbody
        fun delete(
            @HeaderMap headers: MutableMap<String, String>?,
            @Url url: String
        ): Call<ResponseBody>
    }
}