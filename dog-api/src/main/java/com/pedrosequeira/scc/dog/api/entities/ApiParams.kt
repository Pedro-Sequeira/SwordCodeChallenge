package com.pedrosequeira.scc.dog.api.entities

internal object ApiParams {

    object Headers {
        const val API_KEY = "x-api-key"
    }

    object Queries {
        const val ONLY_WITH_BREED_INFO = "has_breeds"
        const val ORDERING = "order"
        const val PAGE = "page"
        const val ITEMS_PER_PAGE = "limit"
    }
}
