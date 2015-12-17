package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.LoadProfileImageASYNC;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.UploadPersonalImage_ASYNC;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

/**
 * Created by SoRa1 on 10/12/2015.
 */
public class UserProfileScreen extends Activity
{
    private Button btnFriends,btnChangeImage,btnBack;
    private ImageView imgViewUserProfileScreen;
    private Users loggedInUser;
    private TextView txtUserProfileScreenUsername,txtUserProfileScreenName,txtUserProfileScreenAge;
    private static UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        userLocalStore=new UserLocalStore(getApplicationContext());
        loggedInUser=userLocalStore.getLoggedInUser();
        btnFriends = (Button) findViewById(R.id.btnUserProfileScreenFriends);
        btnChangeImage= (Button) findViewById(R.id.btnUserProfileScreenChangeImageButton);
        btnBack = (Button) findViewById(R.id.btnUserProfileScreenBack);
        imgViewUserProfileScreen = (ImageView) findViewById(R.id.imageViewUserProfileScreen);
        txtUserProfileScreenUsername= (TextView) findViewById(R.id.txtUserProfileScreenUsername);
        txtUserProfileScreenName= (TextView) findViewById(R.id.txtUserProfileScreenName);
        txtUserProfileScreenAge = (TextView) findViewById(R.id.txtUserProfileScreenAge);



        txtUserProfileScreenName.setText(loggedInUser.getC_name());
        txtUserProfileScreenUsername.setText(loggedInUser.getC_username());
        txtUserProfileScreenAge.setText(loggedInUser.getC_age());


        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FriendsScreenActivity.class));
            }
        });


        new LoadProfileImageASYNC(getApplicationContext(), imgViewUserProfileScreen).execute();

        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new UploadPersonalImage_ASYNC(loggedInUser,"","").execute();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturned) {
        super.onActivityResult(requestCode, resultCode, imageReturned);
        if(resultCode==RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImage = imageReturned.getData();
                new UploadPersonalImage_ASYNC(selectedImage,getApplicationContext(),imgViewUserProfileScreen).execute();
                String path=selectedImage.getPath();
                Log.d("UserProfileScreen....", "Picture Taken \n"+"Path:"+path);
            }
        }
    }
}
