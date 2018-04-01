package bharat.sos_tarp;

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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    Toolbar toolbar;
    TextView welcome;
    SharedPreferences preferences;
    EditText userName, passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.userName);
        passWord = (EditText) findViewById(R.id.password);
        preferences = getSharedPreferences("sp", MODE_PRIVATE);
        performCheck(preferences.getBoolean("loggedIn", false));
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/candara.ttf");
        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setTypeface(tf);
        setUpToolbar();
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

    public void createUser(View view) {
        String username = userName.getText().toString();
        String password = passWord.getText().toString();
        if (username.contains("@") || username.contains(".") || username.contains("$") || username.contains("[") || username.contains("]") || username.contains("#") || username.contains("/")) {
            userName.setError("Username can only contain characters a-z and A-Z and _");
            userName.requestFocus();
        } else if (!username.equals("") && !password.equals("")) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("loggedIn", true);
            editor.putString("name", username);
            editor.putString("password", password);
            editor.apply();
            writeUserToDatabase(username, password);
        } else {
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
    }

    private void writeUserToDatabase(String entered, String password) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(entered);
        Person person = new Person(entered, password, "");
        final MyProgressDialog dialog = new MyProgressDialog(this, "", "Creating your account...");
        dialog.show();
        reference.setValue(person, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                dialog.dismiss();
                if (databaseError == null) {
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else Toast.makeText(Login.this, "Try again...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
