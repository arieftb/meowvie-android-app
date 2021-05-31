package id.my.arieftb.meowvie.feature.favorite

import id.my.arieftb.meowvie.base.BaseFragment
import id.my.arieftb.meowvie.databinding.FragmentFavoriteBinding

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>() {
    override fun getViewBinding(): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater)
}