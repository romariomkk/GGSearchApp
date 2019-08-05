package com.romariomkk.ggsearch.core.domain.api

import com.romariomkk.ggsearch.core.domain.pojo.SearchItems
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GGSearchApi {

    @GET("customsearch/v1?alt=json")
    fun searchWithQuery(
        @Query("key") googleApiKey: String,
        @Query("cx", encoded = true) googleSearchEngineID: String,
        @Query("q", encoded = false) query: String,
        @Query("num") limit: Int,
        @Query("start") offset: Int,
        @Query("fields", encoded = true) fields: String = "items(link%2Ctitle)"
    ) : Single<SearchItems>
}