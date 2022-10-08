package com.pedrosequeira.scc.dog.api.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiWeight(
    @SerialName("imperial")
    val imperial: String? = null,
    @SerialName("metric")
    val metric: String? = null
)
