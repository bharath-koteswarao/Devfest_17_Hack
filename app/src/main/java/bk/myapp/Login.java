package bk.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Toolbar toolbar;
    TextView welcome;
    SharedPreferences preferences;
    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.email);
        preferences = getSharedPreferences("sp",MODE_PRIVATE);
        performCheck(preferences.getBoolean("loggedIn", false));
        setUpToolbar();
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/candara.ttf");
        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setTypeface(tf);
    }

    private void performCheck(boolean alreadyLoggedIn) {
        if (alreadyLoggedIn) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void move(View view) {
        String entered = userName.getText().toString();
        if (!entered.equals("")) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("loggedIn", true);
            editor.putString("name", entered);
            editor.apply();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
        }
    }
}
