package com.deo.compose.model


import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction

interface BaseDao<T> {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upserts(vararg entities: T): List<Long>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: T): Long

    @Transaction
    @Delete
    suspend fun delete(entity: T)

    @Transaction
    @Delete
    suspend fun deletes(entities: List<T>)
}
