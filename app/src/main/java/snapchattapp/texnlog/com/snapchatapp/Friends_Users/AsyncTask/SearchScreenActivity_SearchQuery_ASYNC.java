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
public class SearchScreenActivity_SearchQuery_ASYNC extends AsyncTask {
    //private static final String GET_USERS_SERVICE_URL = "http://192.168.1.4/android/GetUsers.php";    //Local location of Web Service

    private static final String GET_USERS_SERVICE_URL = "http://projectdb.esy.es/Android/GetUsers.php"; //Remote location of Web Service

    private static final int FOUND_USERS_FLAG = 1;
    private static final int NOT_FOUND_USERS_FLAG = 0;

    private  String query;
    private String DataToSend="";
    private ArrayList<Users> users=null;
    private String ServerResponse="null";


    public SearchScreenActivity_SearchQuery_ASYNC(String data, String Query)
    {
        DataToSend=data;
        query=Query;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try
        {
            DataToSend+= URLEncoder.encode("username", "UTF-8")
                    + "=" + URLEncoder.encode(query, "UTF-8");

            HttpURLConnection connection= WebService.httpRequest(DataToSend, GET_USERS_SERVICE_URL);
            ServerResponse = WebService.httpResponse(connection);
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(ServerResponse);
            users=WebService.JSONtoArrayListData(array);
            Log.d("SearchScreenActivity_SearchQuery_ASYNC...UsersFound", users.toString());

        }
        catch (Exception e){e.printStackTrace();}

        return null;
    }


    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
        if(users.isEmpty()) {SearchScreenActivity.addListData(null, NOT_FOUND_USERS_FLAG); Log.d("SearchScreen....ASYNC","No User Found");}
        else {SearchScreenActivity.addListData(users, FOUND_USERS_FLAG); Log.d("SearchScreen....ASYNC", "User Found");}
    }


}
