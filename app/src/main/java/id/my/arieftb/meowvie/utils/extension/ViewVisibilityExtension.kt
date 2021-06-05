package id.my.arieftb.meowvie.utils.extension

import android.util.Log
import android.view.View
import androidx.core.view.isVisible

fun View?.show() {
    if (this != null) {
        if (!isVisible) {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).setDuration(500).setListener(null)
        }
    } else Log.w("MeowVie-TAG", "show: no view attached", )
}

fun View?.hide() {
    if (this != null) {
        if (isVisible) {
            alpha = 1f
            animate().alpha(0f).setDuration(500).setListener(null)
            this.visibility = View.GONE
        }
    } else {
        Log.w("MeowVie-TAG", "hide: no view attached", )
    }
}