package id.my.arieftb.meowvie.persentation.feature.main

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import id.my.arieftb.meowvie.R
import id.my.arieftb.meowvie.databinding.ActivityMainBinding
import id.my.arieftb.meowvie.persentation.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }

    private fun initView() {
        setupNavigation()
    }

    private fun setupNavigation() {
        val appBarConfig = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.exploreFragment,
            R.id.favoriteFragment,
            R.id.accountFragment
        ).build()

        (supportFragmentManager.findFragmentById(binding.fragmentMainContainer.id) as NavHostFragment).let {
            binding.bottomNavigationMain.setupWithNavController(it.findNavController())
            NavigationUI.setupActionBarWithNavController(
                this@MainActivity,
                it.findNavController(),
                appBarConfig
            )
        }
    }
}