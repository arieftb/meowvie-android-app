package id.my.arieftb.meowvie.feature.explore

import id.my.arieftb.meowvie.base.BaseFragment
import id.my.arieftb.meowvie.databinding.FragmentExploreBinding

class ExploreFragment: BaseFragment<FragmentExploreBinding>() {
    override fun getViewBinding(): FragmentExploreBinding = FragmentExploreBinding.inflate(layoutInflater)
}