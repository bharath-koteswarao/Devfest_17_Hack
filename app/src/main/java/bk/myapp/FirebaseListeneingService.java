package bk.myapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.firebase.database.*;

/**
 * Created by bk on 01-04-2018 14:09.
 */

public class FirebaseListeneingService extends Service {


    public FirebaseListeneingService() {

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        listenToFirebaseChanges();
        return START_STICKY;
    }

    private void listenToFirebaseChanges() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                dataSnapshot.getValue();
                Log.d("TAG", dataSnapshot.getValue().toString());
                Person p = dataSnapshot.getValue(Person.class);
                if (p != null) {
                    if (!p.name.equals(getSharedPreferences("sp", MODE_PRIVATE).getString("name", ""))) {
                        Notifs notifs = new Notifs(FirebaseListeneingService.this);
                        notifs.showNotification(p.reason);
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
