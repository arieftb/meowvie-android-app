package id.my.arieftb.meowvie.presentation.feature.explore

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.core.domain.constant.ContentType
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.databinding.FragmentExploreBinding
import id.my.arieftb.meowvie.presentation.adapter.ContentPortraitGridRecyclerAdapter
import id.my.arieftb.meowvie.presentation.adapter.ContentRecyclerListener
import id.my.arieftb.meowvie.presentation.base.BaseFragment
import id.my.arieftb.meowvie.presentation.model.Status
import id.my.arieftb.meowvie.utils.extension.hide
import id.my.arieftb.meowvie.utils.extension.show
import id.my.arieftb.meowvie.utils.helper.test.IdlingResourceHelper
import javax.inject.Inject


@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>(), ContentRecyclerListener {
    private val viewModel: ExploreViewModelImpl by viewModels()

    private var page: Int = 1
    private var searchKey: String? = null
    private var isPaginationEnable: Boolean = false

    @Inject
    lateinit var exploreAdapter: ContentPortraitGridRecyclerAdapter

    override fun getViewBinding(): FragmentExploreBinding =
        FragmentExploreBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getContents()
    }

    override fun onDestroyView() {
        binding?.let { binding ->
            binding.listExplore.adapter = null
        }
        super.onDestroyView()
    }

    private fun initView() {
        initContentList()
        initRecyclerScroll()
        initSearchView()
    }

    private fun initSearchView() {
        binding?.let { binding ->
            with(binding) {
                buttonExploreSearch.setOnClickListener {
                    if (formExploreSearch.text.toString().isNotEmpty()) {
                        formExploreSearch.text.toString().let {
                            IdlingResourceHelper.increment()
                            viewModel.search(page, it)
                            searchKey = it
                        }
                    }
                }
            }
        }
    }

    private fun initContentList() {

        binding?.let { binding ->
            with(binding.listExplore) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = exploreAdapter
            }
        }
    }

    private fun initRecyclerScroll() {
        binding?.let { binding ->
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
    }

    private fun getContents() {
        viewModel.searchData.observe(viewLifecycleOwner) {
            hideKeyBoard()
            when (it.status) {
                Status.SUCCESS -> setSuccessView(it.data)
                Status.ERROR -> setErrorView()
                else -> setLoadingView()
            }
        }
    }

    private fun setSuccessView(data: List<Content>?) {
        IdlingResourceHelper.decrement()
        if (!data.isNullOrEmpty()) {
            binding?.let { binding ->
                with(binding) {
                    shimmerExploreDefault.hide()
                    textExploreErrorMessage.hide()
                    listExplore.show()
                    exploreAdapter.replaceAll(data)
                }
            }
            isPaginationEnable = true
        } else {
            setErrorView(getString(R.string.error_message_list_empty))
        }
    }

    private fun setErrorView(errorMessage: String? = getString(R.string.error_message_something_went_wrong)) {
        if (page == 1) {
            binding?.let { binding ->
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
    }

    private fun setLoadingView() {
        if (page == 1) {
            binding?.let { binding ->
                binding.listExplore.hide()
                binding.textExploreErrorMessage.hide()
                binding.shimmerExploreDefault.show()
            }
        }
    }

    private fun hideKeyBoard() {
        val imm: InputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity?.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onContentClickListener(id: Long?, type: ContentType?, view: View, title: String?) {
        ExploreFragmentDirections.actionExploreToDetail(id!!, type!!, title).also {
            view.findNavController().navigate(it)
        }
    }
}