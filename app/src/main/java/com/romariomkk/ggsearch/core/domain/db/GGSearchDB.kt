package com.romariomkk.ggsearch.core.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.romariomkk.ggsearch.core.domain.pojo.SearchResult

@Database(entities = [SearchResult::class], version = 1, exportSchema = false)
abstract class GGSearchDB: RoomDatabase() {

    abstract fun searchDao(): SearchDao

    companion object {
        const val DB_FILE_NAME = "ggsearchapp.db"
    }
}