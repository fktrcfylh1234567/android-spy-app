package npo.kib.spyapp.foreground

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import npo.kib.spyapp.RestartReceiver
import java.util.concurrent.TimeUnit

fun Context.launchWorkers() {
    val work = PeriodicWorkRequestBuilder<SpyWorker>(15, TimeUnit.SECONDS).build()
    WorkManager.getInstance(this)
        .enqueueUniquePeriodicWork("SpyWork", ExistingPeriodicWorkPolicy.REPLACE, work)
}

fun Context.startSpyService() {
    startForegroundService(Intent(this, SpyService::class.java))
}

fun Context.hitAlarm() {
    val intent = Intent(this, RestartReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(applicationContext, 234324243, intent, 0)
    val alarmManager = getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
    alarmManager[AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000] = pendingIntent
}
