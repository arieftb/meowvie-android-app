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

class ContentPortraitGridRecyclerAdapter(
    private val context: Context
) : BaseRecyclerDefaultAdapter<Content, ContentPortraitGridRecyclerAdapter.ContentPortraitGridRecyclerViewHolder>() {

    inner class ContentPortraitGridRecyclerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    lateinit var binding: ItemContentDefaultBinding

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
            when (layoutParams) {
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

//            binding.root.setOnClickListener { view ->
//                listener?.onContentClickListener(it.id, it.type, view)
//            }
        }
    }
}