package com.pedrosequeira.scc.data.entities

sealed interface DataResult<out T> {

    data class Success<T>(
        val pagination: DataPagination,
        val data: T
    ) : DataResult<T>

    sealed interface DataError : DataResult<Nothing> {

        sealed interface ApiError : DataError {
            data class ClientError(val message: String) : ApiError
            data class NetworkError(val message: String) : ApiError
            object ServiceUnavailable : ApiError
            data class Unknown(val message: String) : ApiError
        }

        data class NotFound(val message: String) : DataError
        data class Unknown(val message: String) : DataError
    }
}
