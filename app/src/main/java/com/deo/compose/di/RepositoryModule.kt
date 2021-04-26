package com.deo.compose.di

import com.deo.compose.repository.PlaceRepository
import com.deo.compose.repository.PlaceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPlaceRepository(
        repository: PlaceRepositoryImpl
    ): PlaceRepository
}
