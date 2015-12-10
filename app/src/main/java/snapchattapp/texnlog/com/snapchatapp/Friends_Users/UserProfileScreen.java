package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.LoadProfileImageASYNC;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

/**
 * Created by SoRa1 on 10/12/2015.
 */
public class UserProfileScreen extends Activity
{
    private Button btnFriends;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        btnFriends = (Button) findViewById(R.id.btnUserProfileScreenFriends);
        imageView = (ImageView) findViewById(R.id.imageViewUserProfileScreen);

        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FriendsScreenActivity.class));
            }
        });


        new LoadProfileImageASYNC(getApplicationContext(),imageView).execute();
    }
}
