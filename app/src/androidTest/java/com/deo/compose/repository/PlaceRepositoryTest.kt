package com.deo.compose.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.deo.compose.model.StickyDatabase
import com.deo.compose.model.entity.Place
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class PlaceRepositoryTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var placeRepository: PlaceRepository

    @Inject
    internal lateinit var database: StickyDatabase

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun `체크인 테스트`() = runBlockingTest {
        val id = placeRepository.checkInPlace("또피빈", 1.0, 1.0)
        val places = placeRepository.fetchPlacesWithinRadius(
            0.9995,
            1.0,
            radius = 1000
        )
        for (place: Place in places) {
            assert(place.distance != null && place.distance!! <= 1000)
        }
    }
}
