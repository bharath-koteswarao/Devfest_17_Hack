package bk.myapp.addContactsRecview;

import android.content.Context;
import com.github.tamir7.contacts.Contact;
import com.github.tamir7.contacts.Contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bk on 29-03-2018 10:50.
 */

public class Data {
    Context context;

    Data(Context context) {
        this.context = context;
    }

    List<ListItem> getList() {
        Contacts.initialize(context);
        List<ListItem> list = new ArrayList<>();
        List<Contact> contacts = Contacts.getQuery().find();
        for (Contact contact : contacts) {
            ListItem li = new ListItem(
                    false,
                    contact.getDisplayName(),
                    contact.getPhoneNumbers().size() == 0 ? "" : contact.getPhoneNumbers().get(0).getNumber()
            );
            list.add(li);
        }
        return list;
    }
}
