package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.os.AsyncTask;

import java.io.IOException;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;

/**
 * Created by SoRa1 on 5/12/2015.
 */
public class AddFriendToRemoteAsyncTask extends AsyncTask {
    private final String user;
    private final String friend;

    @Override
    protected Object doInBackground(Object[] params)
    {
        try{WebService.addFriendToRemoteDatabase(user,friend);}
        catch (IOException e) {e.printStackTrace();}
        return null;
    }

    public AddFriendToRemoteAsyncTask(String user_Id,String friend_id) {user=user_Id;friend=friend_id;}
}

