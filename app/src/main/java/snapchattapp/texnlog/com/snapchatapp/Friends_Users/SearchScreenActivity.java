package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Camera.TestingCameraActivity;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.SearchScreenActivity_SearchQuery_ASYNC;
import snapchattapp.texnlog.com.snapchatapp.R;

public class SearchScreenActivity extends AppCompatActivity {
    private        Button btnGoToCamera;
    private static ListView listview;
    private static Context context;
    private        SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        Log.d("SearchScreenActivity...", "OnCreateFriendsActivity");

        SetUpResources(); //initialize activity resources

        SetUpListeners();  //Set up event listeners for button and searchView

    }

    private void SetUpListeners() {
        btnGoToCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TestingCameraActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });


        searchView.setQueryHint("Enter username to search");
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SearchScreenActivity...QuerySubmit", query);
                new SearchScreenActivity_SearchQuery_ASYNC("",query).execute();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                Log.d("SearchScreenActivity...QueryChange",newText);


                return false;
            }
        });
    }

    private void SetUpResources() {
        btnGoToCamera = (Button) findViewById(R.id.btnSearchScreenCamera);
        listview      = (ListView) findViewById(R.id.listView);
        context       =  getBaseContext();


        searchView = (SearchView) findViewById(R.id.searchView);
    }


    public  static void addListData(final ArrayList<Users> ls,int FOUND_USERS)
    {
        if( FOUND_USERS == 0)
        {
            ArrayList<String> values = new ArrayList<String>();                                                             //
            values.add("No such users found");                                                                         // If no user matches the criteria given
            ArrayAdapter nullAdapter = new ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line,values);  // show "no users message"
            listview.setAdapter(nullAdapter);                                                                          //
        }
        else
        {
            ArrayAdapter<Users> adapter = new CustomListViewAdapter(context, ls, -1);

            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Intent intent = new Intent(context, DetailsScreenActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("data", ls);
                    intent.putExtra("id", position);
                    intent.putExtra("request_code",1);

                    context.startActivity(intent);
                }
            });
        }
    }
}
