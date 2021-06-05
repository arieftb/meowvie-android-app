package id.my.arieftb.meowvie.persentation.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.databinding.FragmentHomeBinding
import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.persentation.feature.movie.MoviesPortraitRecyclerAdapter
import id.my.arieftb.meowvie.persentation.model.Status

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    lateinit var movieAdapter: MoviesPortraitRecyclerAdapter

    private val viewModel: HomeViewModelImpl by viewModels()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        handleGetMovies()
    }

    private fun initView() {
        movieAdapter = MoviesPortraitRecyclerAdapter(requireContext())
    }

    private fun handleGetMovies() {
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

        viewModel.getMovies(1)
    }
}