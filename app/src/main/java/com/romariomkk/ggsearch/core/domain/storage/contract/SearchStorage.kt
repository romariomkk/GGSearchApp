package com.romariomkk.ggsearch.core.domain.storage.contract

import io.reactivex.Single

interface SearchStorage {

    fun saveQuery(query: String)

    fun getLastQuery(): Single<String>

}