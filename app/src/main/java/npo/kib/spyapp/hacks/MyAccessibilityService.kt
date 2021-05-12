package npo.kib.spyapp.hacks

import android.accessibilityservice.AccessibilityGestureEvent
import android.accessibilityservice.AccessibilityService
import android.graphics.PixelFormat
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.widget.FrameLayout
import npo.kib.spyapp.R


class MyAccessibilityService : AccessibilityService() {

    private lateinit var mLayout: FrameLayout

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("myLogs", "MyAccessibilityService onServiceConnected")

        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        mLayout = FrameLayout(this)

        val params = WindowManager.LayoutParams()
        params.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
        params.format = PixelFormat.TRANSLUCENT
        params.flags = params.flags or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        params.flags = params.flags or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        params.flags = params.flags or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        params.gravity = Gravity.TOP

        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.proxy_layout, mLayout)
        windowManager.addView(mLayout, params)

        view.setOnClickListener { Log.d("myLogs", "MyAccessibilityService onClick") }
    }

    override fun onKeyEvent(event: KeyEvent?): Boolean {
        Log.d("myLogs", "MyAccessibilityService onKeyEvent")
        return super.onKeyEvent(event)
    }

    override fun onGesture(gestureEvent: AccessibilityGestureEvent): Boolean {
        Log.d("myLogs", "MyAccessibilityService onGesture")
        return super.onGesture(gestureEvent)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d("myLogs", "MyAccessibilityService onAccessibilityEvent ${event?.text}")
    }

    override fun onInterrupt() {
        Log.d("myLogs", "MyAccessibilityService onInterrupt")
    }
}