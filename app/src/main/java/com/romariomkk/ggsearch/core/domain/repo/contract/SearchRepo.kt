package com.romariomkk.ggsearch.core.domain.repo.contract

import com.romariomkk.ggsearch.core.domain.pojo.SearchRequest
import com.romariomkk.ggsearch.core.domain.pojo.SearchResult
import io.reactivex.Single

interface SearchRepo {
    fun searchText(query: String): Single<List<SearchResult>>
    fun requestLastSearchData(): Single<SearchRequest>
}