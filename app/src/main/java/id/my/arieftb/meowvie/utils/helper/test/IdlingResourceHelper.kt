package id.my.arieftb.meowvie.utils.helper.test

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResourceHelper {
    private val RESOURCE: String? = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)
    fun increment() {
        espressoTestIdlingResource.increment()
    }
    fun decrement() {
        espressoTestIdlingResource.decrement()
    }
    fun getEspressoIdlingResource(): IdlingResource {
        return espressoTestIdlingResource
    }
}