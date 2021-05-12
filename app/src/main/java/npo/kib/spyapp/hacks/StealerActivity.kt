package npo.kib.spyapp.hacks

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import npo.kib.spyapp.R

class StealerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stealer)

        window.addFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }
}