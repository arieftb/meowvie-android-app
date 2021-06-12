package id.my.arieftb.meowvie.persentation.adapter

import android.view.View

interface MovieRecyclerListener {
    fun onMovieClickListener(id: Long, view: View)
}