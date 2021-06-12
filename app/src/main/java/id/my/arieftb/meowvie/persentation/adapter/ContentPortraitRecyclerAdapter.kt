package id.my.arieftb.meowvie.persentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.shape.CornerFamily
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.databinding.ItemContentDefaultBinding
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.persentation.base.BaseRecyclerDefaultAdapter

class ContentPortraitRecyclerAdapter(val context: Context) :
    BaseRecyclerDefaultAdapter<Content, ContentPortraitRecyclerAdapter.MoviesRecyclerViewHolder>() {

    var listener: ContentRecyclerListener? = null

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

            binding.imageContentDefaultPoster.apply {
                shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
                    .setAllCorners(
                        CornerFamily.ROUNDED,
                        this@ContentPortraitRecyclerAdapter.context.resources.getDimensionPixelSize(
                            R.dimen._8sdp
                        )
                            .toFloat()
                    )
                    .build()

                load(it.posterPath) {
                    crossfade(true)
                    placeholder(R.drawable.background_image_default)
                    error(R.drawable.image_not_found)
                }
            }

            binding.root.setOnClickListener { view ->
                listener?.onContentClickListener(it.id, it.type, view)
            }
        }
    }

    inner class MoviesRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}