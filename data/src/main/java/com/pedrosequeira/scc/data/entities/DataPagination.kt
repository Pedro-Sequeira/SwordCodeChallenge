package com.pedrosequeira.scc.data.entities

data class DataPagination(
    val page: Int = 0,
    val totalPages: Int = 0,
    val resultsPerPage: Int = 0
) {
    companion object
}

val DataPagination.Companion.EMPTY: DataPagination
    get() = DataPagination(
        page = 0,
        totalPages = 0,
        resultsPerPage = 0
    )
