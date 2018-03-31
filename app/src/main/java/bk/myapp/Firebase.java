package bk.myapp;

import android.content.Context;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by koteswarao
 * ${CLASS}
 */

public class Firebase {
    FirebaseDatabase database;
    DatabaseReference reference;
    Context cont;

    public Firebase(Context cont) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        this.cont = cont;
    }

    public void sendAlertMessage(String name, String reason) {
        reference.child("name").setValue(new Person(name, cont.getSharedPreferences("sp", Context.MODE_PRIVATE).getString("password", ""), reason));
    }
}
