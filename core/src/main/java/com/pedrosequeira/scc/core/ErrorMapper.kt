package com.pedrosequeira.scc.core

import com.pedrosequeira.scc.domain.Result
import javax.inject.Inject

class ErrorMapper @Inject constructor(
    private val stringProvider: StringProvider
) {

    fun mapToMessage(error: Result.Error): String {
        return when (error) {
            is Result.Error.ApiError -> error.toMessage()

            is Result.Error.NotFound,
            is Result.Error.Unknown -> stringProvider.getString(R.string.error_unknown_message)
        }
    }

    private fun Result.Error.ApiError.toMessage(): String {
        return stringProvider.getString(
            when (this) {
                is Result.Error.ApiError.NetworkError -> R.string.error_api_network_message
                is Result.Error.ApiError.ServiceUnavailable -> R.string.error_api_server_message

                is Result.Error.ApiError.ClientError,
                is Result.Error.ApiError.Unknown -> R.string.error_api_unknown_message
            }
        )
    }
}
