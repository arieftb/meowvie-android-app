package id.my.arieftb.meowvie.persentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.shape.CornerFamily
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.databinding.ItemContentBannerBinding
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.persentation.base.BaseRecyclerDefaultAdapter

class ContentBannerRecyclerAdapter(val context: Context) :
    BaseRecyclerDefaultAdapter<Content, ContentBannerRecyclerAdapter.MoviesRecyclerViewHolder>() {

    var listener: ContentRecyclerListener? = null

    lateinit var binding: ItemContentBannerBinding

    inner class MoviesRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int = getContent(position).id?.toInt() ?: -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesRecyclerViewHolder {
        binding =
            ItemContentBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val layoutParam = binding.root.layoutParams
        layoutParam.width = context.resources.getDimensionPixelSize(R.dimen._180sdp)
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
            binding.textContentBannerTitle.text = it.title

            binding.imageContentBanner.apply {
                shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
                    .setAllCorners(
                        CornerFamily.ROUNDED,
                        this@ContentBannerRecyclerAdapter.context.resources.getDimensionPixelSize(R.dimen._8sdp)
                            .toFloat()
                    )
                    .build()

                load(it.bannerPath) {
                    crossfade(true)
                    placeholder(R.drawable.background_image_default)
                    error(R.drawable.image_not_found)
                }
            }

            when(it.type) {
                ContentType.TV -> binding.imageContentType.setImageResource(R.drawable.ic_content_tv)
                else -> binding.imageContentType.setImageResource(R.drawable.ic_content_movie)
            }

            binding.root.setOnClickListener { view ->
                listener?.onContentClickListener(it.id, it.type, view, it.title)
            }
        }
    }
}