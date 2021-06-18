package id.my.arieftb.meowvie.helper

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.spekframework.spek2.dsl.GroupBody

fun GroupBody.applyInstantTaskExecutor() {
    beforeEachTest {
        ArchTaskExecutor.getInstance().setDelegate(InstantTaskExecutor)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun GroupBody.applyTestDispatcher() {
    beforeEachTest {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    afterEachTest {
        Dispatchers.resetMain()
    }

}

private object InstantTaskExecutor : TaskExecutor() {
    override fun executeOnDiskIO(runnable: Runnable) {
        runnable.run()
    }

    override fun postToMainThread(runnable: Runnable) {
        runnable.run()
    }

    override fun isMainThread(): Boolean {
        return true
    }

}