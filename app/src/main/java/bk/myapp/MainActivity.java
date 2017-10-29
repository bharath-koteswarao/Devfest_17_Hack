package bk.myapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import bk.myapp.Fragments.Coupons;
import bk.myapp.Fragments.History;
import bk.myapp.Fragments.Home;
import bk.myapp.Fragments.Settings;

public class MainActivity extends Activity {
    RelativeLayout layout;
    Home home;
    History history;
    Coupons coupons;
    Settings settings;
    android.app.FragmentTransaction transaction;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.content);
        initializeFragments();
        manager = this.getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content, home).commit();
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
