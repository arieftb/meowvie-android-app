package id.my.arieftb.meowvie.persentation.feature.explore

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.databinding.FragmentExploreBinding
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.persentation.adapter.ContentPortraitGridRecyclerAdapter
import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.persentation.model.Status
import id.my.arieftb.meowvie.utils.extension.hide
import id.my.arieftb.meowvie.utils.extension.show

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    private val viewModel: ExploreViewModelImpl by viewModels()
    private var adapter: ContentPortraitGridRecyclerAdapter? = null
    private var page: Int = 1

    override fun getViewBinding(): FragmentExploreBinding =
        FragmentExploreBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getContents()
    }

    private fun initView() {
        initContentList()
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

    private fun initContentList() {
        adapter = ContentPortraitGridRecyclerAdapter(requireContext()).apply {

        }.also {
            with(binding.listExplore) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = it
            }
        }
    }

    private fun getContents() {
        viewModel.searchData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> setSuccessView(it.data)
                Status.ERROR -> setErrorView()
                else -> setLoadingView()
            }
        })
    }

    private fun setSuccessView(data: List<Content>?) {
        if (!data.isNullOrEmpty()) {
            with(binding) {
                shimmerExploreDefault.hide()
                textExploreErrorMessage.hide()
                listExplore.show()
                adapter?.replaceAll(data)
            }
//            isPaginationEnable = true
        } else {
            setErrorView(getString(R.string.error_message_list_empty))
        }
    }

    private fun setErrorView(errorMessage: String? = getString(R.string.error_message_something_went_wrong)) {
        if (page == 1) {
            with(binding) {
                shimmerExploreDefault.hide()
                binding.listExplore.hide()
                binding.textExploreErrorMessage.apply {
                    show()
                    text = errorMessage
                }
            }
        }
    }

    private fun setLoadingView() {
        if (page == 1) {
            binding.listExplore.hide()
            binding.shimmerExploreDefault.show()
        }
    }
}