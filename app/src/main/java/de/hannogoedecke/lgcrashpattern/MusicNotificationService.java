package de.hannogoedecke.lgcrashpattern;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import de.hannogoedecke.lgcrashpattern.notifications.BaseNotification;
import de.hannogoedecke.lgcrashpattern.notifications.NotificationImpl;

public class MusicNotificationService extends Service {

    public static final String ACTION_NOTIFICATION_CRASH = ".action.notificationcrash";


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BaseNotification notification = null;
        if (intent.getAction().equals(ACTION_NOTIFICATION_CRASH)) {
            Toast.makeText(getApplicationContext(), "CRASH!", Toast.LENGTH_SHORT).show();
            notification = new NotificationImpl();
        }

        startForeground(
                BaseNotification.NOTIFICATION_ID,
                notification.createNotification(getApplicationContext())
        );

        return START_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
