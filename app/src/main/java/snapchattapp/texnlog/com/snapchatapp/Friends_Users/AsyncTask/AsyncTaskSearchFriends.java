package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.SearchScreenActivity;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;

/**
 * Created by SoRa1 on 9/12/2015.
 */
public class AsyncTaskSearchFriends extends AsyncTask {
    public static final String GET_USERS_SERVICE_URL = "http://192.168.1.4/android/GetUsers.php"; //Testing at localhost
    // public static final String GET_USERS_SERVICE_URL = "http://projectdb.esy.es/Android/GetUsers.php"; //Working at remote database
    private final String query;
    private String DataToSend="";
    ArrayList<Users> users;

    @Override
    protected Object doInBackground(Object[] objects) {
        try
        {
            DataToSend+= URLEncoder.encode("username", "UTF-8")
                    + "=" + URLEncoder.encode(query, "UTF-8");

            HttpURLConnection connection= WebService.httpRequest(DataToSend, GET_USERS_SERVICE_URL);
            String response=WebService.httpResponse(connection);
            JSONParser parser=new JSONParser();
            JSONArray array= (JSONArray) parser.parse(response);
            users=WebService.JSONtoArrayListData(array);
            Log.d("AsyncTaskSearchFriends", users.toString());

        }
        catch (Exception e){e.printStackTrace();}

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(users!=null)SearchScreenActivity.addListData(users,1);
        else SearchScreenActivity.addListData(null,0);
    }

    public AsyncTaskSearchFriends(String data, String Query)
    {
        DataToSend=data;
        query=Query;
    }
}
