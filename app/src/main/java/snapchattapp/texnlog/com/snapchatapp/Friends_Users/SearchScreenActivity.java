package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
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

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.AsyncTaskSearchFriends;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

public class SearchScreenActivity extends AppCompatActivity {
    private Button btnBack;
    private static ListView listview;
    private static Context context;
    private SearchView searchView;
    private String DataToSend="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        Log.d("DO", "OnCreateFriendsActivity");

        btnBack=(Button) findViewById(R.id.btnSearchScreenBack);
        listview=(ListView) findViewById(R.id.listView);
        context=getBaseContext();


        searchView = (SearchView) findViewById(R.id.searchView);






        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FriendsScreenActivity.class));
            }
        });



        searchView.setQueryHint("Enter username to search");
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SearchScreenActivity...QuerySubmit", query);
                new AsyncTaskSearchFriends("",query).execute();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                Log.d("SearchScreenActivity...QueryChange",newText);


                DataToSend=null;
                return false;
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
        return true;
    }


    public  static void addListData(final ArrayList<Users> ls,int FOUND)
    {
        if(FOUND==0) {
            ArrayList<String> values = new ArrayList<String>();
            values.add("No such users found");
            ArrayAdapter nullAdapter = new ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line,values);
            listview.setAdapter(nullAdapter);

        }
        else {


            ArrayAdapter<Users> adapter = new ListViewAdapter(context, ls, -1);

            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Intent intent = new Intent(context, DetailsScreenActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("data", ls);
                    intent.putExtra("id", position);

                    context.startActivity(intent);
                }
            });
        }
    }
}
