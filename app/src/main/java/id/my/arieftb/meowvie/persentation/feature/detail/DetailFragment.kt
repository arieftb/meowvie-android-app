package id.my.arieftb.meowvie.persentation.feature.detail

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.databinding.FragmentDetailBinding
import id.my.arieftb.meowvie.persentation.base.BaseFragment

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
