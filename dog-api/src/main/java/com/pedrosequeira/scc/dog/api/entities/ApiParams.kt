package com.pedrosequeira.scc.dog.api.entities

internal object ApiParams {

    object Headers {

        object Request {
            const val API_KEY = "x-api-key"
        }

        object Response {
            const val PAGE = "pagination-page"
            const val TOTAL_PAGES = "pagination-count"
            const val RESULTS_PER_PAGE = "pagination-limit"
        }
    }

    object Queries {
        const val ONLY_WITH_BREED_INFO = "has_breeds"
        const val ORDERING = "order"
        const val PAGE = "page"
        const val ITEMS_PER_PAGE = "limit"
    }
}
