package com.romariomkk.ggsearch.view.base

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter


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

}