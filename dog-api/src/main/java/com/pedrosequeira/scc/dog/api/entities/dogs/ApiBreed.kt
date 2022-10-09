package com.pedrosequeira.scc.dog.api.entities.dogs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiBreed(
    @SerialName("weight")
    val weight: ApiWeight? = null,
    @SerialName("height")
    val height: ApiHeight? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("bred_for")
    val bredFor: String? = null,
    @SerialName("breed_group")
    val breedGroup: String? = null,
    @SerialName("life_span")
    val lifeSpan: String? = null,
    @SerialName("temperament")
    val temperament: String? = null,
    @SerialName("origin")
    val origin: String? = null,
    @SerialName("reference_image_id")
    val referenceImageId: String? = null
)
