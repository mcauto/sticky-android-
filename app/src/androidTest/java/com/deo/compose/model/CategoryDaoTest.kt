package com.deo.compose.model

import androidx.test.filters.SmallTest
import com.deo.compose.model.dao.CategoryDao
import com.deo.compose.model.entity.Category
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class CategoryDaoTest : BaseDaoTest() {
    @Inject
    internal lateinit var categoryDao: CategoryDao

    @Test
    fun `카테고리 불러오기`() = runBlockingTest {
        val categories = categoryDao.findAll()
        assert(categories.size == 6)
    }

    @Test
    fun `카테고리 추가하기`() = runBlockingTest {
        val new = Category(name = "새로운 카테고리")
        val rowId = categoryDao.upsert(new)
        val category = categoryDao.get(rowId)
        assert(category != null)
    }
}
