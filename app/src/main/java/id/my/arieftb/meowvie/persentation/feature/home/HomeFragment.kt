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
import id.my.arieftb.meowvie.persentation.feature.tv_show.TvShowsPortraitRecyclerAdapter
import id.my.arieftb.meowvie.persentation.model.Status

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var movieAdapter: MoviesPortraitRecyclerAdapter
    private lateinit var tvShowAdapter: TvShowsPortraitRecyclerAdapter
    private lateinit var movieUpcomingAdapter: MoviesBannerRecyclerAdapter

    private val viewModel: HomeViewModelImpl by viewModels()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getMoviesHighlight()
        getTvShowsHighlight()
        getMoviesUpcomingHighlight()
    }

    private fun initView() {
        movieAdapter = MoviesPortraitRecyclerAdapter(requireContext())
        tvShowAdapter = TvShowsPortraitRecyclerAdapter(requireContext())
        movieUpcomingAdapter = MoviesBannerRecyclerAdapter(requireContext())
    }

    private fun getMoviesHighlight() {
        viewModel.moviesData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.sectionHomeNewMovie.status = it.status
                    binding.sectionHomeNewMovie.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    binding.sectionHomeNewMovie.adapter = movieAdapter
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
                    binding.sectionHomeNewTvShow.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    binding.sectionHomeNewTvShow.adapter = tvShowAdapter
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
                    binding.sectionHomeComingSoonMovie.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    binding.sectionHomeComingSoonMovie.adapter = movieUpcomingAdapter
                    movieUpcomingAdapter.addAll(it.data)
                }
                else -> binding.sectionHomeComingSoonMovie.status = it.status
            }
        })

        viewModel.getMoviesUpcomingHighlight()
    }
}