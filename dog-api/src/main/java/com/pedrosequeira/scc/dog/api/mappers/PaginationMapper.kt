package com.pedrosequeira.scc.dog.api.mappers

import com.pedrosequeira.scc.data.entities.DataPagination
import com.pedrosequeira.scc.data.entities.EMPTY
import com.pedrosequeira.scc.dog.api.entities.ApiPagination
import com.pedrosequeira.scc.dog.api.extensions.orZero
import javax.inject.Inject

internal class PaginationMapper @Inject constructor() {

    internal fun mapToData(apiPagination: ApiPagination?): DataPagination {
        return apiPagination?.let {
            DataPagination(
                page = it.page.orZero(),
                totalPages = it.totalPages.orZero(),
                resultsPerPage = it.resultsPerPage.orZero()
            )
        } ?: DataPagination.EMPTY
    }
}
