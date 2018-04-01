package bharat.sos_tarp;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import bharat.sos_tarp.Fragments.Coupons;
import bharat.sos_tarp.Fragments.History;
import bharat.sos_tarp.Fragments.Home;
import bharat.sos_tarp.Fragments.Settings;
import bharat.sos_tarp.showContactsRecview.ViewContacts;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.ArrayList;
import java.util.List;

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
    FlowingDrawer drawer;
    Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config = new Config(this);
        if (!config.isNetworkAvailable()) Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
        preferences = getSharedPreferences("sp", MODE_PRIVATE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        getSupportActionBar().setTitle("Emergenci");
        layout = (RelativeLayout) findViewById(R.id.content);
        initializeFragments();
        manager = this.getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content, home).commit();
        startService(new Intent(this, FirebaseListeneingService.class));
        startService(new Intent(getApplicationContext(), LockService.class));
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
        drawer.closeMenu();
    }

    @Override
    public void onBackPressed() {
        home.backPressed();
    }

    public void sendAlertMessage(String reason) {
        if (config.isNetworkAvailable()) {
            (new Firebase(this)).sendAlertMessage(preferences.getString("name", ""), reason);
            startActivity(new Intent(this, AlertSuccessActivity.class));
        } else {
            sendMessageToTrustedContacts(reason);
            onBackPressed();
        }
    }

    private void sendMessageToTrustedContacts(String reason) {
        String contactString = getSharedPreferences("truConts", Context.MODE_PRIVATE).getString("contacts", "");
        String[] conts = contactString.split("%%");
        List<String> mobileNumbers = new ArrayList<>();
        for (String contact : conts) {
            try {
                mobileNumbers.add(contact.split(",")[1]);
            } catch (Exception ignored) {
            }
        }
        for (String mobile : mobileNumbers) {
            sendSms(mobile, "I'm in trouble. Reason is " + reason);
        }
        Toast.makeText(this, "SMS Sent", Toast.LENGTH_SHORT).show();
    }

    void sendSms(String mobile, String message) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            SmsManager sms = SmsManager.getDefault();
            PendingIntent sentPI;
            String SENT = "SMS_SENT";
            sentPI = PendingIntent.getBroadcast(this, 0,new Intent(SENT), 0);
            sms.sendTextMessage(mobile, null, message, sentPI, null);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.SEND_SMS}, 10);
            }
        }

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

    public void emf(View view) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, NotificationReceiver.class));
        } else {
            //ask for permission if user didnot given
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }
    }

    public void startTrustedContacts(View view) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, ViewContacts.class));
        } else {
            //ask for permission if user didnot given
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS}, 0);
            }
        }
    }

    public void logout(View view) {
        getSharedPreferences("sp", MODE_PRIVATE).edit().clear().apply();
        getSharedPreferences("truConts", MODE_PRIVATE).edit().clear().apply();
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Login.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(this, ViewContacts.class));
                } else {
                    Toast.makeText(this, "We need contacts permission", Toast.LENGTH_SHORT).show();
                }
            }
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(this, NotificationReceiver.class));
                } else {
                    Toast.makeText(this, "We need Location permission", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
