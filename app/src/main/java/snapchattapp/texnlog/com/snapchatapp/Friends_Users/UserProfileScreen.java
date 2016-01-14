package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.UserProfileScreen_LoadProfileImage_ASYNC;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.UserProfileScreen_ChangeProfilePhoto_ASYNC;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

/**
 * Created by SoRa1 on 10/12/2015.
 */
public class UserProfileScreen extends Activity
{
    private static final int REQUEST_CODE = 1;
    private        Button btnFriends,btnChangeImage, btnSearch;
    private        ImageView imgViewProfileImage;
    private        Users loggedInUser;
    private        TextView txtUsername, txtFullName, txtAge;
    private static UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        userLocalStore = new UserLocalStore(getApplicationContext());
        loggedInUser   = userLocalStore.getLoggedInUser();


        SetUpResources(); // Initialize Activity Resources
        setValues();      // Set Values to fields
        SetUpListeners(); // Set up event listeners
        new UserProfileScreen_LoadProfileImage_ASYNC(getApplicationContext(), imgViewProfileImage).execute(); // Load Profile Image
    }

    private void SetUpListeners() {
        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FriendsScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });


        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, REQUEST_CODE);
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfileScreen.this,SearchScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    private void setValues() {
        txtFullName.setText(loggedInUser.getC_name());
        txtUsername.setText(loggedInUser.getC_username());
        txtAge.setText(loggedInUser.getC_age());
    }

    private void SetUpResources() {
        imgViewProfileImage = (ImageView) findViewById(R.id.imageViewUserProfileScreen);
        btnChangeImage      = (Button) findViewById(R.id.btnUserProfileScreenChangeImageButton);
        txtUsername         = (TextView) findViewById(R.id.txtUserProfileScreenUsername);
        txtFullName         = (TextView) findViewById(R.id.txtUserProfileScreenName);
        btnFriends          = (Button) findViewById(R.id.btnUserProfileScreenFriends);
        txtAge              = (TextView) findViewById(R.id.txtUserProfileScreenAge);
        btnSearch           = (Button) findViewById(R.id.btnUserProfileScreenSearch);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturned)
    {
        super.onActivityResult(requestCode, resultCode, imageReturned);
        if(resultCode==RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                Uri selectedImage = imageReturned.getData();
                new UserProfileScreen_ChangeProfilePhoto_ASYNC(selectedImage,getApplicationContext(), imgViewProfileImage).execute();
            }
        }
    }
}
