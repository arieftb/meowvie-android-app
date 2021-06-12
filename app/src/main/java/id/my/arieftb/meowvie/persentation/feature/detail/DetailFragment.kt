package id.my.arieftb.meowvie.persentation.feature.detail

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.databinding.FragmentDetailBinding
import id.my.arieftb.meowvie.persentation.base.BaseFragment

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    var id: Long = -1
    var type: ContentType = ContentType.MOVIE

    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArgs()
    }

    private fun initArgs() {
        arguments?.let {
            id = it.getLong("id")
            type = it.get("type") as ContentType
        }
    }
}
