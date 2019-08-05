package com.romariomkk.ggsearch.view.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbsViewModel: ViewModel() {

    private val disposables = CompositeDisposable()

    fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        dispose()
        super.onCleared()
    }

    private fun dispose() {
        disposables.dispose()
    }

    open fun onAttached() {}
}