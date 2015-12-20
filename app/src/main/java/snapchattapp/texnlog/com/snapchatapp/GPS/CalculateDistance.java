package snapchattapp.texnlog.com.snapchatapp.GPS;

import android.location.Location;

/**
 * Created by thomas
 */
public class CalculateDistance {

        // method calculate the distance end returns in meters
        public static float calculate (UserCoordinate coordinate){
            float distance;

            Location mylocation = new Location("current location");
            mylocation.setLatitude(coordinate.getLatitude());
            mylocation.setLongitude(coordinate.getLongitude());

            Location otheruserlocation = new Location("other user");
            otheruserlocation.setLatitude(coordinate.getLatitude());
            otheruserlocation.setLongitude(coordinate.getLongitude());

            distance = mylocation.distanceTo(otheruserlocation);

            return distance;
        }

}
