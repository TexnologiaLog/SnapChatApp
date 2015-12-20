package snapchattapp.texnlog.com.snapchatapp.GPS;


import android.content.Context;



/**
 * Created by thomas
 */


public class GPSTrackerController {

    public void getMyLocation(Context co){
        GPSTracker gpst = new GPSTracker(co);

        if(gpst.cangetLocation){
            double latitude = gpst.getLatitude();
            double longitude = gpst.getLongitude();


        }

    }

}
