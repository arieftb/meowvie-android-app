package id.my.arieftb.meowvie.persentation.feature.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.databinding.ItemContentDefaultBinding
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.persentation.base.BaseRecyclerDefaultAdapter

class MoviesPortraitRecyclerAdapter(val context: Context) :
    BaseRecyclerDefaultAdapter<Movie, MoviesPortraitRecyclerAdapter.MoviesRecyclerViewHolder>() {

    lateinit var binding: ItemContentDefaultBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesRecyclerViewHolder {
        binding =
            ItemContentDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MoviesRecyclerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MoviesRecyclerViewHolder, position: Int) {
        with(binding.root.rootView) {
            (layoutParams as RecyclerView.LayoutParams).apply {
                val startMargin = context.resources.getDimensionPixelSize(R.dimen._16sdp)
                val endMargin = context.resources.getDimensionPixelSize(R.dimen._4sdp)
                when (position) {
                    0 -> {
                        marginStart = startMargin
                        marginEnd = endMargin
                    }
                    getContents().lastIndex -> {
                        marginEnd = startMargin
                        marginStart = endMargin
                    }
                    else -> {
                        marginStart = endMargin
                        marginEnd = endMargin
                    }
                }
            }.also {
                layoutParams = it
            }
        }

        getContent(position).let {
            binding.textContentDefaultTitle.text = it.title
        }
    }

    inner class MoviesRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}