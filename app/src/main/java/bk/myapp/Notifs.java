package bk.myapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

/**
 * Created by koteswarao
 * ${CLASS}
 */

public class Notifs {
    Context context;

    public Notifs(Context context) {
        this.context = context;

    }

    public void showNotification(String reason) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        LocationHelper helper = new LocationHelper(context);
        Location userLocation = helper.getLastKnownLocation();
        double latitude = 0;
        double longitude = 0;
        if (userLocation == null) {
            latitude = 12.968472;
            longitude = 79.159785;
        } else {
            latitude = userLocation.getLatitude();
            longitude = userLocation.getLongitude();
        }
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("Latitude", latitude);
        intent.putExtra("Longitude", longitude);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(context, 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(context)
                .setContentTitle("New Emergency")
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentText(reason)
                .setContentIntent(pi)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setDefaults(Notification.DEFAULT_SOUND)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(0, notification);
    }
}
