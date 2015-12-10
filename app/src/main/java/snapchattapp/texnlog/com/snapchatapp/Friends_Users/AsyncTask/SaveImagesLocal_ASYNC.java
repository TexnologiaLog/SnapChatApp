package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.FileInputStream;
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
public class SaveImagesLocal_ASYNC extends AsyncTask
{
    private static final String TABLE_FRIENDS = SQliteHandlerClass.TABLE_FRIENDS;
    private final Context context;
    ArrayList<Users> arrayListUsers;
    @Override
    protected Object doInBackground(Object[] objects)
    {
         for (Users user : GetDataFromDatabaseAsyncTask.friendsArrayListFromJSON) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(user.getC_photoPath()).openConnection();
                InputStream inputStream = connection.getInputStream();
                Bitmap img = BitmapFactory.decodeStream(inputStream);

                FileOutputStream fos= context.openFileOutput(user.getC_username(),Context.MODE_APPEND);
                img.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();

                user.setC_photoPath(user.getC_username());
                Log.d("IMAGI", user.getC_photoPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public SaveImagesLocal_ASYNC(ArrayList<Users> users,Context conText)
    {
        arrayListUsers=users;
        context=conText;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        WebService.addDataToLocalDatabase(context, GetDataFromDatabaseAsyncTask.friendsArrayListFromJSON, TABLE_FRIENDS);
        FriendsScreenActivity.addListData(WebService.getUsersFromLocalDatabase(context, TABLE_FRIENDS));
    }
}
