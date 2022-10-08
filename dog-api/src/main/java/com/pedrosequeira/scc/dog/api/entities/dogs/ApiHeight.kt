package com.pedrosequeira.scc.dog.api.entities.dogs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiHeight(
    @SerialName("imperial")
    val imperial: String? = null,
    @SerialName("metric")
    val metric: String? = null
)
