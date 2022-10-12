package com.pedrosequeira.scc.dog.api.entities

object ApiHeaders {

    object Request {
        const val API_KEY = "x-api-key"
    }

    object Response {
        const val PAGE = "pagination-page"
        const val TOTAL_PAGES = "pagination-count"
        const val RESULTS_PER_PAGE = "pagination-limit"
    }
}
