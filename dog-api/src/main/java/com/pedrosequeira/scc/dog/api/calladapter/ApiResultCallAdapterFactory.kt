package com.pedrosequeira.scc.dog.api.calladapter

import com.pedrosequeira.scc.dog.api.entities.ApiResult
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit

private const val PARAM_UPPER_BOUND_INDEX_API_RESULT = 0
private const val PARAM_UPPER_BOUND_INDEX_SUCCESS = 0

internal class ApiResultCallAdapterFactory(
    private val headersExtractor: HeadersExtractor
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null
        check(returnType is ParameterizedType) { "Return type must be a parameterized type." }

        val apiResultType = getParameterUpperBound(PARAM_UPPER_BOUND_INDEX_API_RESULT, returnType)
        if (getRawType(apiResultType) != ApiResult::class.java) return null
        check(apiResultType is ParameterizedType) { "Response type must be a parameterized type." }

        val successType = getParameterUpperBound(PARAM_UPPER_BOUND_INDEX_SUCCESS, apiResultType)
        return ApiResultCallAdapter<Any>(successType, headersExtractor)
    }
}
