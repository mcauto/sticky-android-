package com.deo.compose.model.dao

import androidx.room.*
import com.deo.compose.model.BaseDao
import com.deo.compose.model.entity.Place
import com.deo.compose.model.entity.PlaceAccumulateCrossRef
import com.deo.compose.model.entity.PlaceCategoryCrossRef
import com.deo.compose.model.entity.PlaceWithCategory

/**
 * spatilite query 요청용
 * http://www.gaia-gis.it/gaia-sins/spatialite-sql-4.3.0.html#p4
 */
@Dao
interface SpatialDao {
    @Query("SELECT spatialite_version()")
    @SkipQueryVerification
    fun getSpatiaVersion(): String

    @Query("SELECT LongLatToDMS(:longitude, :latitude)")
    @SkipQueryVerification
    fun longlatToDms(longitude: Double, latitude: Double): String

    @Query("SELECT MakePoint(:longitude, :latitude, 4326)")
    @SkipQueryVerification
    fun makePoint(longitude: Double, latitude: Double): ByteArray

    @Query("SELECT PointFromText(:point, 4326)")
    @SkipQueryVerification
    fun pointFromText(point: String): ByteArray
}

@Dao
interface PlaceDao : BaseDao<Place> {

    @Query("SELECT * FROM places WHERE placeId=:id;")
    suspend fun get(id: Long): Place?

    @Transaction
    @Query("SELECT * FROM places WHERE placeId=:id;")
    suspend fun getWithCategory(id: Long): PlaceWithCategory
}

@Dao
interface PlaceCategoryCrossRefDao : BaseDao<PlaceCategoryCrossRef>

@Dao
interface PlaceAccumulateCrossRefDao : BaseDao<PlaceAccumulateCrossRef>
