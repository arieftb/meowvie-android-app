package id.my.arieftb.meowvie.presentation.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.core.domain.constant.ContentType
import id.my.arieftb.core.domain.constant.SectionType
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.databinding.FragmentHomeBinding
import id.my.arieftb.meowvie.presentation.adapter.ContentBannerRecyclerAdapter
import id.my.arieftb.meowvie.presentation.adapter.ContentPortraitRecyclerAdapter
import id.my.arieftb.meowvie.presentation.adapter.ContentRecyclerListener
import id.my.arieftb.meowvie.presentation.base.BaseFragment
import id.my.arieftb.meowvie.presentation.model.Status
import id.my.arieftb.meowvie.utils.helper.test.IdlingResourceHelper
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), ContentRecyclerListener {


    @Inject
    lateinit var movieAdapter: ContentPortraitRecyclerAdapter

    @Inject
    lateinit var tvShowAdapter: ContentPortraitRecyclerAdapter

    @Inject
    lateinit var movieUpcomingAdapter: ContentBannerRecyclerAdapter

    @Inject
    lateinit var tvShowUpcomingAdapter: ContentBannerRecyclerAdapter

    @Inject
    lateinit var moviePopularAdapter: ContentPortraitRecyclerAdapter

    @Inject
    lateinit var tvShowPopularAdapter: ContentPortraitRecyclerAdapter

    val viewModel: HomeViewModelImpl by viewModels()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            IdlingResourceHelper.increment()
            getMoviesHighlight()
            getTvShowsHighlight()
            getMoviesUpcomingHighlight()
            getTvShowsUpcomingHighlight()
            getMoviesPopularHighlight()
            getTvShowsPopularHighlight()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getMoviesHighlight()
        getTvShowsHighlight()
        getMoviesUpcomingHighlight()
        getTvShowsUpcomingHighlight()
        getMoviesPopularHighlight()
        getTvShowsPopularHighlight()
    }

    override fun onDestroyView() {
        binding?.let { binding ->
            binding.sectionHomeComingSoonMovie.adapter = null
            binding.sectionHomeComingSoonTvShow.adapter = null
            binding.sectionHomeNewMovie.adapter = null
            binding.sectionHomeNewTvShow.adapter = null
            binding.sectionHomePopularMovie.adapter = null
            binding.sectionHomePopularTvShow.adapter = null
        }
        super.onDestroyView()
    }

    private fun initView() {
        initMovieHighlightAdapter()
        initTvShowHighlightAdapter()
        initMovieUpcomingHighlightAdapter()
        initTvShowUpcomingHighlightAdapter()
        initMoviesPopularHighlightAdapter()
        initTvShowPopularHighlightAdapter()
    }

    private fun initMovieHighlightAdapter() {
        binding?.let { binding ->
            binding.sectionHomeNewMovie.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = movieAdapter.apply {
                    listener = this@HomeFragment
                }
            }

            with(binding.sectionHomeNewMovie) {
                buttonMore?.setOnClickListener {
                    navigateToSection(it, SectionType.NEW_MOVIE, sectionTitle ?: "")
                }
            }
        }
    }

    private fun initTvShowHighlightAdapter() {
        binding?.let { binding ->
            binding.sectionHomeNewTvShow.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = tvShowAdapter.apply {
                    listener = this@HomeFragment
                }
            }

            with(binding.sectionHomeNewTvShow) {
                buttonMore?.setOnClickListener {
                    navigateToSection(it, SectionType.NEW_TV, sectionTitle ?: "")
                }
            }
        }
    }

    private fun initMovieUpcomingHighlightAdapter() {
        binding?.let { binding ->
            binding.sectionHomeComingSoonMovie.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = movieUpcomingAdapter.apply {
                    listener = this@HomeFragment
                }
            }

            with(binding.sectionHomeComingSoonMovie) {
                buttonMore?.setOnClickListener {
                    navigateToSection(it, SectionType.UPCOMING_MOVIE, sectionTitle ?: "")
                }
            }
        }
    }

    private fun initTvShowUpcomingHighlightAdapter() {
        binding?.let { binding ->
            binding.sectionHomeComingSoonTvShow.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = tvShowUpcomingAdapter.apply {
                    listener = this@HomeFragment
                }
            }

            with(binding.sectionHomeComingSoonTvShow) {
                buttonMore?.setOnClickListener {
                    navigateToSection(it, SectionType.UPCOMING_TV, sectionTitle ?: "")
                }
            }
        }
    }

    private fun initMoviesPopularHighlightAdapter() {
        binding?.let { binding ->
            binding.sectionHomePopularMovie.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = moviePopularAdapter.apply {
                    listener = this@HomeFragment
                }
            }

            with(binding.sectionHomePopularMovie) {
                buttonMore?.setOnClickListener {
                    navigateToSection(it, SectionType.POPULAR_MOVIE, sectionTitle ?: "")
                }
            }
        }
    }

    private fun initTvShowPopularHighlightAdapter() {
        binding?.let { binding ->
            binding.sectionHomePopularTvShow.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = tvShowPopularAdapter.apply {
                    listener = this@HomeFragment
                }
            }

            with(binding.sectionHomePopularTvShow) {
                buttonMore?.setOnClickListener {
                    navigateToSection(it, SectionType.POPULAR_TV, sectionTitle ?: "")
                }
            }
        }
    }

    private fun getMoviesHighlight() {
        viewModel.moviesData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.sectionHomeNewMovie?.status = it.status
                    movieAdapter.addAll(it.data)
                    IdlingResourceHelper.decrement()
                }
                Status.ERROR -> {
                    binding?.sectionHomeNewMovie?.status = it.status
                    binding?.sectionHomeNewMovie?.errorMessage =
                        getString(R.string.error_message_something_went_wrong)
                }
                else -> binding?.sectionHomeNewMovie?.status = it.status
            }
        }
    }

    private fun getTvShowsHighlight() {
        viewModel.tvShowsData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.sectionHomeNewTvShow?.status = it.status
                    tvShowAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding?.let { binding ->
                        binding.sectionHomeNewTvShow.status = it.status
                        binding.sectionHomeNewTvShow.errorMessage =
                            getString(R.string.error_message_something_went_wrong)
                    }
                }
                else -> binding?.sectionHomeNewMovie?.status = it.status
            }
        }


    }

    private fun getMoviesUpcomingHighlight() {
        viewModel.moviesUpcomingData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.sectionHomeComingSoonMovie?.status = it.status
                    movieUpcomingAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding?.let { binding ->
                        binding.sectionHomeComingSoonMovie.status = it.status
                        binding.sectionHomeComingSoonMovie.errorMessage =
                            getString(R.string.error_message_something_went_wrong)
                    }
                }
                else -> binding?.sectionHomeComingSoonMovie?.status = it.status
            }
        }


    }

    private fun getTvShowsUpcomingHighlight() {
        viewModel.tvShowsUpcomingData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.sectionHomeComingSoonTvShow?.status = it.status
                    tvShowUpcomingAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding?.let { binding ->
                        binding.sectionHomeComingSoonTvShow.status = it.status
                        binding.sectionHomeComingSoonTvShow.errorMessage =
                            getString(R.string.error_message_something_went_wrong)
                    }
                }
                else -> binding?.sectionHomeComingSoonTvShow?.status = it.status
            }
        }

    }

    private fun getMoviesPopularHighlight() {
        viewModel.moviesPopularData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.sectionHomePopularMovie?.status = it.status
                    moviePopularAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding?.let { binding ->
                        binding.sectionHomePopularMovie.status = it.status
                        binding.sectionHomePopularMovie.errorMessage =
                            getString(R.string.error_message_something_went_wrong)
                    }
                }
                else -> binding?.sectionHomePopularMovie?.status = it.status
            }
        }
    }

    private fun getTvShowsPopularHighlight() {
        viewModel.tvShowsPopularData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.sectionHomePopularTvShow?.status = it.status
                    tvShowPopularAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding?.let { binding ->
                        binding.sectionHomePopularTvShow.status = it.status
                        binding.sectionHomePopularTvShow.errorMessage =
                            getString(R.string.error_message_something_went_wrong)
                    }
                }
                else -> binding?.sectionHomePopularTvShow?.status = it.status
            }
        }
    }

    private fun navigateToSection(view: View, type: SectionType, title: String) {
        HomeFragmentDirections.actionHomeToSection(type, title).also {
            view.findNavController().navigate(it)
        }
    }

    override fun onContentClickListener(id: Long?, type: ContentType?, view: View, title: String?) {
        HomeFragmentDirections.actionHomeToDetail(id ?: -1, type ?: ContentType.MOVIE, title).also {
            view.findNavController().navigate(it)
        }
    }
}