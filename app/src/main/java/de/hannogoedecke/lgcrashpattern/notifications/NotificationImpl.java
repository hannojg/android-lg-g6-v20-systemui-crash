package de.hannogoedecke.lgcrashpattern.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import de.hannogoedecke.lgcrashpattern.MainActivity;
import de.hannogoedecke.lgcrashpattern.MusicNotificationService;
import de.hannogoedecke.lgcrashpattern.R;

import static de.hannogoedecke.lgcrashpattern.MusicNotificationService.ACTION_NEXT;
import static de.hannogoedecke.lgcrashpattern.MusicNotificationService.ACTION_PREVIOUS;
import static de.hannogoedecke.lgcrashpattern.MusicNotificationService.ACTION_STOP;
import static de.hannogoedecke.lgcrashpattern.MusicNotificationService.ACTION_TOGGLE_PLAYBACK;

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

        setActionItems(context, mBuilder);

        mBuilder.setContentTitle("LG Notification Crash Demo");
        mBuilder.setSubText("DEMO");

        return mBuilder.build();
    }

    private void setActionItems(Context context, NotificationCompat.Builder mBuilder) {
        Intent nextIntent = new Intent(context, MusicNotificationService.class);
        nextIntent.setAction(ACTION_NEXT);
        PendingIntent pendingNextIntent = PendingIntent.getService(context, 100, nextIntent, 0);

        nextIntent.setAction(ACTION_PREVIOUS);
        PendingIntent pendingPreviousIntent = PendingIntent.getService(context, 100, nextIntent, 0);

        Intent stopIntent = new Intent(context, MusicNotificationService.class);
        stopIntent.setAction(ACTION_STOP);
        PendingIntent stopPendingIntent = PendingIntent.getService(context, 100, stopIntent, 0);

        Intent toggleIntent = new Intent(context, MusicNotificationService.class);
        toggleIntent.setAction(ACTION_TOGGLE_PLAYBACK);
        PendingIntent pendingTogglePlaybackIntent = PendingIntent.getService(context, 100, toggleIntent, 0);

        NotificationCompat.Action actionClose = new NotificationCompat.Action(R.drawable.ic_close_white_24dp, "Close", stopPendingIntent);
        NotificationCompat.Action actionPrev = new NotificationCompat.Action(R.drawable.ic_skip_previous_white_24dp, "Previous", pendingPreviousIntent);
        NotificationCompat.Action actionToggle = new NotificationCompat.Action(R.drawable.ic_pause_white_24dp,"PlayToggle.Pausing", pendingTogglePlaybackIntent);
        NotificationCompat.Action actionNext = new NotificationCompat.Action(R.drawable.ic_skip_next_white_24dp, "Next", pendingNextIntent);

        mBuilder.mActions.add(0, actionClose);
        mBuilder.mActions.add(1, actionPrev);
        mBuilder.mActions.add(2, actionToggle);
        mBuilder.mActions.add(3, actionNext);
    }
}
