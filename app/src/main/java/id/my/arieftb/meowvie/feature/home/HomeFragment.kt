package id.my.arieftb.meowvie.feature.home

import id.my.arieftb.meowvie.base.BaseFragment
import id.my.arieftb.meowvie.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
}