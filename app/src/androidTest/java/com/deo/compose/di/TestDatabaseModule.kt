package com.deo.compose.di

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import co.anbora.labs.spatia.builder.SpatiaRoom
import com.deo.compose.model.StickyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
@Module
class TestDatabaseModule {
//    @Singleton
//    @Provides
//    fun provideDatabase(
//        @ApplicationContext
//        context: Context
//    ): StickyDatabase = Room.inMemoryDatabaseBuilder(
//        context, StickyDatabase::class.java,
//    ).addCallback(object : RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            db.execSQL(insertInitCategories)
//        }
//    }).fallbackToDestructiveMigration(
//    ).allowMainThreadQueries(
//    ).build()

    /**
     * 장소 데이터베이스
     */
    @Singleton
    @Provides
    fun provideSpatiaDatabase(
        @ApplicationContext
        context: Context
    ) = SpatiaRoom.databaseBuilder(
        context.applicationContext,
        StickyDatabase::class.java,
        "sticky-geo.db"
    ).addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db.execSQL(insertInitCategories)
        }
    }).fallbackToDestructiveMigration(
    ).build()
}