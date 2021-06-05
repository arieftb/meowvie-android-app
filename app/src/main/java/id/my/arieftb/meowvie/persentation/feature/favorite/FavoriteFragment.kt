package id.my.arieftb.meowvie.persentation.feature.favorite

import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.databinding.FragmentFavoriteBinding

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>() {
    override fun getViewBinding(): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater)
}