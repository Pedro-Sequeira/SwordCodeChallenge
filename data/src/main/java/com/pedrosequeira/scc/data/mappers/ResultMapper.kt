package com.pedrosequeira.scc.data.mappers

import com.pedrosequeira.scc.data.entities.DataResult
import com.pedrosequeira.scc.domain.Result

internal inline fun <S, U> DataResult<S>.map(success: (S) -> U): Result<U> {
    return when (this) {
        is DataResult.Success -> Result.Success(pagination.toDomain(), success(data))
        is DataResult.Error -> Result.Error(message)
    }
}
