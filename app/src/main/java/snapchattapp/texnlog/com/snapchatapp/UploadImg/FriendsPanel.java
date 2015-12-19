package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.SQliteHandlerClass;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

/**
 * Created by User on 17/12/2015.
 */
public class FriendsPanel extends AppCompatActivity {
    private static ListView listview;
    public static  String TABLE_FRIENDS="balls";
    private static Context context;
    private static SQliteHandlerClass sQliteHandlerClass;
    private UserLocalStore user ;
    private WebService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_panel);
        sQliteHandlerClass=new SQliteHandlerClass(getApplicationContext());
        TABLE_FRIENDS=sQliteHandlerClass.TABLE_FRIENDS;

        Log.d("panos", TABLE_FRIENDS);

        ArrayList<Users> user=sQliteHandlerClass.getAllUsers(TABLE_FRIENDS);


//        Users tmp=null;
//        tmp=WebService.getUser("Phonetest",TABLE_FRIENDS);
//        listview = (ListView) findViewById(R.id.listFriendsScreen);
//        context = getBaseContext();
//
//
//
//        ArrayList<Users> friends =new ArrayList<>();
//
//        ArrayAdapter  adapter=new ArrayAdapter(context,R.layout.select_dialog_multichoice_material);
//        for(Users c : friends){
//            adapter.add(c.getC_username());
//        }
//        listview.setAdapter(adapter);

    }








    public  static void addListData(final ArrayList<Users> ls) {


    }

}
