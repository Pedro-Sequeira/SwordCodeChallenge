package com.pedrosequeira.scc.domain

import com.pedrosequeira.scc.domain.entities.Pagination

sealed interface Result<out T> {

    data class Success<T>(
        val pagination: Pagination,
        val data: T
    ) : Result<T>

    sealed interface Error : Result<Nothing> {

        sealed interface ApiError : Error {
            data class ClientError(val message: String) : ApiError
            data class NetworkError(val message: String) : ApiError
            object ServiceUnavailable : ApiError
            data class Unknown(val message: String) : ApiError
        }

        data class NotFound(val message: String) : Error
        data class Unknown(val message: String) : Error
    }
}
