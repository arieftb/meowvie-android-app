package id.my.arieftb.meowvie.feature.splash

import android.os.Bundle
import id.my.arieftb.meowvie.base.BaseActivity
import id.my.arieftb.meowvie.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun getViewBinding(): ActivitySplashScreenBinding =
        ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: 5/16/21 - Add Init View Here
    }
}