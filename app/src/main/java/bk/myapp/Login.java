package bk.myapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    Toolbar toolbar;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpToolbar();
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/candara.ttf");
        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setTypeface(tf);
    }

    void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void move(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}
