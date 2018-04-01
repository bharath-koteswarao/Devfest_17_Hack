package bharat.sos_tarp.showContactsRecview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import bharat.sos_tarp.R;
import bharat.sos_tarp.addContactsRecview.AddContacts;

public class ViewContacts extends AppCompatActivity {

    RecyclerView showContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_contacts);
        showContacts = (RecyclerView) findViewById(R.id.showContactsRecview);
        setUpToolbar();
        Data data = new Data(this);
        Adapter adapter = new Adapter(this, data.getList());
        showContacts.setAdapter(adapter);
        showContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            startActivity(new Intent(this, AddContacts.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view_contacts, menu);
        return true;
    }
}
