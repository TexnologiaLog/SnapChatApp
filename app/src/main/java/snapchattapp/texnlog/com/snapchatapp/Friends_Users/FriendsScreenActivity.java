package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Camera.TestingCameraActivity;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.FriendsScreenActivity_GetFriendsFromRDatabase_ASYNC;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

public class FriendsScreenActivity extends AppCompatActivity {
    public  static  String USER_ID =null;
    private static ListView listview;
    private static Context context;
    private Button btnSearch, btnCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_screen);
        Log.d("DO", "OnCreateFriendsActivity");

        SetUpResources();                                     //Initialize activity resources
        SetUpButtonListeners();                               //set button listeners

    }

    private void SetUpButtonListeners() {
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TestingCameraActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,SearchScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    private void SetUpResources() {
        btnCamera = (Button) findViewById(R.id.btnFriendsScreenCamera);
        btnSearch = (Button) findViewById(R.id.btnFriendsScreenSearch);
        listview = (ListView) findViewById(R.id.listFriendsScreen);
        context = getBaseContext();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(updateUI());
    }

    public boolean updateUI()
    {
        UserLocalStore localStore=new UserLocalStore(context);
        Users user=localStore.getLoggedInUser();
        USER_ID=user.getC_id();
        new FriendsScreenActivity_GetFriendsFromRDatabase_ASYNC(FriendsScreenActivity.this,USER_ID).execute();
        return true;
    }


    public  static void addListData(final ArrayList<Users> ls)
    {
        ArrayAdapter<Users> adapter=new CustomListViewAdapter(context,ls,0);

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
