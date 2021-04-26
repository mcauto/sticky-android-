package com.deo.compose.repository

interface PlaceRepository {
    /**
     * 장소 체크인
     */
    suspend fun checkInPlace(
        name: String,
        latitude: Double,
        longitude: Double
    ): Long
}
