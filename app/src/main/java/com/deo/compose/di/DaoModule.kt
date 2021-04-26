package com.deo.compose.di

import com.deo.compose.model.StickyDatabase
import com.deo.compose.model.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun providePlaceDao(
        database: StickyDatabase
    ): PlaceDao = database.getPlaceDao()

    @Singleton
    @Provides
    fun provideCategoryDao(
        database: StickyDatabase
    ): CategoryDao = database.getCategoryDao()

    @Singleton
    @Provides
    fun providePlaceCategoryCrossRefDao(
        database: StickyDatabase
    ): PlaceCategoryCrossRefDao = database.getPlaceCategoryCrossRefDao()

    @Singleton
    @Provides
    fun provideAccumulateDao(
        database: StickyDatabase
    ): AccumulateDao = database.getAccumulateDao()

    @Singleton
    @Provides
    fun providePlaceAccumulateCrossRefDao(
        database: StickyDatabase
    ): PlaceAccumulateCrossRefDao = database.getPlaceAccumulateCrossRefDao()

    @Singleton
    @Provides
    fun provideSpatialDao(
        database: StickyDatabase
    ): SpatialDao = database.getSpatialDao()
}
