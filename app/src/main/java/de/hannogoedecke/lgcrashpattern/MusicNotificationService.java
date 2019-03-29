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
    public static final String ACTION_TOGGLE_PLAYBACK = ".action.TOGGLE_PLAYBACK";
    public static final String ACTION_STOP = ".action.STOP";
    public static final String ACTION_NEXT = ".action.NEXT";
    public static final String ACTION_PREVIOUS = ".action.PREVIOUS";


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BaseNotification notification;
        if (intent.getAction().equals(ACTION_NOTIFICATION_CRASH)) {
            notification = new NotificationImpl();

            startForeground(
                    BaseNotification.NOTIFICATION_ID,
                    notification.createNotification(getApplicationContext())
            );
        } else {
            // Notification actions
            Toast.makeText(getApplicationContext(), intent.getAction(), Toast.LENGTH_SHORT).show();
        }

        return START_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
