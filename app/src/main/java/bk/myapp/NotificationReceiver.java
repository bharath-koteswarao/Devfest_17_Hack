package bk.myapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import ru.rambler.libs.swipe_layout.SwipeLayout;

public class NotificationReceiver extends AppCompatActivity {
    SwipeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_receiver);
        layout = (SwipeLayout) findViewById(R.id.swipe_layout);
    }
}
