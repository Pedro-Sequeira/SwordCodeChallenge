package com.pedrosequeira.scc.dog.api.calladapter

import com.pedrosequeira.scc.dog.api.entities.ApiResult
import com.pedrosequeira.scc.dog.api.entities.ApiResult.ApiError.HttpError
import com.pedrosequeira.scc.dog.api.entities.ApiResult.ApiError.NetworkError
import com.pedrosequeira.scc.dog.api.entities.ApiResult.ApiError.UnknownError
import com.pedrosequeira.scc.dog.api.entities.ApiResult.Success
import java.io.IOException
import java.lang.reflect.Type
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ApiResultCall<T>(
    private val delegate: Call<T>,
    private val successType: Type,
    private val headersExtractor: HeadersExtractor
) : Call<ApiResult<T>> {

    override fun enqueue(callback: Callback<ApiResult<T>>) {
        delegate.enqueue(
            onResponse = { _, response ->
                callback.onResponse(this, Response.success(response.toApiResult()))
            },
            onFailure = { _, throwable ->
                callback.onResponse(this, Response.success(throwable.toApiResult()))
            }
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun Response<T>.toApiResult(): ApiResult<T> {
        if (isSuccessful.not()) {
            val httpCode = code()
            val message = message()

            return HttpError(httpCode, message)
        }

        body()?.let { body ->
            val pagination = headersExtractor.extractHeaders(this)
            return Success(body, pagination)
        }

        return if (successType == Unit::class.java) {
            (Success(Unit) as ApiResult<T>)
        } else {
            UnknownError(IllegalStateException("The response body was null."))
        }
    }

    private fun Throwable.toApiResult(): ApiResult<T> {
        return when (this) {
            is IOException -> NetworkError(this)
            else -> UnknownError(this)
        }
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    override fun execute(): Response<ApiResult<T>> {
        throw UnsupportedOperationException()
    }

    override fun clone(): Call<ApiResult<T>> {
        return ApiResultCall(delegate.clone(), successType, headersExtractor)
    }
}
