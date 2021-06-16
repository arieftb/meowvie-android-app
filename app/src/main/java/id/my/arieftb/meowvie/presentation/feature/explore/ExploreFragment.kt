package id.my.arieftb.meowvie.presentation.feature.explore

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.databinding.FragmentExploreBinding
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.presentation.adapter.ContentPortraitGridRecyclerAdapter
import id.my.arieftb.meowvie.presentation.adapter.ContentRecyclerListener
import id.my.arieftb.meowvie.presentation.base.BaseFragment
import id.my.arieftb.meowvie.presentation.model.Status
import id.my.arieftb.meowvie.utils.extension.hide
import id.my.arieftb.meowvie.utils.extension.show

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>(), ContentRecyclerListener {
    private val viewModel: ExploreViewModelImpl by viewModels()
    private var adapter: ContentPortraitGridRecyclerAdapter? = null
    private var page: Int = 1
    private var searchKey: String? = null
    private var isPaginationEnable: Boolean = false

    override fun getViewBinding(): FragmentExploreBinding =
        FragmentExploreBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getContents()
    }

    private fun initView() {
        initContentList()
        initRecyclerScroll()
        initSearchView()
    }

    private fun initSearchView() {
        binding.searchExplore.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.search(page, query)
                    searchKey = query
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
            listener = this@ExploreFragment
        }.also {
            with(binding.listExplore) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = it
            }
        }
    }

    private fun initRecyclerScroll() {
        binding.listExplore.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!binding.listExplore.canScrollVertically(1)
                    && isPaginationEnable
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    isPaginationEnable = false
                    page += 1
                    if (!searchKey.isNullOrEmpty()) {
                        viewModel.search(page, searchKey!!)
                    }
                }
            }
        })
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
            isPaginationEnable = true
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
            binding.textExploreErrorMessage.hide()
            binding.shimmerExploreDefault.show()
        }
    }

    override fun onContentClickListener(id: Long?, type: ContentType?, view: View, title: String?) {
        ExploreFragmentDirections.actionExploreToDetail(id!!, type!!, title).also {
            view.findNavController().navigate(it)
        }
    }
}