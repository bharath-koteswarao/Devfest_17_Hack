package bharat.sos_tarp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import ru.rambler.libs.swipe_layout.SwipeLayout;

public class NotificationReceiver extends AppCompatActivity {
    SwipeLayout layout;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_receiver);
        layout = (SwipeLayout) findViewById(R.id.swipe_layout);
    }


    public void showGoogleMap(View view) {
        Uri.Builder directionsBuilder = new Uri.Builder()
                .scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("destination", 12.972391 + "," + 79.161289);

        startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));
    }
}
