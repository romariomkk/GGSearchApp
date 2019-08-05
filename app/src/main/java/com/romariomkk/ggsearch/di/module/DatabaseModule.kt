package com.romariomkk.ggsearch.di.module

import androidx.room.Room
import com.romariomkk.ggsearch.GGSearchApp
import com.romariomkk.ggsearch.core.domain.db.GGSearchDB
import com.romariomkk.ggsearch.core.domain.db.SearchDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: GGSearchApp) =
        Room.databaseBuilder(app, GGSearchDB::class.java, GGSearchDB.DB_FILE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideContactDao(db: GGSearchDB): SearchDao = db.searchDao()
}