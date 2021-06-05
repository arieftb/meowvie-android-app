package id.my.arieftb.meowvie.persentation.feature.account

import id.my.arieftb.meowvie.persentation.base.BaseFragment
import id.my.arieftb.meowvie.databinding.FragmentAccountBinding

class AccountFragment: BaseFragment<FragmentAccountBinding>() {
    override fun getViewBinding(): FragmentAccountBinding = FragmentAccountBinding.inflate(layoutInflater)
}