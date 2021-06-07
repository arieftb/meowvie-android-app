package id.my.arieftb.meowvie.persentation.feature.tv_show

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.shape.CornerFamily
import id.my.arieftb.meowvie.BuildConfig
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.databinding.ItemContentDefaultBinding
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow
import id.my.arieftb.meowvie.persentation.base.BaseRecyclerDefaultAdapter

class TvShowsPortraitRecyclerAdapter(val context: Context) :
    BaseRecyclerDefaultAdapter<TvShow, TvShowsPortraitRecyclerAdapter.TvShowsRecyclerViewHolder>() {

    lateinit var binding: ItemContentDefaultBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsRecyclerViewHolder {
        binding =
            ItemContentDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TvShowsRecyclerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TvShowsRecyclerViewHolder, position: Int) {
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
                        this@TvShowsPortraitRecyclerAdapter.context.resources.getDimensionPixelSize(
                            R.dimen._8sdp
                        )
                            .toFloat()
                    )
                    .build()

                load(BuildConfig.BASE_URL_IMAGE_PORTRAIT + it.posterPath) {
                    crossfade(true)
                    placeholder(R.drawable.background_image_default)
                    error(R.drawable.background_image_default)
                }
            }
        }
    }

    inner class TvShowsRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
