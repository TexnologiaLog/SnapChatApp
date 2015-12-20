package snapchattapp.texnlog.com.snapchatapp.GPS;

/**
 * Created by thomas
 */
public class UserCoordinate {
    String username;
    double latitude;
    double longitude;


    public double getLatitude(){
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
