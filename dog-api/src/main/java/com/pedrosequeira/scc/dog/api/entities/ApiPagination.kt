package com.pedrosequeira.scc.dog.api.entities

data class ApiPagination(
    val page: Int? = null,
    val totalPages: Int? = null,
    val resultsPerPage: Int? = null
)

