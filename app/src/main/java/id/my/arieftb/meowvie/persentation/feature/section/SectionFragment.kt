package id.my.arieftb.meowvie.persentation.feature.section

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.databinding.FragmentSectionBinding
import id.my.arieftb.meowvie.persentation.base.BaseFragment

@AndroidEntryPoint
class SectionFragment: BaseFragment<FragmentSectionBinding>() {
    private val viewModel: SectionViewModelImpl by viewModels()

    override fun getViewBinding(): FragmentSectionBinding = FragmentSectionBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}