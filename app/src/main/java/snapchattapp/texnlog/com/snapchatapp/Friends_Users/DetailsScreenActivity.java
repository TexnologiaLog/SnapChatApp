package snapchattapp.texnlog.com.snapchatapp.Friends_Users;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.R;

/**
 * Created by SoRa1 on 2/12/2015.
 */


public class DetailsScreenActivity extends Activity
{
    public static final String TABLE_FRIENDS = "user";
    public static final String TABLE_USERS = "friends";
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;

    Button btnAddFriend;
    Button btnRemoveFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Intent intent=getIntent();
        savedInstanceState= intent.getExtras();
        ArrayList<Users> usersArrayList= (ArrayList<Users>) savedInstanceState.getSerializable("data");
        int position=savedInstanceState.getInt("id");

        textView= (TextView) findViewById(R.id.txtData);
        textView1= (TextView) findViewById(R.id.txtFirstName);
        textView2= (TextView) findViewById(R.id.txtLasstName);
        textView3= (TextView) findViewById(R.id.txtAge);

        btnAddFriend=(Button) findViewById(R.id.btnAddFriend);
        btnRemoveFriend=(Button) findViewById(R.id.btnRemoveFriend);


        textView.setText(usersArrayList.get(position).getC_username());
        textView1.setText(usersArrayList.get(position).getC_name());
        textView2.setText(usersArrayList.get(position).getC_id());
        textView3.setText(usersArrayList.get(position).getC_age());


        setUpButtonListeners();
    }

    private void setUpButtonListeners()
    {
        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= (String) textView.getText();
                if(WebService.checkForInternet(getApplicationContext()))
                {
                    if (checkIfExists(user, TABLE_FRIENDS))
                    {
                        Toast.makeText(DetailsScreenActivity.this, "Friend Already Exists", Toast.LENGTH_SHORT).show();
                        Log.d("DATABASE", WebService.getUsersFromLocalDatabase(getApplicationContext(), "user").toString());
                    }
                    else
                    {
                        if (DoActionToFriendFromLocalAndRemoteDatabase(user, "add", TABLE_FRIENDS))
                            Toast.makeText(DetailsScreenActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();

                    }
                    Log.d("DATABASE", WebService.getUsersFromLocalDatabase(getApplicationContext(), TABLE_FRIENDS).toString());
                }
            }
        });

        btnRemoveFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String user=(String) textView.getText();
                if(WebService.checkForInternet(getApplicationContext()))
                {
                    if (checkIfExists(user, TABLE_FRIENDS))
                    {
                        if (DoActionToFriendFromLocalAndRemoteDatabase(user, "delete", TABLE_FRIENDS))
                        {
                            Toast.makeText(getApplicationContext(), "Friend Deleted", Toast.LENGTH_SHORT).show();
                            //Log.d("DATABASE", WebService.getUsersFromLocalDatabase(getApplicationContext(), "users").toString());
                        }
                    } else
                        Toast.makeText(getApplicationContext(), "This user isn't your friend", Toast.LENGTH_SHORT).show();
                    //Log.d("DATABASE",WebService.getUsersFromLocalDatabase(getApplicationContext(),"users").toString());
                }
            }
        });


    }

    private boolean checkIfExists(String user,String table)
    {
        Users tmp=null;
        tmp=WebService.getUser(user,table);
        if(tmp!=null){return true;}
        else return false;
    }
    private boolean DoActionToFriendFromLocalAndRemoteDatabase(String user,String action,String table)
    {
       Users tmp=WebService.getUser(user,table);
       switch (action)
       {
          case "delete": Log.d("Button", "delete");
              if(tmp!=null)
              {
                  WebService.removeUser(user);
                  new DeleteFriendFromRemoteAsyncTask(tmp.getC_id()).execute();
                  return true;
              }
               break;
           case "add": Log.d("Button", "add");

               Users tmpUser=WebService.getUser(user, TABLE_USERS);
               ArrayList<Users> tempList=new ArrayList<Users>();
               tempList.add(tmpUser);
               WebService.addDataToLocalDatabase(getApplicationContext(), tempList, table);
               new AddFriendToRemoteAsyncTask(tmpUser.getC_id()).execute();

               return true;
       }
       return false;
    }
}
