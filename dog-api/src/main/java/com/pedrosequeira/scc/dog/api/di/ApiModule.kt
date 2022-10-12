package com.pedrosequeira.scc.dog.api.di

import com.pedrosequeira.scc.BuildConfig
import com.pedrosequeira.scc.dog.api.DogsApi
import com.pedrosequeira.scc.dog.api.asConverterFactory
import com.pedrosequeira.scc.dog.api.calladapter.ApiResultCallAdapterFactory
import com.pedrosequeira.scc.dog.api.calladapter.HeadersExtractor
import com.pedrosequeira.scc.dog.api.interceptors.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    fun provideDogsApi(retrofit: Retrofit): DogsApi {
        return retrofit.create(DogsApi::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        callAdapterFactory: ApiResultCallAdapterFactory,
        json: Json
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(json.asConverterFactory())
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthenticationInterceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideApiResultCallAdapterFactory(
        headersExtractor: HeadersExtractor
    ): ApiResultCallAdapterFactory {
        return ApiResultCallAdapterFactory(headersExtractor)
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }
}
