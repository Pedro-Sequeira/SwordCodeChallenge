package com.pedrosequeira.scc.dog.api.di

import com.pedrosequeira.scc.data.ImagesDataSource
import com.pedrosequeira.scc.dog.api.ImagesRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindImageRemoteDataSource(
        imagesRemoteDataSource: ImagesRemoteDataSource
    ): ImagesDataSource
}
