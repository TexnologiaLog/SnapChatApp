package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


import snapchattapp.texnlog.com.snapchatapp.Friends_Users.DetailsScreenActivity;

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
    private Button btnDone;
    private ListView listView;
    private Context context;
    private SendingData uploadData=SendingData.getInstance();
    private static final String TABLE_FRIENDS="friends";
    private ArrayList<Users> friends=new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_panel);
        context=getBaseContext();


        SQliteHandlerClass sQliteHandlerClass=new SQliteHandlerClass(context);
        UserLocalStore userLocalStore=new UserLocalStore(context);
        Users user=userLocalStore.getLoggedInUser();
        friends=sQliteHandlerClass.getAllUsers(TABLE_FRIENDS+user.getC_username());
        final ArrayList<String> usernames=new ArrayList();
        for(Users c : friends ){
            usernames.add(c.getC_username());
        }


        listView=(ListView)findViewById(R.id.friend_panel);
        btnDone=(Button) findViewById(R.id.doneButton);

        ArrayAdapter adapter =new ArrayAdapter(context,R.layout.select_dialog_multichoice_material,usernames);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
                for (int i = 0; i < sparseBooleanArray.size(); i++) {
                    if (sparseBooleanArray.get(sparseBooleanArray.keyAt(i))) {
                        Log.d("FriendsPanel", (String) listView.getItemAtPosition(sparseBooleanArray.keyAt(i)));

                        uploadData.getSenderId().add((String) listView.getItemAtPosition(sparseBooleanArray.keyAt(i)));

                    }
                }

                Intent intent =new Intent(FriendsPanel.this,Upload.class);
                intent.putExtra("image",uploadData.getFirstImage());
                startActivity(intent);
            }
        });




    }

}