package com.pedrosequeira.scc.data.mappers

import com.pedrosequeira.scc.data.entities.DataPagination
import com.pedrosequeira.scc.domain.entities.Pagination
import javax.inject.Inject

internal class PaginationMapper @Inject constructor() {

    internal fun mapToDomainPagination(
        dataPagination: DataPagination
    ): Pagination = with(dataPagination) {
        return Pagination(
            page = page,
            totalPages = totalPages,
            resultsPerPage = resultsPerPage
        )
    }
}
