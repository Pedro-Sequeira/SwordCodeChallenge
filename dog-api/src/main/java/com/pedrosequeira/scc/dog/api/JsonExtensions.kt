package com.pedrosequeira.scc.dog.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter

@OptIn(ExperimentalSerializationApi::class)
internal fun Json.asConverterFactory(): Converter.Factory {
    return asConverterFactory("application/json".toMediaType())
}

