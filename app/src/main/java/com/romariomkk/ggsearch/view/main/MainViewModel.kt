package com.romariomkk.ggsearch.view.main

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.romariomkk.ggsearch.core.domain.pojo.SearchResult
import com.romariomkk.ggsearch.core.domain.repo.contract.SearchRepo
import com.romariomkk.ggsearch.util.Resource
import com.romariomkk.ggsearch.view.base.AbsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val searchRepo: SearchRepo
) : AbsViewModel() {

    val searchResultsSource = MutableLiveData<Resource<List<SearchResult>>>()
    val queryObservable = ObservableField<String>()
    private var searchDisposable: Disposable? = null

    override fun onAttached() {
        add(
            searchRepo.requestLastSearchData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ request ->
                    queryObservable.set(request.searchQuery)
                    searchResultsSource.value = Resource.success(request.searchResults)
                }, {
                    it.printStackTrace()
                    searchResultsSource.value = Resource.error(it)
                })
        )
    }

    fun searchText() {
        searchDisposable = searchRepo.searchText(queryObservable.get()!!)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { searchResultsSource.value = Resource.loading() }
            .doOnDispose { searchResultsSource.value = Resource.abort() }
            .subscribe({ results ->
                searchResultsSource.value = Resource.success(results)
            }, {
                it.printStackTrace()
                searchResultsSource.value = Resource.error(it)
            })

        add(searchDisposable!!)
    }

    fun stopSearch() {
        searchDisposable?.let { remove(it) }
    }


}