package bharat.sos_tarp;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by bk on 01-04-2018 14:33.
 */

public class LocationHelper {
    Context context;
    LocationManager manager;

    public LocationHelper(Context context) {
        this.context = context;
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public Location getLastKnownLocation() {
        return manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }
}
