package id.my.arieftb.meowvie.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.shape.CornerFamily
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.databinding.ItemContentDefaultBinding
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.presentation.base.BaseRecyclerDefaultAdapter

class ContentPortraitRecyclerAdapter(val context: Context) :
    BaseRecyclerDefaultAdapter<Content, ContentPortraitRecyclerAdapter.MoviesRecyclerViewHolder>() {

    var listener: ContentRecyclerListener? = null

    lateinit var binding: ItemContentDefaultBinding

    override fun getItemViewType(position: Int): Int = getContent(position).id?.toInt() ?: -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesRecyclerViewHolder {
        binding =
            ItemContentDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val layoutParam = binding.root.layoutParams
        layoutParam.width = context.resources.getDimensionPixelSize(R.dimen._100sdp)
        binding.root.layoutParams = layoutParam

        return MoviesRecyclerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MoviesRecyclerViewHolder, position: Int) {
        with(binding.root.rootView) {
            when(layoutParams) {
                is RecyclerView.LayoutParams -> {
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
                else -> {
                    // * NOT A RECYCLER ITEM
                }
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

            when(it.type) {
                ContentType.TV -> binding.imageContentDefaultType.setImageResource(R.drawable.ic_content_tv)
                else -> binding.imageContentDefaultType.setImageResource(R.drawable.ic_content_movie)
            }

            binding.root.setOnClickListener { view ->
                listener?.onContentClickListener(it.id, it.type, view, it.title)
            }
        }
    }

    inner class MoviesRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}