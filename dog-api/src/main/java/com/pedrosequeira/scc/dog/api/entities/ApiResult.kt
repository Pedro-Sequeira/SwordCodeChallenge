package com.pedrosequeira.scc.dog.api.entities

import com.pedrosequeira.scc.dog.api.entities.ApiResult.ApiError

sealed interface ApiResult<out T> {

    data class Success<T>(
        val data: T,
        val pagination: ApiPagination? = null
    ) : ApiResult<T>

    sealed interface ApiError : ApiResult<Nothing> {
        data class HttpError(val code: Int, val message: String) : ApiError
        data class NetworkError(val throwable: Throwable) : ApiError
        data class UnknownError(val throwable: Throwable) : ApiError
    }
}

val ApiError.isHttpError: Boolean
    get() = this is ApiError.HttpError

val ApiError.isServerError: Boolean
    get() = this is ApiError.HttpError && code >= HttpCodes.SERVER_ERROR

val ApiError.isNetworkError: Boolean
    get() = this is ApiError.NetworkError

val ApiError.isUnknownError: Boolean
    get() = this is ApiError.UnknownError

val ApiError.httpErrorMessage: String
    get() = if (this is ApiError.HttpError) message else ""

val ApiError.networkErrorMessage: String
    get() = (if (this is ApiError.NetworkError) (throwable.message ?: "") else "")

val ApiError.unknownErrorMessage: String
    get() = (if (this is ApiError.UnknownError) (throwable.message ?: "") else "")

