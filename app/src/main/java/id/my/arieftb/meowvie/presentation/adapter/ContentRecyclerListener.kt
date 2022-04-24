package id.my.arieftb.meowvie.presentation.adapter

import android.view.View
import id.my.arieftb.core.domain.constant.ContentType

interface ContentRecyclerListener {
    fun onContentClickListener(id: Long?, type: ContentType?, view: View, title: String? = null)
}