package com.deo.compose.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.deo.compose.model.dao.*
import com.deo.compose.model.entity.*
import com.deo.compose.model.dao.PlaceCategoryCrossRefDao

@Database(
    entities = [
        Place::class,
        Category::class,
        PlaceCategoryCrossRef::class,
        Accumulate::class,
        PlaceAccumulateCrossRef::class,
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class StickyDatabase : RoomDatabase() {
    internal abstract fun getPlaceDao(): PlaceDao
    internal abstract fun getCategoryDao(): CategoryDao
    internal abstract fun getPlaceCategoryCrossRefDao(): PlaceCategoryCrossRefDao
    internal abstract fun getAccumulateDao(): AccumulateDao
    internal abstract fun getPlaceAccumulateCrossRefDao(): PlaceAccumulateCrossRefDao
    internal abstract fun getSpatialDao(): SpatialDao
}