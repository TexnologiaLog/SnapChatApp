package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.simple.JSONArray;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.FriendsScreenActivity;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

/**
 * Created by SoRa1 on 27/11/2015.
 */
public class GetDataFromDatabaseAsyncTask extends AsyncTask
{
    private static final String GetUsersServiceURL ="http://192.168.1.4/android/ReadJSON.php";
    private static final String GetFriendsServiceURL ="http://192.168.1.4/android/GetFriends.php";
    //private static final String GetUsersServiceURL ="http://192.168.1.4/android/GetUsers.php";
    //private static final String GetUsersServiceURL ="http://projectdb.esy.es/Android/ReadJSON.php";
    //private static final String GetFriendsServiceURL ="http://projectdb.esy.es/Android/GetFriends.php";
    private static String userID;
    private ProgressDialog dialog;
    private static Context context;
    public static JSONArray jSONarrayFriendsFromDatabase=null;
    public static ArrayList<Users> friendsArrayListFromJSON=new ArrayList<Users>();
    private static WebService webService;







    public GetDataFromDatabaseAsyncTask(Context applicationContext,String UserID)
    {
        context=applicationContext;
        webService=new WebService(context);
        userID=UserID;
        Log.d("Debug","onConstructor");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        webService=new WebService(context);

        dialog=new ProgressDialog(context);
        dialog.setTitle("Please wait....");
        dialog.setMessage("Contacting Server");
        dialog.show();
        Log.d("Debug", "onPreExecute");
    }

    @Override
    protected Object doInBackground(Object[] params)
    {
        try
        {
            jSONarrayFriendsFromDatabase=webService.getFriendsListFromRemoteDatabase(GetFriendsServiceURL, userID);

            Log.d("DEBUG",jSONarrayFriendsFromDatabase.toString());
        }
        catch (Exception e){e.getMessage();e.printStackTrace();}



        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d("Debug", "onPostExecute");

        friendsArrayListFromJSON=webService.JSONtoArrayListData(jSONarrayFriendsFromDatabase);

        new SaveImagesLocal_ASYNC(friendsArrayListFromJSON,context).execute();












        dialog.dismiss();
    }





}

