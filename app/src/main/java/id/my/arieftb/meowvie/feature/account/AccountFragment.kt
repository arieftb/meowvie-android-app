package id.my.arieftb.meowvie.feature.account

import id.my.arieftb.meowvie.base.BaseFragment
import id.my.arieftb.meowvie.databinding.FragmentAccountBinding

class AccountFragment: BaseFragment<FragmentAccountBinding>() {
    override fun getViewBinding(): FragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)
}