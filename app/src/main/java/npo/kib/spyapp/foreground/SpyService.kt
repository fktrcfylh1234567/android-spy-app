package npo.kib.spyapp.foreground

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import npo.kib.spyapp.MainActivity
import npo.kib.spyapp.R


class SpyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("myLogs", "SpyService onCreate")
        startForeground(5, createForegroundNotification())

        GlobalScope.launch {
            while (true) {
                Log.d("myLogs", "SpyService is alive!!!")
                delay(5000)
                startActivity()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("myLogs", "SpyService onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int =
        START_REDELIVER_INTENT

    override fun onBind(intent: Intent?): IBinder? = null

    private fun startActivity() {
        Log.d("myLogs", "SpyService starting Activity")
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun createForegroundNotification(): Notification {
        val pendingIntent: PendingIntent = Intent(this, MainActivity::class.java)
            .let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }

        val channel = NotificationChannel("42", "channelName", NotificationManager.IMPORTANCE_LOW)
        channel.description = "description"

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        return NotificationCompat.Builder(this, "42")
            .setContentTitle("SpyService is alive!!!")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setTicker("Ticker")
            .setPriority(NotificationManager.IMPORTANCE_LOW)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
    }
}