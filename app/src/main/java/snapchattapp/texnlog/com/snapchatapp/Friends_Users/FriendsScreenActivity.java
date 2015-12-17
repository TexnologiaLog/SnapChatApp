package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.GetDataFromDatabaseAsyncTask;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

public class FriendsScreenActivity extends AppCompatActivity {
    public  static  String USER_ID =null; /// Change it after login fix
    private static ListView listview;
    private static Context context;
    private Button btnSearch,btnProfile;
    String SP_NAME ="userDetails";
    SharedPreferences userLocalDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_screen);
        Log.d("DO", "OnCreateFriendsActivity");

        btnProfile = (Button) findViewById(R.id.btnFriendsScreenProfile);
        btnSearch = (Button) findViewById(R.id.btnFriendsScreenSearch);
        listview = (ListView) findViewById(R.id.listFriendsScreen);
        context = getBaseContext();







        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),UserProfileScreen.class));
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,SearchScreenActivity.class));
            }
        });

    }





    @Override
    protected void onResume() {
        super.onResume();
        if(updateUI());
    }

    public boolean updateUI()
    {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
        UserLocalStore localStore=new UserLocalStore(context);
        Users user=localStore.getLoggedInUser();
        USER_ID=user.getC_id();
        new GetDataFromDatabaseAsyncTask(FriendsScreenActivity.this,USER_ID).execute();
        return true;
    }


    public  static void addListData(final ArrayList<Users> ls)
    {


        ArrayAdapter<Users> adapter=new ListViewAdapter(context,ls,0);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(context, DetailsScreenActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("data", ls);
                intent.putExtra("id", position);
                intent.putExtra("request_code",0);

                context.startActivity(intent);
            }
        });
    }
}
