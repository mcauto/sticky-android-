package com.deo.compose.model.dao

import androidx.room.*
import com.deo.compose.model.BaseDao
import com.deo.compose.model.entity.*

/**
 * spatilite query 요청용
 * http://www.gaia-gis.it/gaia-sins/spatialite-sql-4.3.0.html#p4
 */
@Dao
interface SpatialDao {
    @Query("SELECT spatialite_version()")
    @SkipQueryVerification
    fun getSpatiaVersion(): String

    @Query("SELECT LongLatToDMS(:latitude, :longitude)")
    @SkipQueryVerification
    fun longlatToDms(latitude: Double, longitude: Double): String

    @Query("SELECT MakePoint(:latitude, :longitude, 4326)")
    @SkipQueryVerification
    fun makePoint(latitude: Double, longitude: Double): ByteArray

    @Query("SELECT PointFromText(:point, 4326)")
    @SkipQueryVerification
    fun pointFromText(point: String): ByteArray
}

@Dao
interface PlaceDao : BaseDao<Place> {
    @Transaction
    @Query(
        """
        INSERT INTO places (name, isFavorite, latitude, longitude, geometry) 
             VALUES (:name, :isFavorite, :latitude, :longitude, MakePoint(:latitude, :longitude, 4326))
        """
    )
    @SkipQueryVerification
    suspend fun upsert(
        name: String,
        latitude: Double,
        longitude: Double,
        isFavorite: Boolean = false,
    ): Long

    @Query("SELECT * FROM places WHERE placeId=:id;")
    suspend fun get(id: Long): Place?

    @Query(
        """
       SELECT placeId,
              name,
              isFavorite,
              latitude,
              longitude,
              GeodesicLength(ShortestLine(geometry, MakePoint(:latitude, :longitude, 4326))) as distance
        FROM places
       WHERE PtDistWithin(geometry, MakePoint(:latitude, :longitude, 4326), :radius)
    """
    )
    @SkipQueryVerification
    suspend fun getPlacesWithinRadius(
        latitude: Double,
        longitude: Double,
        radius: Int
    ): List<Place>
}

@Dao
interface PlaceCategoryCrossRefDao : BaseDao<PlaceCategoryCrossRef>

@Dao
interface PlaceAccumulateCrossRefDao : BaseDao<PlaceAccumulateCrossRef>
