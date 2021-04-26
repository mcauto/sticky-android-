package com.deo.compose.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.deo.compose.model.BaseDao
import com.deo.compose.model.entity.Accumulate

@Dao
interface AccumulateDao : BaseDao<Accumulate> {
    @Query("SELECT * FROM accumulates")
    suspend fun findAll(): List<Accumulate>

    @Query("SELECT * FROM accumulates WHERE accumulateId = :id")
    suspend fun get(id: Long): Accumulate?
}
