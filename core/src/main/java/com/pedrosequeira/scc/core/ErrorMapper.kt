package com.pedrosequeira.scc.core

import com.pedrosequeira.scc.domain.Result.Error
import com.pedrosequeira.scc.domain.Result.Error.ApiError
import com.pedrosequeira.scc.domain.Result.Error.ApiError.ClientError
import com.pedrosequeira.scc.domain.Result.Error.ApiError.NetworkError
import com.pedrosequeira.scc.domain.Result.Error.ApiError.ServiceUnavailable
import com.pedrosequeira.scc.domain.Result.Error.NotFound
import com.pedrosequeira.scc.domain.Result.Error.Unknown
import javax.inject.Inject

class ErrorMapper @Inject constructor(
    private val stringProvider: StringProvider
) {

    fun mapToMessage(error: Error): String {
        return when (error) {
            is ApiError -> error.toMessage()
            is NotFound,
            is Unknown -> stringProvider.getString(R.string.error_unknown_message)
        }
    }

    private fun ApiError.toMessage(): String {
        return stringProvider.getString(
            when (this) {
                is NetworkError -> R.string.error_api_network_message
                is ServiceUnavailable -> R.string.error_api_server_message
                is ClientError,
                is ApiError.Unknown -> {
                    R.string.error_api_unknown_message
                }
            }
        )
    }
}
