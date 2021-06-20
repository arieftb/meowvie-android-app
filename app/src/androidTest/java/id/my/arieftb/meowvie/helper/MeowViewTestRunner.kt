package id.my.arieftb.meowvie.helper

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MeowViewTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, MeowViewTestApplication_Application::class.java.name, context)
    }
}