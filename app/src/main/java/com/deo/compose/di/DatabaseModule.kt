package com.deo.compose.di

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import co.anbora.labs.spatia.builder.SpatiaRoom
import com.deo.compose.model.StickyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val insertInitCategories = """
    INSERT OR REPLACE INTO categories (`categoryId`, `name`, `color`,`image`)
     VALUES (1, '맛집','#276EF1', ''),
            (2, '카페','#05944F', ''),
            (3, '쇼핑','#E11900', ''),
            (4, '술','#A0BFF8', ''),
            (5, '집','#F1998E', ''),
            (6, '회사','#66D19E', '');
 
""".trimIndent()

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext
        context: Context
    ) = SpatiaRoom.databaseBuilder(
        context,
        StickyDatabase::class.java,
        "sticky.db"
    ).addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db.execSQL(insertInitCategories)
        }
    }).fallbackToDestructiveMigration(
    ).build()
}
