package snapchattapp.texnlog.com.snapchatapp.Friends_Users;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.AddFriendToRemoteAsyncTask;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.DeleteFriendFromRemoteAsyncTask;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.DetailsScreenActivity_DownloadImage_ASYNC;
import snapchattapp.texnlog.com.snapchatapp.R;

/**
 * Created by SoRa1 on 2/12/2015.
 */


public class DetailsScreenActivity extends Activity
{
    public static final String TABLE_FRIENDS = SQliteHandlerClass.TABLE_FRIENDS;
    private final int FRIEND_PHOTO_REQUEST=0;
    private final int NOT_FRIEND_PHOTO_REQUEST=1;
    private TextView txtUsername,txtFullName,txtUserID,txtAge;
    private Button btnAddFriend,btnRemoveFriend;
    private ArrayList<Users> usersList;
    private ImageView imageView;
    private int requestServiceCode,currentUserID;
    private WebService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        service=new WebService(getApplicationContext());

        Intent intent=getIntent();                                                     //
        savedInstanceState= intent.getExtras();                                        //
        usersList= (ArrayList<Users>) savedInstanceState.getSerializable("data");      //Get values from intent
        currentUserID=savedInstanceState.getInt("id");                                 //
        requestServiceCode=savedInstanceState.getInt("request_code");                  //


        SetUpResources();                                                              // Initialize txt fields and imageview
        SetValues(usersList,currentUserID,requestServiceCode);                         // set values to fields
        setUpButtonListeners();                                                        // set button listeners
    }

    private void SetValues(ArrayList<Users> usersArrayList,int position,int requestCode)
    {
        txtUsername.setText(usersArrayList.get(position).getC_username());
        txtFullName.setText(usersArrayList.get(position).getC_name());
        txtUserID.setText(usersArrayList.get(position).getC_id());
        txtAge.setText(usersArrayList.get(position).getC_age());

        if(requestCode==FRIEND_PHOTO_REQUEST)
        {
            try
            {
                FileInputStream fis = openFileInput(usersArrayList.get(position).getC_username());
                Bitmap bit = BitmapFactory.decodeStream(fis);
                imageView.setImageBitmap(bit);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.no_photo));
            }
        }
        if(requestCode==NOT_FRIEND_PHOTO_REQUEST) new DetailsScreenActivity_DownloadImage_ASYNC(usersArrayList.get(position).getC_photoPath(),imageView,getApplicationContext()).execute();
    }

    private void SetUpResources() {
        txtUsername = (TextView) findViewById(R.id.txtData);
        txtFullName = (TextView) findViewById(R.id.txtFirstName);
        txtUserID   = (TextView) findViewById(R.id.txtLasstName);
        txtAge      = (TextView) findViewById(R.id.txtAge);
        imageView   = (ImageView) findViewById(R.id.imageViewDetails);

        btnAddFriend=(Button) findViewById(R.id.btnAddFriend);
        btnRemoveFriend=(Button) findViewById(R.id.btnRemoveFriend);
    }

    private void setUpButtonListeners()
    {
        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= (String) txtUsername.getText();
                if(WebService.checkForInternet(getApplicationContext()))
                {
                    if (checkIfExists(user, TABLE_FRIENDS))
                    {
                        Toast.makeText(DetailsScreenActivity.this, "Friend Already Exists", Toast.LENGTH_SHORT).show();
                        Log.d("DetailsScreenActivity....SQLite data", service.getUsersFromLocalDatabase(TABLE_FRIENDS).toString());
                    }
                    else
                    {
                        if (DoActionToFriendFromLocalAndRemoteDatabase(user, "add", TABLE_FRIENDS))
                            Toast.makeText(DetailsScreenActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();

                    }
                    Log.d("DetailsScreenActivity....SQLite data", service.getUsersFromLocalDatabase(TABLE_FRIENDS).toString());
                }
            }
        });

        btnRemoveFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String user=(String) txtUsername.getText();
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
        tmp=service.getUser(user,table);
        if(tmp!=null){Log.d("wtf",tmp.getC_name());return true;}
        else return false;
    }
    private boolean DoActionToFriendFromLocalAndRemoteDatabase(String user,String action,String table)
    {
       Users tmp=service.getUser(user,table);
       switch (action)
       {
          case "delete": Log.d("Button", "delete");
              if(tmp!=null)
              {
                  service.removeUser(user);
                  new DeleteFriendFromRemoteAsyncTask(tmp.getC_id()).execute();
                  return true;
              }
               break;
           case "add": Log.d("Button", "add");

               Users tmpUser=usersList.get(currentUserID);
               ArrayList<Users> tempList=new ArrayList<Users>();
               tempList.add(tmpUser);
               service.addDataToLocalDatabase(tempList, table);
               new AddFriendToRemoteAsyncTask(tmpUser.getC_id(),FriendsScreenActivity.USER_ID).execute();

               return true;
       }
       return false;
    }
}
