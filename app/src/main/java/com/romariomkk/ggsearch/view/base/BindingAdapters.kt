package com.romariomkk.ggsearch.view.base

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.romariomkk.ggsearch.util.Resource


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("onDoneAction")
    fun bindOnDoneAction(editText: AppCompatEditText, listener: View.OnClickListener) {
        editText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                listener.onClick(v)

                with(editText.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager) {
                    hideSoftInputFromWindow(editText.windowToken, 0)
                }
                true
            } else false
        }
    }

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun bindButtonVisibility(button: Button, searchLiveData: LiveData<Resource<*>>) {
        button.visibility = when (searchLiveData.value?.status) {
            Resource.Status.LOADING -> View.VISIBLE
            Resource.Status.SUCCESS, Resource.Status.ERROR, Resource.Status.ABORT -> View.GONE
            else -> View.GONE
        }
    }

}