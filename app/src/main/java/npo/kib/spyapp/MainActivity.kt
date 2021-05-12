package npo.kib.spyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import npo.kib.spyapp.foreground.SpyService
import npo.kib.spyapp.hacks.startOverlayService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        launchWorkers()
//        startSpyService()

        startOverlayService()
    }
}