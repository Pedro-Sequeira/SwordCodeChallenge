package com.pedrosequeira.scc.data.entities

sealed class DataResult<out T> {

    data class Success<T>(
        val pagination: DataPagination,
        val data: T
    ) : DataResult<T>()

    data class Error(
        val message: String
    ) : DataResult<Nothing>()
}
