package com.romariomkk.ggsearch.core.domain.pojo

data class SearchRequest(
    val searchQuery: String = "",
    val searchResults: List<SearchResult> = ArrayList()
)