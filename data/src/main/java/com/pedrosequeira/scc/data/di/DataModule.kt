package com.pedrosequeira.scc.data.di

import com.pedrosequeira.scc.data.ImagesRepositoryImpl
import com.pedrosequeira.scc.domain.ImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindImagesRepository(
        imagesRepositoryImpl: ImagesRepositoryImpl
    ): ImagesRepository
}
