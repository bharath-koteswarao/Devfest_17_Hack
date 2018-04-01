package bharat.sos_tarp;

import android.app.ProgressDialog;
import android.content.Context;



public class MyProgressDialog extends ProgressDialog {
    String title, message;

    public MyProgressDialog(Context context, String title, String message) {
        super(context);
        if (!title.equals("")) this.setTitle(title);
        this.setMessage(message);
        this.setCancelable(false);
        this.setIndeterminate(false);
    }
}