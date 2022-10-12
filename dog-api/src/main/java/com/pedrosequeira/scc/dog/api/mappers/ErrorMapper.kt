package com.pedrosequeira.scc.dog.api.mappers

import com.pedrosequeira.scc.data.entities.DataResult
import com.pedrosequeira.scc.dog.api.entities.ApiResult.ApiError
import com.pedrosequeira.scc.dog.api.entities.httpErrorMessage
import com.pedrosequeira.scc.dog.api.entities.isHttpError
import com.pedrosequeira.scc.dog.api.entities.isNetworkError
import com.pedrosequeira.scc.dog.api.entities.isServerError
import com.pedrosequeira.scc.dog.api.entities.isUnknownError
import com.pedrosequeira.scc.dog.api.entities.networkErrorMessage
import com.pedrosequeira.scc.dog.api.entities.unknownErrorMessage
import javax.inject.Inject

class ErrorMapper @Inject constructor() {

    fun mapToDataError(apiError: ApiError): DataResult.DataError = with(apiError) {
        return when {
            isServerError -> DataResult.DataError.ApiError.ServiceUnavailable
            isHttpError -> DataResult.DataError.ApiError.ClientError(httpErrorMessage)
            isNetworkError -> DataResult.DataError.ApiError.NetworkError(networkErrorMessage)
            isUnknownError -> DataResult.DataError.ApiError.Unknown(unknownErrorMessage)

            else -> throw IllegalStateException("Could not map the api error $this to a data error.")
        }
    }
}
