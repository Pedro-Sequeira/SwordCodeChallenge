package com.pedrosequeira.scc.dog.api.calladapter

import com.pedrosequeira.scc.dog.api.entities.ApiHeaders.Response.PAGE
import com.pedrosequeira.scc.dog.api.entities.ApiHeaders.Response.RESULTS_PER_PAGE
import com.pedrosequeira.scc.dog.api.entities.ApiHeaders.Response.TOTAL_PAGES
import com.pedrosequeira.scc.dog.api.entities.ApiPagination
import javax.inject.Inject
import okhttp3.Headers
import retrofit2.Response

internal class HeadersExtractor @Inject constructor() {

    fun <T> extractHeaders(response: Response<T>): ApiPagination = with(response) {
        val headers = headers()
        val pageHeader = headers.find(PAGE)
        val totalPages = headers.find(TOTAL_PAGES)
        val resultsPerPage = headers.find(RESULTS_PER_PAGE)

        return ApiPagination(
            page = pageHeader,
            totalPages = totalPages,
            resultsPerPage = resultsPerPage
        )
    }

    private fun Headers.find(key: String): Int {
        return this.find { it.first == key }?.second?.toIntOrNull() ?: 0
    }
}
