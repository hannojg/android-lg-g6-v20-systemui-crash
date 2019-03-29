package de.hannogoedecke.lgcrashpattern.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import de.hannogoedecke.lgcrashpattern.MainActivity;
import de.hannogoedecke.lgcrashpattern.R;

public class NotificationImpl implements BaseNotification {

    private static final String NOTIFICATION_CHANNELID = "test-"+String.valueOf(NOTIFICATION_ID);


    @Override
    public Notification createNotification(Context context) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNELID);
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setColor(context.getResources().getColor(R.color.black));
        mBuilder.setShowWhen(false);
        mBuilder.setChannelId(NOTIFICATION_CHANNELID);

        Notification.MediaStyle mMediaStyle = new Notification.MediaStyle();

        // this is where the bug exposes
        androidx.media.app.NotificationCompat.MediaStyle mediaStyle
                = new androidx.media.app.NotificationCompat.MediaStyle();


        mMediaStyle.setShowActionsInCompactView(1, 2, 3);
        mBuilder.setStyle(mediaStyle);

        mBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
        mBuilder.setContentIntent(
                PendingIntent.getActivity(
                        context,
                        0,
                        new Intent(context, MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT
                        ));

        return mBuilder.build();
    }
}
