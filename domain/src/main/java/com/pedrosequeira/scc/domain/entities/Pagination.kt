package com.pedrosequeira.scc.domain.entities

data class Pagination(
    val page: Int = 0,
    val totalPages: Int = 0,
    val resultsPerPage: Int = 0
) {
    val endReached = page == totalPages

    fun nextPage() = copy(page = (page.plus(1)))
}
