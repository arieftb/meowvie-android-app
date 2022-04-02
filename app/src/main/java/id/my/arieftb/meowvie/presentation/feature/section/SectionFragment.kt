package id.my.arieftb.meowvie.presentation.feature.section

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.domain.constant.SectionType
import id.my.arieftb.meowvie.databinding.FragmentSectionBinding
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.presentation.adapter.ContentPortraitGridRecyclerAdapter
import id.my.arieftb.meowvie.presentation.adapter.ContentRecyclerListener
import id.my.arieftb.meowvie.presentation.base.BaseFragment
import id.my.arieftb.meowvie.presentation.model.Status
import id.my.arieftb.meowvie.utils.extension.hide
import id.my.arieftb.meowvie.utils.extension.show
import id.my.arieftb.meowvie.utils.helper.test.IdlingResourceHelper

@AndroidEntryPoint
class SectionFragment : BaseFragment<FragmentSectionBinding>(), ContentRecyclerListener {
    private val viewModel: SectionViewModelImpl by viewModels()
    private var page: Int = 1
    private var type: SectionType = SectionType.NEW_MOVIE
    private var adapter: ContentPortraitGridRecyclerAdapter? = null
    private var isPaginationEnable: Boolean = false

    override fun getViewBinding(): FragmentSectionBinding =
        FragmentSectionBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IdlingResourceHelper.increment()
        initArgs()
        viewModel.getContents(page, type)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getContents()
    }

    private fun initArgs() {
        arguments?.let {
            type = it.get("type") as SectionType
        }
    }

    private fun initView() {
        initContentList()
        initRecyclerScroll()
    }

    private fun initContentList() {
        adapter = ContentPortraitGridRecyclerAdapter(requireContext()).apply {
            listener = this@SectionFragment
        }.also {
            with(binding.listSection) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = it
            }
        }
    }

    private fun initRecyclerScroll() {
        binding.listSection.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!binding.listSection.canScrollVertically(1) && isPaginationEnable && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    isPaginationEnable = false
                    page += 1
                    viewModel.getContents(page, type)
                }
            }
        })
    }

    private fun getContents() {
        viewModel.contentData.observe(viewLifecycleOwner, {
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
                if (!listSection.isVisible) listSection.show()
                shimmerSectionDefault.hide()
                textSectionErrorMessage.hide()
                adapter?.replaceAll(data)
            }
            isPaginationEnable = true
            IdlingResourceHelper.decrement()
        } else {
            setErrorView(getString(R.string.error_message_list_empty))
        }
        IdlingResourceHelper.decrement()
    }

    private fun setErrorView(errorMessage: String? = getString(R.string.error_message_something_went_wrong)) {
        if (page == 1) {
            with(binding) {
                shimmerSectionDefault.hide()
                listSection.hide()
                binding.textSectionErrorMessage.apply {
                    show()
                    text = errorMessage
                }
            }
        }
    }

    private fun setLoadingView() {
        if (page == 1) {
            binding.shimmerSectionDefault.show()
        }
    }

    override fun onContentClickListener(id: Long?, type: ContentType?, view: View, title: String?) {
        SectionFragmentDirections.actionSectionToDetail(id!!, type!!, title).also {
            view.findNavController().navigate(it)
        }
    }
}