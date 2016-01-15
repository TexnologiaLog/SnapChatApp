package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.FriendsScreenActivity;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.SQliteHandlerClass;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;

/**
 * Created by SoRa1 on 9/12/2015.
 */
public class FriendsScreenActivity_StoreImagesLocally_ASYNC extends AsyncTask
{
    private static  String TABLE_FRIENDS ;
    private  Context context;
    private  WebService service;
    private ArrayList<Users> arrayListUsers;

    public FriendsScreenActivity_StoreImagesLocally_ASYNC(ArrayList<Users> users, Context conText)
    {
        arrayListUsers=users;
        context=conText;
        service=new WebService(context);
        TABLE_FRIENDS = service.sQliteHandlerClass.TABLE_FRIENDS;
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
         for (Users user : arrayListUsers) {
            try
            {
                HttpURLConnection connection = (HttpURLConnection) new URL(user.getC_photoPath()).openConnection();
                InputStream inputStream = connection.getInputStream();
                Bitmap img = BitmapFactory.decodeStream(inputStream);

                FileOutputStream fos= context.openFileOutput(user.getC_username(),Context.MODE_APPEND);
                img.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();

                user.setC_photoPath(user.getC_username());
                Log.d("StoreImagesLocally....CurrentUser photoPath", user.getC_photoPath());
            } catch (Exception e) { e.printStackTrace();}
        }
        return null;
    }



    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        service.addDataToLocalDatabase(FriendsScreenActivity_GetFriendsFromRDatabase_ASYNC.friendsArrayListFromJSON, TABLE_FRIENDS); //Add Friends to SQLite Database
        FriendsScreenActivity.addListData(service.getUsersFromLocalDatabase(TABLE_FRIENDS));                                         //Fill listView at Friends Screen Activity
    }
}
