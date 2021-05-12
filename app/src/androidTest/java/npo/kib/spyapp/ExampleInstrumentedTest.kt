package npo.kib.spyapp

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import npo.kib.spyapp.foreground.SpyService
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        appContext.startForegroundService(Intent(appContext, SpyService::class.java))
//        appContext.stopService(Intent(appContext, SpyService::class.java))
    }
}