package bharat.sos_tarp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ScreenReceiver extends BroadcastReceiver {

    public static boolean wasScreenOn = true;
    Context context;
    Config config;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        this.context = context;
        config = new Config(context);
        Log.e("LOB", "onReceive");
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            wasScreenOn = false;
            Log.e("LOB", "wasScreenOn" + wasScreenOn);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            wasScreenOn = true;
        } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            if (config.isNetworkAvailable()) {
                (new Firebase(context)).sendAlertMessage(context.getSharedPreferences("sp", MODE_PRIVATE).getString("name", ""), "Critical Emergency" + getSpaces());
                Intent intent2 = new Intent(context, AlertSuccessActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            } else {
                sendMessageToTrustedContacts("Critical Emergency");
            }
        }
    }

    private String getSpaces() {
        String space = "";
        int s = (new Date()).getSeconds() % 10;
        for (int i = 0; i < s; i++) {
            space += " ";
        }
        return space;
    }

    private void sendMessageToTrustedContacts(String reason) {
        String contactString = context.getSharedPreferences("truConts", Context.MODE_PRIVATE).getString("contacts", "");
        String[] conts = contactString.split("%%");
        List<String> mobileNumbers = new ArrayList<>();
        for (String contact : conts) {
            try {
                mobileNumbers.add(contact.split(",")[1]);
            } catch (Exception ignored) {
            }
        }
        for (String mobile : mobileNumbers) {
            sendSmsMsgFnc(mobile, "I'm in trouble. Reason is " + reason);
        }
    }

    void sendSmsMsgFnc(String mblNumVar, String smsMsgVar) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            try {
                SmsManager smsMgrVar = SmsManager.getDefault();
                smsMgrVar.sendTextMessage(mblNumVar, null, smsMsgVar, null, null);
                Toast.makeText(context, "Message Sent",
                        Toast.LENGTH_LONG).show();
            } catch (Exception ErrVar) {
                Toast.makeText(context, ErrVar.getMessage(),
                        Toast.LENGTH_LONG).show();
                ErrVar.printStackTrace();
            }
        }


    }
}