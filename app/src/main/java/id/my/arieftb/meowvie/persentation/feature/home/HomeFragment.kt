package id.my.arieftb.meowvie.persentation.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.databinding.FragmentHomeBinding
import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.persentation.feature.movie.MoviesBannerRecyclerAdapter
import id.my.arieftb.meowvie.persentation.feature.movie.MoviesPortraitRecyclerAdapter
import id.my.arieftb.meowvie.persentation.feature.tv_show.TvShowsBannerRecyclerAdapter
import id.my.arieftb.meowvie.persentation.feature.tv_show.TvShowsPortraitRecyclerAdapter
import id.my.arieftb.meowvie.persentation.model.Status

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var movieAdapter: MoviesPortraitRecyclerAdapter
    private lateinit var tvShowAdapter: TvShowsPortraitRecyclerAdapter
    private lateinit var movieUpcomingAdapter: MoviesBannerRecyclerAdapter
    private lateinit var tvShowUpcomingAdapter: TvShowsBannerRecyclerAdapter
    private lateinit var moviePopularAdapter: MoviesPortraitRecyclerAdapter

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
    }

    private fun initView() {
        initMovieHighlightAdapter()
        initTvShowHighlightAdapter()
        initMovieUpcomingHighlightAdapter()
        initTvShowUpcomingHighlightAdapter()
        initMoviesPopularHighlightAdapter()
    }

    private fun initMovieHighlightAdapter() {
        binding.sectionHomeNewMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MoviesPortraitRecyclerAdapter(requireContext()).also {
                movieAdapter = it
            }
        }
    }

    private fun initTvShowHighlightAdapter() {
        binding.sectionHomeNewTvShow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TvShowsPortraitRecyclerAdapter(requireContext()).also {
                tvShowAdapter = it
            }
        }
    }

    private fun initMovieUpcomingHighlightAdapter() {
        binding.sectionHomeComingSoonMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MoviesBannerRecyclerAdapter(requireContext()).also {
                movieUpcomingAdapter = it
            }
        }
    }

    private fun initTvShowUpcomingHighlightAdapter() {
        binding.sectionHomeComingSoonTvShow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TvShowsBannerRecyclerAdapter(requireContext()).also {
                tvShowUpcomingAdapter = it
            }
        }
    }

    private fun initMoviesPopularHighlightAdapter() {
        binding.sectionHomePopularMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MoviesPortraitRecyclerAdapter(requireContext()).also {
                moviePopularAdapter = it
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
                else -> binding.sectionHomeComingSoonTvShow.status = it.status
            }
        })

        viewModel.getTvShowsUpcomingHighlight()
    }

    private fun getMoviesPopularHighlight() {
        viewModel.moviesPopularData.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.sectionHomePopularMovie.status = it.status
                    moviePopularAdapter.addAll(it.data)
                }
                else -> binding.sectionHomePopularMovie.status = it.status
            }
        })

        viewModel.getMoviesPopularHighlight()
    }
}