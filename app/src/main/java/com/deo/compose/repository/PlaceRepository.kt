package com.deo.compose.repository

import com.deo.compose.model.entity.Place

interface PlaceRepository {
    /**
     * 장소 체크인
     *
     * name: 장소명
     * latitude: 위도
     * longitude: 경도
     */
    suspend fun checkInPlace(
        name: String,
        latitude: Double,
        longitude: Double
    ): Long

    /**
     * 반경 내 방문한 장소 불러오기
     *
     * latitude: 위도
     * longitude: 경도
     * radius: 반경 (m)
     */
    suspend fun fetchPlacesWithinRadius(
        latitude: Double,
        longitude: Double,
        radius: Int
    ): List<Place>

}
