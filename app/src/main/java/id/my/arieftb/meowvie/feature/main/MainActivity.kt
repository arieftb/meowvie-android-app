package id.my.arieftb.meowvie.feature.main

import android.os.Bundle
import id.my.arieftb.meowvie.base.BaseActivity
import id.my.arieftb.meowvie.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}