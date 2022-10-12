package com.pedrosequeira.scc.dog.api.calladapter

import com.pedrosequeira.scc.dog.api.entities.ApiResult
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter

internal class ApiResultCallAdapter<R>(
    private val successType: Type,
    private val headersExtractor: HeadersExtractor
) : CallAdapter<R, Call<ApiResult<R>>> {

    override fun adapt(call: Call<R>): Call<ApiResult<R>> {
        return ApiResultCall(
            delegate = call,
            successType = successType,
            headersExtractor = headersExtractor
        )
    }

    override fun responseType(): Type = successType
}

