package id.my.arieftb.meowvie.presentation.feature.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.databinding.FragmentDetailBinding
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.domain.model.entity.base.ContentDetail
import id.my.arieftb.meowvie.presentation.base.BaseFragment
import id.my.arieftb.meowvie.presentation.model.Status
import id.my.arieftb.meowvie.utils.extension.hide
import id.my.arieftb.meowvie.utils.extension.show
import id.my.arieftb.meowvie.domain.utils.date.DateHelper
import id.my.arieftb.meowvie.utils.helper.test.IdlingResourceHelper

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private var id: Long = -1
    private var type: ContentType = ContentType.MOVIE
    private var isSaved: Boolean = false
    private var contentDetail: ContentDetail? = null
    private val viewModel: DetailViewModelImpl by viewModels()

    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArgs()
        initView()
        getDetail()
        getContentAvailabilityStatus()
        getContentSaveStatus()
    }

    private fun initArgs() {
        arguments?.let {
            id = it.getLong("id")
            type = it.get("type") as ContentType
        }
    }

    private fun initView() {
        initTypeView()
        initButtonFavorite()
        initButtonShare()
    }

    private fun initTypeView() {
        when (type) {
            ContentType.TV -> binding.imageDetailType.apply {
                tag = R.drawable.ic_content_tv
                setImageResource(R.drawable.ic_content_tv)
            }
            else -> binding.imageDetailType.apply {
                tag = R.drawable.ic_content_movie
                setImageResource(R.drawable.ic_content_movie)
            }
        }
    }

    private fun initButtonFavorite() {
        binding.buttonDetailFavorite.setOnClickListener {
            IdlingResourceHelper.increment()
            if (!isSaved && contentDetail != null) {
                viewModel.saveWatchList(
                    Content(
                    id = contentDetail?.id!!,
                    title = contentDetail?.title!!
                ).apply {
                    this.posterPath = contentDetail?.posterPath
                    this.bannerPath = contentDetail?.bannerPath
                    this.type = this@DetailFragment.type
                })
            } else viewModel.removeContent(id, type)
        }
    }

    private fun initButtonShare() {
        binding.buttonDetailShare.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, binding.textDetailTitle.text)
                putExtra(
                    Intent.EXTRA_TEXT,
                    "${binding.textDetailTitle.text} \n\n ${binding.textDetailOverview.text}"
                )
            }.also {
                startActivity(Intent.createChooser(it, "Share using"))
            }
        }
    }

    private fun getDetail() {
        viewModel.detailData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> setSuccessDetailView(it.data)
                Status.ERROR -> setErrorView()
                else -> setLoadingView()

            }
        })

        IdlingResourceHelper.increment()
        viewModel.getDetail(id, type)
    }

    private fun getContentAvailabilityStatus() {
        viewModel.isAvailable.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.buttonDetailFavorite.isEnabled = true
                    setSuccessSaveView(it.data)
                }
                else -> binding.buttonDetailFavorite.isEnabled = false
            }
        })

        viewModel.checkWatchList(id, type)
    }

    private fun getContentSaveStatus() {
        viewModel.isSaved.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data == true) {
                        Toast.makeText(
                            context,
                            "Save to watch list successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Remove from watch list successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    setSuccessSaveView(it.data)
                }
                Status.ERROR -> Toast.makeText(
                    context,
                    getString(R.string.error_message_something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                }
            }
            IdlingResourceHelper.decrement()
        })
    }

    private fun setSuccessSaveView(data: Boolean?) {
        this.isSaved = data ?: false
        if (this.isSaved) {
            binding.buttonDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else binding.buttonDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
    }

    private fun setLoadingView() {
        binding.shimmerDetailLoading.show()
        binding.groupDetailView.hide()
        binding.textDetailErrorMessage.hide()
    }

    private fun setErrorView() {
        binding.shimmerDetailLoading.hide()
        binding.groupDetailView.hide()
        binding.textDetailErrorMessage.apply {
            show()
            text = getString(R.string.error_message_something_went_wrong)
        }
        IdlingResourceHelper.decrement()
    }

    private fun setSuccessDetailView(data: ContentDetail?) {
        this.contentDetail = data
        binding.shimmerDetailLoading.hide()
        binding.groupDetailView.show()

        data?.let {
            binding.imageDetailPoster.load(it.posterPath) {
                crossfade(true)
                placeholder(R.drawable.background_image_default)
                error(R.drawable.image_not_found)
            }

            binding.textDetailTitle.text = it.title
            binding.textDetailGenre.text = it.genre
            binding.textDetailReleaseDate.text = it.releaseDate
            binding.textDetailOverview.text = it.overview
            binding.textDetailReleaseDate.text = String.format(
                getString(
                    R.string.label_release_date,
                    DateHelper.instance?.fromDateString(
                        it.releaseDate ?: "0000-00-00",
                        "yyyy-MM-dd"
                    )
                        ?.toPattern("dd MMM yyyy")?.getString() ?: ""
                )
            )
            binding.ratingDetailVote.rating = it.rating?.toFloat() ?: 0.0f
        }
        IdlingResourceHelper.decrement()
    }
}
