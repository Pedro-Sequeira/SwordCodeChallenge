package com.pedrosequeira.scc.data.extensions

import com.pedrosequeira.scc.data.entities.DataPagination
import com.pedrosequeira.scc.data.entities.DataResult
import com.pedrosequeira.scc.domain.Result
import com.pedrosequeira.scc.domain.entities.Pagination
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
internal inline fun <T, U> DataResult<T>.mapEither(
    paginationMapping: (DataPagination) -> Pagination,
    success: (T) -> U,
    failure: (DataResult.DataError) -> Result.Error
): Result<U> {

    contract {
        callsInPlace(paginationMapping, kotlin.contracts.InvocationKind.AT_MOST_ONCE)
        callsInPlace(success, kotlin.contracts.InvocationKind.AT_MOST_ONCE)
        callsInPlace(failure, kotlin.contracts.InvocationKind.AT_MOST_ONCE)
    }

    return when (this) {
        is DataResult.Success -> Result.Success(
            paginationMapping(pagination),
            success(data)
        )
        is DataResult.DataError -> failure(this)
    }
}

