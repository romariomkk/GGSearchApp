package com.romariomkk.ggsearch.core.domain.storage

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.romariomkk.ggsearch.core.domain.storage.contract.SearchStorage
import io.reactivex.Completable
import io.reactivex.Single

@SuppressLint("ApplySharedPref")
class PrefStorageImpl(private val prefs: SharedPreferences) : SearchStorage {

    override fun saveQuery(query: String) {
        prefs.edit().putString(KEY_QUERY, query).commit()
    }

    override fun getLastQuery(): Single<String> {
        return Single.just(prefs.getString(KEY_QUERY, ""))
    }

    companion object {
        const val STORAGE = "ggSearchApp"
        const val KEY_QUERY = "query"
    }
}