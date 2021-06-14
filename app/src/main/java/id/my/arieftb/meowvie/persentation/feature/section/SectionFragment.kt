package id.my.arieftb.meowvie.persentation.feature.section

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.constant.SectionType
import id.my.arieftb.meowvie.databinding.FragmentSectionBinding
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.persentation.adapter.ContentPortraitGridRecyclerAdapter
import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.persentation.model.Status
import id.my.arieftb.meowvie.utils.extension.hide
import id.my.arieftb.meowvie.utils.extension.show

@AndroidEntryPoint
class SectionFragment : BaseFragment<FragmentSectionBinding>() {
    private val viewModel: SectionViewModelImpl by viewModels()
    private var page: Int = 1
    private var type: SectionType = SectionType.NEW_MOVIE
    private var adapter: ContentPortraitGridRecyclerAdapter? = null

    override fun getViewBinding(): FragmentSectionBinding =
        FragmentSectionBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArgs()
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
    }

    private fun initContentList() {
        adapter = ContentPortraitGridRecyclerAdapter(requireContext()).also {
            with(binding.listSection) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = it
            }
        }
    }

    private fun getContents() {
        viewModel.contentData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> setSuccessView(it.data)
                Status.ERROR -> setErrorView()
                else -> binding.shimmerSectionDefault.show()
            }
        })

        viewModel.getContents(page, type)
    }

    private fun setSuccessView(data: List<Content>?) {
        if (!data.isNullOrEmpty()) {
            with(binding) {
                shimmerSectionDefault.hide()
                textSectionErrorMessage.hide()
                listSection.show()
                adapter?.addAll(data)
            }
        } else {
            setErrorView(getString(R.string.error_message_list_empty))
        }
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
}