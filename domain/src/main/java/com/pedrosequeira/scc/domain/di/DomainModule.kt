package com.pedrosequeira.scc.domain.di

import com.pedrosequeira.scc.domain.providers.DispatcherProvider
import com.pedrosequeira.scc.domain.providers.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CoreModule {

    @Binds
    fun bindDispatcherProvider(binding: DispatcherProviderImpl): DispatcherProvider
}
