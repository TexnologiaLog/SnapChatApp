package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.os.AsyncTask;

import java.io.IOException;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;

/**
 * Created by SoRa1 on 5/12/2015.
 */
public class DeleteFriendFromRemoteAsyncTask extends AsyncTask {
    private final String user_ID;

    @Override
    protected Object doInBackground(Object[] params)
    {
        try {
            WebService.deleteFromRemote(user_ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DeleteFriendFromRemoteAsyncTask(String user_id)
    {
        user_ID=user_id;
    }
}
