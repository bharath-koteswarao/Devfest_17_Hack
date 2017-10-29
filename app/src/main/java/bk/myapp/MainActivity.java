package bk.myapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import bk.myapp.Fragments.Coupons;
import bk.myapp.Fragments.History;
import bk.myapp.Fragments.Home;
import bk.myapp.Fragments.Settings;
import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layout;
    Home home;
    History history;
    Coupons coupons;
    Settings settings;
    android.app.FragmentTransaction transaction;
    FragmentManager manager;
    Toolbar toolbar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("sp", MODE_PRIVATE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EmergencI");
        layout = (RelativeLayout) findViewById(R.id.content);
        initializeFragments();
        manager = this.getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content, home).commit();
        listenToFirebaseChanges();
    }

    private void listenToFirebaseChanges() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("name");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Person p = dataSnapshot.getValue(Person.class);
                if (!p.name.equals(preferences.getString("name", ""))) {
                    // TODO: 29-10-2017 send notification
                    Toast.makeText(MainActivity.this, "notif", Toast.LENGTH_SHORT).show();
                    Notifs notifs = new Notifs(MainActivity.this);
                    notifs.showNotification();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initializeFragments() {
        home = new Home();
        history = new History();
        coupons = new Coupons();
        settings = new Settings();
    }

    private void performTransaction(Fragment fragToReplace) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content, fragToReplace).commit();
    }

    @Override
    public void onBackPressed() {
        home.backPressed();
    }

    public void sendAlertMessage(String reason) {
        (new Firebase(this)).sendAlertMessage(preferences.getString("name", ""), reason);
    }

    public void hf(View view) {
        performTransaction(home);
    }

    public void hisf(View view) {
        performTransaction(history);
    }

    public void cf(View view) {
        performTransaction(coupons);
    }

    public void setf(View view) {
        performTransaction(settings);
    }
}
