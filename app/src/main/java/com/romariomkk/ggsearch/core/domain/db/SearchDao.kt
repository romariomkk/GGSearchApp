package com.romariomkk.ggsearch.core.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.romariomkk.ggsearch.core.domain.pojo.SearchResult
import io.reactivex.Single

@Dao
abstract class SearchDao {

    @Transaction
    open fun saveLastSearchResults(searchResults: List<SearchResult>) {
        deleteAll()
        saveAll(searchResults)
    }

    @Query("DELETE FROM $TABLE_NAME")
    protected abstract fun deleteAll()

    @Insert
    protected abstract fun saveAll(searchResults: List<SearchResult>)

    @Query("SELECT * FROM $TABLE_NAME")
    abstract fun getLastSearchResults(): Single<List<SearchResult>>

    companion object {
        const val TABLE_NAME = "contact"
    }
}