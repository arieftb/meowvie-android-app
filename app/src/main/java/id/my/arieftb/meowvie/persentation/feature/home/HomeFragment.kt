package id.my.arieftb.meowvie.persentation.feature.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModelImpl by viewModels()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.moviesData.observe(viewLifecycleOwner, {
            Log.d("MeowVieTAG", "onViewCreated: $it")
        })

        viewModel.getMovies(1)
    }
}