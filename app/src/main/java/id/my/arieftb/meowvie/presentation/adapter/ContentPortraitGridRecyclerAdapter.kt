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

class ContentPortraitGridRecyclerAdapter(
    private val context: Context
) : BaseRecyclerDefaultAdapter<Content, ContentPortraitGridRecyclerAdapter.ContentPortraitGridRecyclerViewHolder>() {

    inner class ContentPortraitGridRecyclerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    private lateinit var binding: ItemContentDefaultBinding

    var listener: ContentRecyclerListener? = null

    override fun getItemViewType(position: Int): Int {
        return getContent(position).id?.toInt() ?: -1
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentPortraitGridRecyclerViewHolder {
        binding =
            ItemContentDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ContentPortraitGridRecyclerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ContentPortraitGridRecyclerViewHolder, position: Int) {
        with(binding.root.rootView) {
            when(layoutParams) {
                is RecyclerView.LayoutParams -> {
                    (layoutParams as RecyclerView.LayoutParams).apply {
                        val startMargin = context.resources.getDimensionPixelSize(R.dimen._16sdp)
                        val endMargin = context.resources.getDimensionPixelSize(R.dimen._8sdp)
                        when (position % 2) {
                            0 -> {
                                if (position == 0) {
                                    topMargin = startMargin
                                    bottomMargin = endMargin
                                    marginEnd = endMargin
                                    marginStart = startMargin
                                } else  {
                                    topMargin = endMargin
                                    bottomMargin = endMargin
                                    marginEnd = endMargin
                                    marginStart = startMargin
                                }
                            }
                            else -> {
                                if (position == 1) {
                                    topMargin = startMargin
                                    bottomMargin = endMargin
                                    marginEnd = startMargin
                                    marginStart = endMargin
                                } else {
                                    topMargin = endMargin
                                    bottomMargin = endMargin
                                    marginEnd = startMargin
                                    marginStart = endMargin
                                }
                            }
                        }
                    }.also {
                        layoutParams = it
                    }
                }
                else -> {
                    // NOT A RECYCLER ITEM
                }
            }
        }

        getContent(position).let {
            binding.textContentDefaultTitle.text = it.title

            binding.imageContentDefaultPoster.apply {
                shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
                    .setAllCorners(
                        CornerFamily.ROUNDED,
                        this@ContentPortraitGridRecyclerAdapter.context.resources.getDimensionPixelSize(
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
}