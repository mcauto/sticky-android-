package com.deo.compose.model.entity

import androidx.room.*
import java.util.*

/**
 * 장소
 *
 * name: 장소명
 * category: 카테고리
 * latitude: 위도
 * longitude: 경도
 */
@Entity(
    tableName = "places"
)
data class Place(
    @PrimaryKey(autoGenerate = true)
    val placeId: Long? = null,
    val name: String,
    val isFavorite: Boolean = false,
    val latitude: Double,
    val longitude: Double,
    val geometry: ByteArray?,
    // 현재 위치로부터 장소까지의 거리를 불러오기 위한 읽기용 필드
    val distance: Double? = 0.0,
)

/**
 * 카테고리
 *
 * name: 카테고리명
 * color: 색상
 * image: 대표이미지
 */
@Entity(
    tableName = "categories"
)
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long? = null,
    val name: String,
    val color: String = "#276EF1", // blue400
    val image: String? = null
)

data class PlaceWithCategory(
    @Embedded
    val place: Place,
    @Relation(
        parentColumn = "placeId",
        entityColumn = "categoryId",
        associateBy = Junction(PlaceCategoryCrossRef::class)
    )
    val categories: List<Category>,
)

@Entity(primaryKeys = ["placeId", "categoryId"])
data class PlaceCategoryCrossRef(
    val placeId: Long,
    val categoryId: Long
)

/**
 * 누적시간
 *
 * start: 시작시간(unix time)
 * end: 종료시간(unix time)
 *
 * memo data
 * thumbnail: 이미지 혹은 색상
 * title: 제목
 * contents: 내용
 */
@Entity(
    tableName = "accumulates"
)
data class Accumulate(
    @PrimaryKey(autoGenerate = true)
    val accumulateId: Long? = null,
    val start: Date,
    val end: Date? = null,
    // memo data
    val memoThumbnail: String? = null,
    val memoTitle: String? = null,
    val memoContents: String? = null,
)

data class AccumulateWithPlaceAndCategory(
    @Embedded
    val accumulate: Accumulate,
    @Relation(
        parentColumn = "accumulateId",
        entityColumn = "placeId"
    )
    val place: PlaceWithCategory
)

@Entity(
    primaryKeys = ["placeId", "accumulateId"]
)
data class PlaceAccumulateCrossRef(
    val placeId: Long,
    val accumulateId: Long
)


/**
 * 타입 변환
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
