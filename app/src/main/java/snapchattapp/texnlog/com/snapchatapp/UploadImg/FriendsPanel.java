package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.GetDataFromDatabaseAsyncTask;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.DetailsScreenActivity;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.ListViewAdapter;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.SQliteHandlerClass;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.SearchScreenActivity;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.UserProfileScreen;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.User;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

/**
 * Created by User on 17/12/2015.
 */
public class FriendsPanel extends AppCompatActivity {
    public  static  String USER_ID =null; /// Change it after login fix
    private static ListView listview;
    private static Context context;
    private static SQliteHandlerClass sQliteHandlerClass;
    private UserLocalStore user ;
    private WebService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_panel);
        Log.d("panos", "FriendsPanel");
//        sQliteHandlerClass=new SQliteHandlerClass(get());
        user=new UserLocalStore(getApplicationContext());
        Users string=user.getLoggedInUser();
        service=new WebService(context);
        listview = (ListView) findViewById(R.id.listFriendsScreen);
        context = getBaseContext();
        ArrayList<Users> friends =new ArrayList<>();
        friends=service.getUsersFromLocalDatabase(context, "friends"+string.getC_username());

        ArrayAdapter  adapter=new ArrayAdapter(context,R.layout.select_dialog_multichoice_material);
        for(Users c : friends){
            adapter.add(c.getC_username());
        }
        listview.setAdapter(adapter);

    }








    public  static void addListData(final ArrayList<Users> ls) {


    }

}
