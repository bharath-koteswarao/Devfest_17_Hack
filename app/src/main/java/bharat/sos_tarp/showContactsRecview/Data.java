package bharat.sos_tarp.showContactsRecview;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bk on 27-03-2018 16:58.
 */

public class Data {
    Context context;

    public Data(Context context) {
        this.context = context;
    }

    public List<ListItem> getList() {
        String contactString = context.getSharedPreferences("truConts", Context.MODE_PRIVATE).getString("contacts", "");
        String[] conts = contactString.split("%%");
//        Toast.makeText(context, contactString, Toast.LENGTH_SHORT).show();
        List<ListItem> list = new ArrayList<>();
        for (String contact : conts) {
            String[] broken = contact.split(",");
            try {
                list.add(new ListItem(broken[0], broken[1]));
            } catch (Exception ignored) {
            }
        }
        return list;
    }
}
