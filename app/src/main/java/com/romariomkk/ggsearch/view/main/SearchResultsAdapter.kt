package com.romariomkk.ggsearch.view.main

import android.view.View
import androidx.databinding.ViewDataBinding
import com.romariomkk.ggsearch.R
import com.romariomkk.ggsearch.core.domain.pojo.SearchResult
import com.romariomkk.ggsearch.databinding.ItemResultBinding
import com.romariomkk.ggsearch.view.base.AbsRVAdapter
import com.romariomkk.ggsearch.view.base.AbsRVViewHolder
import com.romariomkk.ggsearch.view.base.OnItemClickListener

class SearchResultsAdapter(itemClickListener: OnItemClickListener<SearchResult>)
    : AbsRVAdapter<SearchResult, ItemResultBinding, SearchResultsAdapter.SearchResultVH>(itemClickListener) {

    override fun provideLayoutId(viewType: Int) = R.layout.item_result

    override fun getViewHolder(view: View, viewType: Int) = SearchResultVH(view)

    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult) = oldItem.link == newItem.link

    override fun areContentTheSame(oldItem: SearchResult, newItem: SearchResult) = oldItem == newItem

    class SearchResultVH(view: View) : AbsRVViewHolder<SearchResult, ItemResultBinding>(view) {
        override fun bind(item: SearchResult?, payloads: MutableList<Any>?) {
            super.bind(item, payloads)
            binding?.result = item
            binding?.executePendingBindings()
        }
    }
}