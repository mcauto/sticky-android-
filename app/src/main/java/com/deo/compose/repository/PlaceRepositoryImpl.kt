package com.deo.compose.repository

import com.deo.compose.model.dao.PlaceDao
import com.deo.compose.model.dao.SpatialDao
import com.deo.compose.model.entity.Place
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeDao: PlaceDao,
    private val rawQueryDao: SpatialDao,
) : PlaceRepository {

    override suspend fun checkInPlace(
        name: String,
        latitude: Double,
        longitude: Double
    ): Long {
        val result = rawQueryDao.makePoint(1.0, 0.0)
        return placeDao.upsert(
            Place(
                name = "강남 11출 또피빈",
                geometry = result
            )
        )
    }
}