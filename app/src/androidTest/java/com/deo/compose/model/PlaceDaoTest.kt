package com.deo.compose.model

import androidx.test.filters.SmallTest
import com.deo.compose.model.dao.PlaceAccumulateCrossRefDao
import com.deo.compose.model.dao.PlaceCategoryCrossRefDao
import com.deo.compose.model.dao.PlaceDao
import com.deo.compose.model.dao.SpatialDao
import com.deo.compose.model.entity.Place
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class PlaceDaoTest : BaseDaoTest() {
    @Inject
    internal lateinit var placeDao: PlaceDao

    @Inject
    internal lateinit var rawQueryDao: SpatialDao

    @Inject
    internal lateinit var placeCategoryCrossRefDao: PlaceCategoryCrossRefDao

    @Inject
    internal lateinit var placeAccumulateCrossRefDao: PlaceAccumulateCrossRefDao

    @Test
    fun `체크인하는 장소 저장`() = runBlockingTest {
        val result = rawQueryDao.makePoint(1.0, 0.0)
        val id = placeDao.upsert(
            Place(
                name = "강남 11출 또피빈",
                geometry = result
            )
        )
        val place = placeDao.get(id)
        assert(place != null)
    }

    @Test
    fun `장소 불러오기`() = runBlockingTest {
        database.runInTransaction(Runnable {
            val result = rawQueryDao.makePoint(1.0, 0.0)
            launch {
                val id = placeDao.upsert(
                    Place(
                        name = "강남 11출 또피빈",
                        geometry = result
                    )
                )
                val place = placeDao.getWithCategory(id)
                print(place)
            }
        })
    }

    @Test
    fun `반경 내 장소 불러오기`() = runBlockingTest {
        val result = rawQueryDao.makePoint(1.0, 0.0)
        placeDao.upsert(
            Place(
                name = "강남 11출 또피빈",
                geometry = result
            )
        )
    }

    @Test
    fun `위치 데이터베이스 테스트`() = runBlockingTest {

        val result = rawQueryDao.makePoint(1.0, 0.0)

        print(result)
        val result2 = rawQueryDao.pointFromText("POINT(1.0 0.0)")
        print(result2)
    }
}
