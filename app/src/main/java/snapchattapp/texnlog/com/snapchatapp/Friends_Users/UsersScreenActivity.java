package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.R;

public class UsersScreenActivity extends AppCompatActivity {
    public static final String TABLE_TO_FILL = "friends";
    private Button btnGetData;
    private FragmentManager fragmentManager;
    private static ListView listview;
    private static Context context;
    private Button btnGoToFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_screen);
        Log.d("DO", "OnCreateFriendsActivity");

        btnGetData=(Button) findViewById(R.id.btnGetData);
        listview=(ListView) findViewById(R.id.listView);
        context=getBaseContext();

        btnGoToFriends=(Button) findViewById(R.id.btnFriends);


        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetDataFromDatabaseAsyncTask(UsersScreenActivity.this, TABLE_TO_FILL).execute();
            }
        });
        btnGoToFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(UsersScreenActivity.this,FriendsScreenActivity.class));
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
        new GetDataFromDatabaseAsyncTask(UsersScreenActivity.this, TABLE_TO_FILL).execute();
        return true;
    }


    public  static void addListData(final ArrayList<Users> ls)
    {

        ArrayList<String> values=new ArrayList<String>();
        for(int i=0;i<ls.size();i++) values.add(ls.get(i).getC_name());

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,values);

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
