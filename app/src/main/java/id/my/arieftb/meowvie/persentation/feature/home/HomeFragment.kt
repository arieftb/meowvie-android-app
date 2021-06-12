package id.my.arieftb.meowvie.persentation.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.databinding.FragmentHomeBinding
import id.my.arieftb.meowvie.persentation.adapter.*
import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.persentation.model.Status

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), ContentRecyclerListener {

    private lateinit var movieAdapter: ContentPortraitRecyclerAdapter
    private lateinit var tvShowAdapter: ContentPortraitRecyclerAdapter
    private lateinit var movieUpcomingAdapter: ContentBannerRecyclerAdapter
    private lateinit var tvShowUpcomingAdapter: ContentBannerRecyclerAdapter
    private lateinit var moviePopularAdapter: ContentPortraitRecyclerAdapter
    private lateinit var tvShowPopularAdapter: ContentPortraitRecyclerAdapter

    private val viewModel: HomeViewModelImpl by viewModels()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

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

    private fun initView() {
        initMovieHighlightAdapter()
        initTvShowHighlightAdapter()
        initMovieUpcomingHighlightAdapter()
        initTvShowUpcomingHighlightAdapter()
        initMoviesPopularHighlightAdapter()
        initTvShowPopularHighlightAdapter()
    }

    private fun initMovieHighlightAdapter() {
        binding.sectionHomeNewMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ContentPortraitRecyclerAdapter(requireContext()).apply {
                listener = this@HomeFragment
            }.also {
                movieAdapter = it
            }
        }
    }

    private fun initTvShowHighlightAdapter() {
        binding.sectionHomeNewTvShow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ContentPortraitRecyclerAdapter(requireContext()).apply {
                listener = this@HomeFragment
            }.also {
                tvShowAdapter = it
            }
        }
    }

    private fun initMovieUpcomingHighlightAdapter() {
        binding.sectionHomeComingSoonMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ContentBannerRecyclerAdapter(requireContext()).apply {
                listener = this@HomeFragment
            }.also {
                movieUpcomingAdapter = it
            }
        }
    }

    private fun initTvShowUpcomingHighlightAdapter() {
        binding.sectionHomeComingSoonTvShow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ContentBannerRecyclerAdapter(requireContext()).apply {
                listener = this@HomeFragment
            }.also {
                tvShowUpcomingAdapter = it
            }
        }
    }

    private fun initMoviesPopularHighlightAdapter() {
        binding.sectionHomePopularMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ContentPortraitRecyclerAdapter(requireContext()).apply {
                listener = this@HomeFragment
            }.also {
                moviePopularAdapter = it
            }
        }
    }

    private fun initTvShowPopularHighlightAdapter() {
        binding.sectionHomePopularTvShow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ContentPortraitRecyclerAdapter(requireContext()).apply {
                listener = this@HomeFragment
            }.also {
                tvShowPopularAdapter = it
            }
        }
    }

    private fun getMoviesHighlight() {
        viewModel.moviesData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.sectionHomeNewMovie.status = it.status
                    movieAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding.sectionHomeNewMovie.status = it.status
                    binding.sectionHomeNewMovie.errorMessage =
                        getString(R.string.error_message_something_went_wrong)
                }
                else -> binding.sectionHomeNewMovie.status = it.status
            }
        })

        viewModel.getMovies()
    }

    private fun getTvShowsHighlight() {
        viewModel.tvShowsData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.sectionHomeNewTvShow.status = it.status
                    tvShowAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding.sectionHomeNewTvShow.status = it.status
                    binding.sectionHomeNewTvShow.errorMessage =
                        getString(R.string.error_message_something_went_wrong)
                }
                else -> binding.sectionHomeNewMovie.status = it.status
            }
        })

        viewModel.getTvShowsHighlight()
    }

    private fun getMoviesUpcomingHighlight() {
        viewModel.moviesUpcomingData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.sectionHomeComingSoonMovie.status = it.status
                    movieUpcomingAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding.sectionHomeComingSoonMovie.status = it.status
                    binding.sectionHomeComingSoonMovie.errorMessage =
                        getString(R.string.error_message_something_went_wrong)
                }
                else -> binding.sectionHomeComingSoonMovie.status = it.status
            }
        })

        viewModel.getMoviesUpcomingHighlight()
    }

    private fun getTvShowsUpcomingHighlight() {
        viewModel.tvShowsUpcomingData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.sectionHomeComingSoonTvShow.status = it.status
                    tvShowUpcomingAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding.sectionHomeComingSoonTvShow.status = it.status
                    binding.sectionHomeComingSoonTvShow.errorMessage =
                        getString(R.string.error_message_something_went_wrong)
                }
                else -> binding.sectionHomeComingSoonTvShow.status = it.status
            }
        })

        viewModel.getTvShowsUpcomingHighlight()
    }

    private fun getMoviesPopularHighlight() {
        viewModel.moviesPopularData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.sectionHomePopularMovie.status = it.status
                    moviePopularAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding.sectionHomePopularMovie.status = it.status
                    binding.sectionHomePopularMovie.errorMessage =
                        getString(R.string.error_message_something_went_wrong)
                }
                else -> binding.sectionHomePopularMovie.status = it.status
            }
        })

        viewModel.getMoviesPopularHighlight()
    }

    private fun getTvShowsPopularHighlight() {
        viewModel.tvShowsPopularData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.sectionHomePopularTvShow.status = it.status
                    tvShowPopularAdapter.addAll(it.data)
                }
                Status.ERROR -> {
                    binding.sectionHomePopularTvShow.status = it.status
                    binding.sectionHomePopularTvShow.errorMessage =
                        getString(R.string.error_message_something_went_wrong)
                }
                else -> binding.sectionHomePopularTvShow.status = it.status
            }
        })

        viewModel.getTvShowsPopularHighlight()
    }

    override fun onContentClickListener(id: Long?, type: ContentType?, view: View) {
        HomeFragmentDirections.actionHomeToDetail(id ?: -1, type ?: ContentType.MOVIE).also {
            view.findNavController().navigate(it)
        }
    }
}