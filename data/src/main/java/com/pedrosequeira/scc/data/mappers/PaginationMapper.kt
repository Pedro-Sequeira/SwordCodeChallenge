package com.pedrosequeira.scc.data.mappers

import com.pedrosequeira.scc.domain.entities.Pagination
import com.pedrosequeira.scc.data.entities.DataPagination

internal fun DataPagination.toDomain() = Pagination(
    page = page,
    totalPages = totalPages,
    resultsPerPage = resultsPerPage
)
