package id.my.arieftb.meowvie.persentation.feature.explore

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.databinding.FragmentExploreBinding

@AndroidEntryPoint
class ExploreFragment: BaseFragment<FragmentExploreBinding>() {
    private val viewModel: ExploreViewModelImpl by viewModels()
    private var page: Int = 1

    override fun getViewBinding(): FragmentExploreBinding = FragmentExploreBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        initSearchView()
    }

    private fun initSearchView() {
        binding.searchExplore.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.search(page, query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                page = 1
                return false
            }
        })
    }
}