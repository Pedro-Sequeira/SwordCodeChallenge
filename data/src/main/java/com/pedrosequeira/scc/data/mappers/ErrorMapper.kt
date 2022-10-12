package com.pedrosequeira.scc.data.mappers

import com.pedrosequeira.scc.data.entities.DataResult.DataError
import com.pedrosequeira.scc.domain.Result.Error
import javax.inject.Inject

internal class ErrorMapper @Inject constructor() {

    fun mapToDomainError(dataError: DataError): Error {
        return when (dataError) {
            is DataError.ApiError -> dataError.toDomainApiError()
            is DataError.NotFound -> Error.NotFound(dataError.message)
            is DataError.Unknown -> Error.Unknown(dataError.message)
        }
    }

    private fun DataError.ApiError.toDomainApiError(): Error.ApiError {
        return when (this) {
            is DataError.ApiError.ClientError -> Error.ApiError.ClientError(message)
            is DataError.ApiError.NetworkError -> Error.ApiError.NetworkError(message)
            is DataError.ApiError.ServiceUnavailable -> Error.ApiError.ServiceUnavailable
            is DataError.ApiError.Unknown -> Error.ApiError.Unknown(message)
        }
    }
}
