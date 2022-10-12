package com.pedrosequeira.scc.domain

import com.pedrosequeira.scc.domain.entities.Pagination

sealed class Result<out T> {

    data class Success<T>(
        val pagination: Pagination,
        val data: T
    ) : Result<T>()

    data class Error(
        val message: String
    ) : Result<Nothing>()
}
