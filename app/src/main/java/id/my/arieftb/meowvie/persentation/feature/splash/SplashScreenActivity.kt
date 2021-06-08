package id.my.arieftb.meowvie.persentation.feature.splash

import android.content.Intent
import android.os.Bundle
import id.my.arieftb.meowvie.persentation.base.BaseActivity
import id.my.arieftb.meowvie.databinding.ActivitySplashScreenBinding
import id.my.arieftb.meowvie.persentation.feature.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun getViewBinding(): ActivitySplashScreenBinding =
        ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)

            Intent(this@SplashScreenActivity, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}