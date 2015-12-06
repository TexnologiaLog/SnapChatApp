package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by SoRa1 on 5/12/2015.
 */
public class AddFriendToRemoteAsyncTask extends AsyncTask {
    private final String user_id;

    @Override
    protected Object doInBackground(Object[] params)
    {
        try {
            WebService.addFriendToRemoteDatabase(user_id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AddFriendToRemoteAsyncTask(String user_Id) {user_id=user_Id;}
}

