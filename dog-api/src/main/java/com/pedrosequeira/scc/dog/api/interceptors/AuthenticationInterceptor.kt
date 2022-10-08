package com.pedrosequeira.scc.dog.api.interceptors

import com.pedrosequeira.scc.BuildConfig
import com.pedrosequeira.scc.dog.api.entities.ApiParams.Headers.API_KEY
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthenticationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authenticatedRequest = chain
            .request()
            .newBuilder()
            .addHeader(API_KEY, BuildConfig.API_KEY)
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
