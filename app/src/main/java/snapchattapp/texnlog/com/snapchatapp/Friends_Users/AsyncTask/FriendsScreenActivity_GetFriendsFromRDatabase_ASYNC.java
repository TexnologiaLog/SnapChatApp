package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.simple.JSONArray;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.FriendsScreenActivity;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;

/**
 * Created by SoRa1 on 27/11/2015.
 */
public class FriendsScreenActivity_GetFriendsFromRDatabase_ASYNC extends AsyncTask
{
    //private static final String GetFriendsServiceURL ="http://192.168.1.4/android/GetFriends.php";        //Local location of web service

    private static final String GetFriendsServiceURL ="http://projectdb.esy.es/Android/GetFriends.php";     //Remote location of web service

    private static String userID;
    private final String TABLE_FRIENDS;
    private ProgressDialog dialog;
    private static Context context;
    public static JSONArray jSONarrayFriendsFromDatabase=null;
    public static ArrayList<Users> friendsArrayListFromJSON=new ArrayList<Users>();
    private static WebService webService;
    private static String ServerResponse = "null";

    public FriendsScreenActivity_GetFriendsFromRDatabase_ASYNC(Context applicationContext, String UserID)
    {
        context=applicationContext;
        webService=new WebService(context);
        TABLE_FRIENDS = webService.sQliteHandlerClass.TABLE_FRIENDS;
        userID=UserID;
        Log.d("GetFriendsFromRDatabase....","onConstructor");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog=new ProgressDialog(context);
        dialog.setTitle("Please wait....");
        dialog.setMessage("Contacting Server");
        dialog.show();



        Log.d("GetFriendsFromRDatabase....JSONArray Result", "onPreExecute");
    }

    @Override
    protected Object doInBackground(Object[] params)
    {
        try
        {
            jSONarrayFriendsFromDatabase=webService.getFriendsListFromRemoteDatabase(GetFriendsServiceURL, userID); //Get JSONArray  response from server
            Log.d("GetFriendsFromRDatabase....JSONArray Result",jSONarrayFriendsFromDatabase.toString());
        }
        catch (Exception e){e.getMessage();e.printStackTrace();}
        return null;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
        Log.d("GetFriendsFromRDatabase....JSONArray Result", "onPostExecute");

        try
        {
            FriendsScreenActivity.addListData(webService.getUsersFromLocalDatabase(TABLE_FRIENDS));                                         //Fill listView at Friends Screen Activity
        }
        catch (NullPointerException e){e.printStackTrace();}

        friendsArrayListFromJSON=webService.JSONtoArrayListData(jSONarrayFriendsFromDatabase);              //Convert JSONArray to ArrayList<Users>
        new FriendsScreenActivity_StoreImagesLocally_ASYNC(friendsArrayListFromJSON,context).execute();     //Store Friends Personal images locally
        dialog.dismiss();
    }

}

