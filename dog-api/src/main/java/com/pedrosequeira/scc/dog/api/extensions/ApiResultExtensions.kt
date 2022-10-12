package com.pedrosequeira.scc.dog.api.extensions

import com.pedrosequeira.scc.data.entities.DataPagination
import com.pedrosequeira.scc.data.entities.DataResult
import com.pedrosequeira.scc.dog.api.entities.ApiPagination
import com.pedrosequeira.scc.dog.api.entities.ApiResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
internal inline fun <T, U> ApiResult<T>.mapEither(
    paginationMapping: (ApiPagination?) -> DataPagination,
    success: (T) -> U,
    failure: (ApiResult.ApiError) -> DataResult.DataError
): DataResult<U> {

    contract {
        callsInPlace(paginationMapping, InvocationKind.AT_MOST_ONCE)
        callsInPlace(success, InvocationKind.AT_MOST_ONCE)
        callsInPlace(failure, InvocationKind.AT_MOST_ONCE)
    }

    return when (this) {
        is ApiResult.Success -> DataResult.Success(
            paginationMapping(pagination),
            success(data)
        )
        is ApiResult.ApiError -> failure(this)
    }
}
