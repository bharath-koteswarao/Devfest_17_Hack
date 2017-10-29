package bk.myapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by koteswarao on 29-10-2017.
 * ${CLASS}
 */

public class Notifs {
    Context context;

    public Notifs(Context context) {
        this.context = context;
    }

    public void showNotification(String reason) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pi = PendingIntent.getActivity(context, 10, intent, 0);
        Notification notification = new Notification.Builder(context)
            .setContentTitle("New Emergency")
            .setSmallIcon(R.mipmap.icon)
            .setContentText(reason)
            .setContentIntent(pi)
            .build();
        manager.notify(0,notification);
    }
}
