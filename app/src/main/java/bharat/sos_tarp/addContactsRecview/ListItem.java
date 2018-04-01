package bharat.sos_tarp.addContactsRecview;

/**
 * Created by bk on 29-03-2018 10:50.
 */

public class ListItem {
    boolean switchActivated;
    String name, mobile;

    public ListItem(boolean switchActivated, String name, String mobile) {
        this.switchActivated = switchActivated;
        this.name = name;
        this.mobile = mobile;
    }
}
