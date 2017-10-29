package bk.myapp;

import android.content.Context;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by koteswarao on 29-10-2017.
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
    public void sendAlertMessage(String name,String reason) {
        reference.child("name").setValue(new Person(name,reason));
    }
}
