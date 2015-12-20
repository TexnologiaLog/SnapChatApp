package snapchattapp.texnlog.com.snapchatapp.GPS;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;


/**
 * Created by thomas
 */
public class GPSTracker extends Service implements LocationListener{
    private static int minimumDistanceChangeForUpdate = 5; // 5 meters
    private static int minimumTimeForUpdate = 1000 * 60; // one minute

    private LocationManager locationManager;
    private Context context;
    boolean isGPSEnabled = false;
    boolean cangetLocation = false;
    Location location;
    double latitude;
    double longitude;

    public GPSTracker(Context co) {
        this.context = co;
        getLocation();
    }

    public Location getLocation(){
        try{
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            this.cangetLocation = true;

            if(isGPSEnabled){
                if(location == null){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minimumTimeForUpdate,minimumDistanceChangeForUpdate,this);
                    if(locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if(location !=null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return location;
    }

    public void stopGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }
        return longitude;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
