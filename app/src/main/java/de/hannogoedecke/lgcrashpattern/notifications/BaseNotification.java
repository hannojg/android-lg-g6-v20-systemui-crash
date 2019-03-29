package de.hannogoedecke.lgcrashpattern.notifications;

import android.app.Notification;
import android.content.Context;

public interface BaseNotification {
    public int NOTIFICATION_ID = 908115;

    Notification createNotification(Context context);
}
