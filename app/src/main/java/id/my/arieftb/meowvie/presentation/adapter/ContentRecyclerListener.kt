package id.my.arieftb.meowvie.presentation.adapter

import android.view.View
import id.my.arieftb.meowvie.constant.ContentType

interface ContentRecyclerListener {
    fun onContentClickListener(id: Long?, type: ContentType?, view: View, title: String? = null)
}