package com.deo.compose.repository

import com.deo.compose.model.dao.PlaceDao
import com.deo.compose.model.entity.Place
import javax.inject.Inject

class PlaceSQLiteRepository @Inject constructor(
    private val placeDao: PlaceDao,
) : PlaceRepository {

    override suspend fun checkInPlace(
        name: String,
        latitude: Double,
        longitude: Double
    ): Long = placeDao.upsert(
        name = name, latitude = latitude, longitude = longitude
    )

    override suspend fun fetchPlacesWithinRadius(
        latitude: Double,
        longitude: Double,
        radius: Int
    ): List<Place> = placeDao.getPlacesWithinRadius(
        latitude = latitude,
        longitude = longitude,
        radius = radius
    )
}
