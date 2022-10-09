package com.pedrosequeira.scc.dog.api.entities.dogs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiImage(
    @SerialName("breeds")
    val breeds: List<ApiBreed?>? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("width")
    val width: Int? = null,
    @SerialName("height")
    val height: Int? = null
)
