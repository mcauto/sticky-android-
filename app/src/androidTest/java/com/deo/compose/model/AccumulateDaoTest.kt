package com.deo.compose.model

import androidx.test.filters.SmallTest
import com.deo.compose.model.dao.AccumulateDao
import com.deo.compose.model.entity.Accumulate
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.time.LocalDate.now
import java.util.*
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AccumulateDaoTest : BaseDaoTest() {
    @Inject
    internal lateinit var accumulateDao: AccumulateDao

    private fun fixture(): Accumulate {
        val _now = now().toEpochDay()
        val accumulate = Accumulate(
            null,
            start = Date(_now - 3600),
        )
        return accumulate
    }

    @Test
    fun `체크아웃하기`() = runBlockingTest {
        val rowId = accumulateDao.upsert(fixture())
        val accumulate = accumulateDao.get(rowId)
        assert(accumulate != null)
    }
}
