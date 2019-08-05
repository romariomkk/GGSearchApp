package com.romariomkk.ggsearch.util

import android.content.Context
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri


class Keys {

    companion object {

        const val EMPTY_PREVIOUS_REQUEST = "EMPTY_PREVIOUS_REQUEST"

        @JvmStatic
        fun openInBrowser(context: Context, url: String) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }
    }
}