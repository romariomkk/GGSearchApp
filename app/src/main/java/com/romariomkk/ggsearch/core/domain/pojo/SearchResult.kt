package com.romariomkk.ggsearch.core.domain.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.romariomkk.ggsearch.core.domain.db.SearchDao

@Entity(tableName = SearchDao.TABLE_NAME)
data class SearchResult(
    val title: String,
    val link: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}