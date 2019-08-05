package com.romariomkk.ggsearch.view.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.romariomkk.ggsearch.R
import com.romariomkk.ggsearch.config.GlideApp
import com.romariomkk.ggsearch.core.domain.pojo.SearchResult
import com.romariomkk.ggsearch.databinding.ActivityMainBinding
import com.romariomkk.ggsearch.util.Keys
import com.romariomkk.ggsearch.util.Resource
import com.romariomkk.ggsearch.util.annotation.RequiresView
import com.romariomkk.ggsearch.util.annotation.RequiresViewModel
import com.romariomkk.ggsearch.view.base.AbsActivity
import com.romariomkk.ggsearch.view.base.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*


@RequiresView(R.layout.activity_main)
@RequiresViewModel(MainViewModel::class)
class MainActivity : AbsActivity<ActivityMainBinding, MainViewModel>() {

    private val mItemClickListener = object: OnItemClickListener<SearchResult> {
        override fun onItemClicked(item: SearchResult) {
            Keys.openInBrowser(this@MainActivity, item.link)
        }
    }
    private val mResultsAdapter by lazy { SearchResultsAdapter(mItemClickListener) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        GlideApp.with(this)
            .load("https://www.google.com/images/branding/googleg/1x/googleg_standard_color_128dp.png")
            .into(ivGLogo)

        binding.vm = viewModel

        with(rvResults) {
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mResultsAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel?.let { vm ->
            vm.reObserve(vm.searchResultsSource, mSearchResultsObserver)
        }
    }

    private val mSearchResultsObserver = Observer<Resource<List<SearchResult>>> {
        when (it.status) {
            Resource.Status.SUCCESS -> {
                mResultsAdapter.updateItems(it.data!!)
            }
            Resource.Status.ERROR -> {
                if (it.exception?.message == Keys.EMPTY_PREVIOUS_REQUEST) {
                    Toast.makeText(this, "No previous request done", Toast.LENGTH_SHORT).show()
                } else {
                    it.exception?.printStackTrace()
                }
            }
        }
    }
}
