package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.simple.JSONArray;

import java.util.ArrayList;

/**
 * Created by SoRa1 on 27/11/2015.
 */
public class GetDataFromDatabaseAsyncTask extends AsyncTask
{
    //private static final String GetUsersServiceURL ="http://192.168.1.4/android/ReadJSON.php";
    //private static final String GetFriendsServiceURL ="http://192.168.1.4/android/GetFriends.php";
    private static final String GetUsersServiceURL ="http://projectdb.esy.es/Android/ReadJSON.php";
    private static final String GetFriendsServiceURL ="http://projectdb.esy.es/Android/GetFriends.php";
    private static final String TABLE_FRIENDS ="user";
    public static final String TABLE_USERS = "friends";
    private ProgressDialog dialog;
    private Context context;
    public static JSONArray jSONarrayUsersFromDatabase=null,jSONarrayFriendsFromDatabase=null;
    public static ArrayList<Users> usersArrayListFromJSON=new ArrayList<Users>();
    public static ArrayList<Users> friendsArrayListFromJSON=new ArrayList<Users>();
    private static WebService webService;
    private String table;


    public GetDataFromDatabaseAsyncTask(Context applicationContext, String tab)
    {
        context=applicationContext;
        webService=new WebService(context);
        table=tab;
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
            jSONarrayUsersFromDatabase=webService.getDataFromRemoteDatabase(GetUsersServiceURL);
            jSONarrayFriendsFromDatabase=webService.getFriendsListFromRemoteDatabase(GetFriendsServiceURL);
        }
        catch (Exception e){e.getMessage();e.printStackTrace();}



        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d("Debug", "onPostExecute");

        usersArrayListFromJSON=webService.JSONtoArrayListData(jSONarrayUsersFromDatabase);

        friendsArrayListFromJSON=webService.JSONtoArrayListData(jSONarrayFriendsFromDatabase);


        webService.addDataToLocalDatabase(context, usersArrayListFromJSON, TABLE_USERS);

        webService.addDataToLocalDatabase(context, friendsArrayListFromJSON, TABLE_FRIENDS);


        FillList(table);



        dialog.dismiss();
    }

    private void FillList(String table)
    {
        if(table.equals(TABLE_USERS))UsersScreenActivity.addListData(webService.getUsersFromLocalDatabase(context,table));
        else FriendsScreenActivity.addListData(webService.getUsersFromLocalDatabase(context,table));
    }



}

