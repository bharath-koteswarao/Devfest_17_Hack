package bk.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import ru.rambler.libs.swipe_layout.SwipeLayout;

public class NotificationReceiver extends AppCompatActivity {
    SwipeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_receiver);
        layout = (SwipeLayout) findViewById(R.id.swipe_layout);

    }

    public void goToMapActivity(View view) {
        startActivity(new Intent(this, MapActivity.class));
        tellFirebaseThatAUserHasRespondedToRequest();
    }

    private void tellFirebaseThatAUserHasRespondedToRequest() {

    }
}
