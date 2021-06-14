package id.my.arieftb.meowvie.persentation.feature.section

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.constant.SectionType
import id.my.arieftb.meowvie.databinding.FragmentSectionBinding
import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.persentation.model.Status
import id.my.arieftb.meowvie.utils.extension.hide
import id.my.arieftb.meowvie.utils.extension.show

@AndroidEntryPoint
class SectionFragment : BaseFragment<FragmentSectionBinding>() {
    private val viewModel: SectionViewModelImpl by viewModels()
    private var page: Int = 1
    private var type: SectionType = SectionType.NEW_MOVIE

    override fun getViewBinding(): FragmentSectionBinding =
        FragmentSectionBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArgs()
        getContents()
    }

    private fun initArgs() {
        arguments?.let {
            type = it.get("type") as SectionType
        }
    }

    private fun getContents() {
        viewModel.contentData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                }
                Status.ERROR -> setErrorView()
                else -> binding.shimmerSectionDefault.show()
            }
        })

        viewModel.getContents(page, type)
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