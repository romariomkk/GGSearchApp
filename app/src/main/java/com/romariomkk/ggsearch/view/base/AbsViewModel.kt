package com.romariomkk.ggsearch.view.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbsViewModel: ViewModel() {

    private val disposables = CompositeDisposable()

    fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun remove(disposable: Disposable) {
        disposables.remove(disposable)
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