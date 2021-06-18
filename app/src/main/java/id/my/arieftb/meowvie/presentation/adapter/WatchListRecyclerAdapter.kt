package id.my.arieftb.meowvie.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.shape.CornerFamily
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.databinding.ItemContentBannerBinding
import id.my.arieftb.meowvie.domain.model.base.Content

class WatchListRecyclerAdapter(
    private val context: Context
) :
    PagingDataAdapter<Content, WatchListRecyclerAdapter.WatchListRecyclerViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(
                oldItem: Content,
                newItem: Content
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Content,
                newItem: Content
            ): Boolean {
                return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.type == newItem.type
            }
        }
    }

    lateinit var binding: ItemContentBannerBinding
    var listener: ContentRecyclerListener? = null

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.id?.toInt() ?: 0
    }

    override fun onBindViewHolder(holder: WatchListRecyclerViewHolder, position: Int) {
        with(binding.root.rootView) {
            when (layoutParams) {
                is RecyclerView.LayoutParams -> {
                    (layoutParams as RecyclerView.LayoutParams).apply {
                        val startMargin = context.resources.getDimensionPixelSize(R.dimen._16sdp)
                        val endMargin = context.resources.getDimensionPixelSize(R.dimen._4sdp)
                        when (position % 2) {
                            0 -> {
                                if (position == 0) {
                                    topMargin = startMargin
                                    bottomMargin = endMargin
                                    marginEnd = endMargin
                                    marginStart = startMargin
                                } else {
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

        getItem(position)?.let {
            binding.textContentBannerTitle.text = it.title

            binding.imageContentBanner.apply {
                shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
                    .setAllCorners(
                        CornerFamily.ROUNDED,
                        this@WatchListRecyclerAdapter.context.resources.getDimensionPixelSize(R.dimen._8sdp)
                            .toFloat()
                    )
                    .build()

                load(it.bannerPath) {
                    crossfade(true)
                    placeholder(R.drawable.background_image_default)
                    error(R.drawable.image_not_found)
                }
            }

            when (it.type) {
                ContentType.TV -> binding.imageContentType.setImageResource(R.drawable.ic_content_tv)
                else -> binding.imageContentType.setImageResource(R.drawable.ic_content_movie)
            }

            binding.root.setOnClickListener { view ->
                listener?.onContentClickListener(it.id, it.type, view, it.title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListRecyclerViewHolder {
        binding =
            ItemContentBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return WatchListRecyclerViewHolder(binding)
    }


    inner class WatchListRecyclerViewHolder(
        binding: ItemContentBannerBinding
    ) : RecyclerView.ViewHolder(binding.root)
}