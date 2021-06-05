package id.my.arieftb.meowvie.persentation.feature.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.databinding.ActivityMainBinding
import id.my.arieftb.meowvie.persentation.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        (supportFragmentManager.findFragmentById(binding.fragmentMainContainer.id) as NavHostFragment).let {
            binding.bottomNavigationMain.setupWithNavController(it.findNavController())
        }
    }
}