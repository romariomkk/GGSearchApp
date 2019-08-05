package com.romariomkk.ggsearch.core.domain.repo

import android.util.Log
import com.romariomkk.ggsearch.BuildConfig
import com.romariomkk.ggsearch.core.domain.api.GGSearchApi
import com.romariomkk.ggsearch.core.domain.db.SearchDao
import com.romariomkk.ggsearch.core.domain.pojo.SearchRequest
import com.romariomkk.ggsearch.core.domain.pojo.SearchResult
import com.romariomkk.ggsearch.core.domain.repo.contract.SearchRepo
import com.romariomkk.ggsearch.core.domain.storage.contract.SearchStorage
import com.romariomkk.ggsearch.util.Keys
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepoImpl @Inject constructor(
    private val api: GGSearchApi,
    private val searchDao: SearchDao,
    private val searchStorage: SearchStorage
): SearchRepo {

    override fun requestLastSearchData(): Single<SearchRequest> {
        return Single.zip(
            searchStorage.getLastQuery(),
            searchDao.getLastSearchResults(),
            BiFunction<String, List<SearchResult>, SearchRequest> {
                    query, results ->
                SearchRequest(query, results)
            })
            .filter { it.searchQuery.isNotEmpty() }
            .switchIfEmpty(Single.error(Throwable(Keys.EMPTY_PREVIOUS_REQUEST)))
            .subscribeOn(Schedulers.io())
    }

    override fun searchText(query: String): Single<List<SearchResult>> {
        return Single.zip(
            provide15Results(query, threadFactory = mThreadFactory1),
            provide15Results(query, offset = 15, threadFactory = mThreadFactory2),
            BiFunction<List<SearchResult>, List<SearchResult>, List<SearchResult>> { t1, t2 -> t1 + t2 })
            .flatMap {
                searchDao.saveLastSearchResults(it)
                searchStorage.saveQuery(query)
                Single.just(it)
            }
            .subscribeOn(Schedulers.io())

    }

    private fun provide15Results(query: String, limit: Int = MAX_RESULTS_GOOGLE_RESTRICTED, offset: Int = 1,
                                 threadFactory: ThreadFactory)
            : Single<List<SearchResult>> {
        return Single.zip(
            getSearchResults(query, limit, offset),
            getSearchResults(query, 5, limit + offset),
            BiFunction<List<SearchResult>, List<SearchResult>, List<SearchResult>> { t1, t2 -> t1 + t2 })
            .subscribeOn(Schedulers.from(Executors.newSingleThreadExecutor(threadFactory)))
            .doOnSuccess {
                Log.d(TAG, Thread.currentThread().name)
            }
    }

    private fun getSearchResults(query: String, limit: Int, offset: Int): Single<List<SearchResult>> {
        return api.searchWithQuery(
            BuildConfig.GOOGLE_API_KEY,
            BuildConfig.GOOGLE_SEARCH_ENGINE_KEY,
            query, limit, offset)
            .map { it.items }
    }

    private val mThreadFactory1: ThreadFactory = ThreadFactory {
        Thread(it).apply { name = "LeizerGGSearch1" }
    }

    private val mThreadFactory2: ThreadFactory = ThreadFactory {
        Thread(it).apply { name = "LeizerGGSearch96" }
    }


    companion object {
        const val MAX_RESULTS_GOOGLE_RESTRICTED = 10
        val TAG: String = SearchRepoImpl::class.java.simpleName
    }
}