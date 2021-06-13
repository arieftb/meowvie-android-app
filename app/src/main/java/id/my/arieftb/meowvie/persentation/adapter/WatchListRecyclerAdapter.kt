package id.my.arieftb.meowvie.persentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.shape.CornerFamily
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import id.my.arieftb.meowvie.databinding.ItemContentBannerBinding

class WatchListRecyclerAdapter(
    private val context: Context
) :
    PagedListAdapter<WatchListEntity, WatchListRecyclerAdapter.WatchListRecyclerViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WatchListEntity>() {
            override fun areItemsTheSame(
                oldItem: WatchListEntity,
                newItem: WatchListEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: WatchListEntity,
                newItem: WatchListEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    lateinit var binding: ItemContentBannerBinding
    var listener: ContentRecyclerListener? = null

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: WatchListRecyclerViewHolder, position: Int) {
        with(binding.root.rootView) {
            when(layoutParams) {
                is RecyclerView.LayoutParams -> {
                    (layoutParams as RecyclerView.LayoutParams).apply {
                        val startMargin = context.resources.getDimensionPixelSize(R.dimen._16sdp)
                        val endMargin = context.resources.getDimensionPixelSize(R.dimen._4sdp)
                        when (position) {
                            0 -> {
                                topMargin = startMargin
                                bottomMargin = endMargin
                                marginEnd = startMargin
                                marginStart = startMargin
                            }
                            else -> {
                                topMargin = endMargin
                                bottomMargin = endMargin
                                marginEnd = startMargin
                                marginStart = startMargin
                            }
                        }
                    }.also {
                        layoutParams = it
                    }
                }
                else -> {}
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

            binding.root.setOnClickListener { view ->
                listener?.onContentClickListener(it.code, ContentType.valueOf(it.type), view)
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