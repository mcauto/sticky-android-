/**
 * room dao test
 *
 * references
 * https://developer.android.com/training/dependency-injection/hilt-testing?hl=ko
 * https://medium.com/swlh/android-room-testing-made-easy-using-dagger-hilt-89d2d5d0e7f2
 */
package com.deo.compose.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject


open class BaseDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    internal lateinit var database: StickyDatabase


    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        database.close()
    }
}
