package npo.kib.spyapp.hacks

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.WindowManager.LayoutParams;
import android.view.WindowManager.LayoutParams.*
import android.widget.FrameLayout
import npo.kib.spyapp.R


class OverlayService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("myLogs", "OverlayService onCreate")
        displayOverlayView()
    }

    private fun displayOverlayView() {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val layoutParamsType = TYPE_APPLICATION_OVERLAY

        val params = LayoutParams(
            MATCH_PARENT,
            MATCH_PARENT,
            layoutParamsType,
            FLAG_NOT_FOCUSABLE or FLAG_NOT_TOUCH_MODAL or FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT
        )

        val interceptorLayout: FrameLayout = object : FrameLayout(this) {
            override fun dispatchKeyEvent(event: KeyEvent): Boolean {
                if (event.action != KeyEvent.ACTION_DOWN)
                    return super.dispatchKeyEvent(event)

                // Check if the HOME button is pressed
                if (event.keyCode != KeyEvent.KEYCODE_BACK)
                    return super.dispatchKeyEvent(event)

                Log.v("myLogs", "BACK Button Pressed")

                // As we've taken action, we'll return true to prevent other apps from consuming the event as well
                return true
            }
        }

        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val floatyView = inflater.inflate(R.layout.floating_view, interceptorLayout)
        floatyView.setOnTouchListener { v, event ->
            v.performClick();
            Log.v("myLogs", "onTouch...");
            true
        }

        windowManager.addView(floatyView, params)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY

    override fun onBind(intent: Intent?): IBinder? = null

}