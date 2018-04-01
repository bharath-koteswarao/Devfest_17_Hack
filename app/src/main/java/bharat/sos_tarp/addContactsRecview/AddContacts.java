package bharat.sos_tarp.addContactsRecview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import bharat.sos_tarp.MainActivity;
import bharat.sos_tarp.R;

import java.util.List;

public class AddContacts extends AppCompatActivity {

    RecyclerView addContacts;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        addContacts = (RecyclerView) findViewById(R.id.addContactsRecview);
        Data data = new Data(this);
        adapter = new Adapter(this, data.getList());
        addContacts.setLayoutManager(new LinearLayoutManager(this));
        addContacts.setAdapter(adapter);
        setUpToolbar();
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.done) {
            addTrustedContacts();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_contacts, menu);
        return true;
    }

    private void addTrustedContacts() {
        List<ListItem> list = adapter.getSelectedItems();
        String build = "";
        SharedPreferences preferences = getSharedPreferences("truConts", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for (ListItem li : list) {
            if (li.switchActivated) {
                build += li.name + "," + li.mobile + "%%";
            }
        }
        editor.putString("contacts", build).apply();
        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}
