package com.romariomkk.ggsearch.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.romariomkk.ggsearch.util.annotation.AnnotationUtil
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class AbsActivity<DB : ViewDataBinding, VM : AbsViewModel> : AppCompatActivity() {

    @set:Inject
    var vmFactory: ViewModelProvider.Factory? = null

    protected lateinit var binding: DB
    var viewModel: VM? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, AnnotationUtil.findViewId(this))
        setViewModel()
    }

    private fun setViewModel() {
        if (AnnotationUtil.hasViewModel(this)) {
            val viewModelClass = AnnotationUtil.findViewModelClass(this)
            viewModel = (ViewModelProviders.of(this, vmFactory).get(viewModelClass) as VM)
                .apply {
                    onAttached()
                }
        }
    }

    fun <T> AbsViewModel.reObserve(liveData: LiveData<T>, observer: Observer<T>) {
        liveData.run {
            removeObserver(observer)
            observe(this@AbsActivity, observer)
        }
    }
}