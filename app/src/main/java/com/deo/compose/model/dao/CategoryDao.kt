package com.deo.compose.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.deo.compose.model.BaseDao
import com.deo.compose.model.entity.Category


@Dao
interface CategoryDao : BaseDao<Category> {
    @Query("SELECT * FROM categories")
    suspend fun findAll(): List<Category>

    @Query("SELECT * FROM categories WHERE categoryId = :id")
    suspend fun get(id: Long): Category?
}