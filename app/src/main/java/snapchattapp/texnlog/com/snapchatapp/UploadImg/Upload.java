package snapchattapp.texnlog.com.snapchatapp.UploadImg;



import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.SQliteHandlerClass;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

/**
 * Created by Charis on 11/12/2015.
 */


public class Upload extends AppCompatActivity implements View.OnClickListener {

    public static final String UPLOAD_URL = "http://projectdb.esy.es/Android/upload.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";


    private int RESULT_LOAD_IMAGE = 1;
    private File mediaStorageDir,mediaFile;
    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView,buttonUsers;
    private SendingData uploadData=SendingData.getInstance();
    private UserLocalStore userLocalStore;
    private ImageView imageView;

    private Bitmap bitmap;

    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        Log.d("panagiotis1",image);
        uploadData.setFirstImage(image);
        InitializeButtons();
        userLocalStore=new UserLocalStore(getApplicationContext());


        bitmap=BitmapFactory.decodeFile(mediaFile.getPath());
        Log.d("panagiotis2", String.valueOf(bitmap));
    }




    public void InitializeButtons(){
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        buttonUsers = (Button) findViewById(R.id.optionUserButton);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonView   = (Button) findViewById(R.id.buttonView);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageURI(Uri.parse(image));
        imageView.setRotation(90);
        imageView.setAdjustViewBounds(true);

        buttonUsers.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MyCameraApp");
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "Custom_"+ ".jpg");
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Upload.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                HashMap<String,String> dataMap=new HashMap<>();
                JSONArray data =new JSONArray();
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);
                Log.d(TAG, uploadImage);
                String userid=userLocalStore.getLoggedInUser().getC_username();

                for(int i=0;i<uploadData.getSenderId().size();i++) {
                        Log.d("SendData", String.valueOf(uploadData.getSenderId().size()));
                        JSONObject dataItem =new JSONObject();
                    try {
                        dataItem.put("userid",userid);
                        dataItem.put("senderid",uploadData.getSenderId().get(i));
                        dataItem.put("timer", 10);
//                        dataItem.put("image",uploadImage);
                        data.put(dataItem);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                dataMap.put("image",uploadImage);
                dataMap.put("json",data.toString());
                dataMap.put("count", String.valueOf(uploadData.getSenderId().size()));
                Log.d("datamap", dataMap.get("image").toString());

                String result = rh.sendPostRequest(UPLOAD_URL,dataMap);

                Log.d("result",result);

                return result;

            }
        }

        UploadImage ui = new UploadImage();

        ui.execute(bitmap);
    }

    @Override
    public void onClick(View v) {

        if(v == buttonUpload){
            uploadImage();
        }

        if(v == buttonUsers)
        {
            uploadData.getSenderId().clear();
            Intent intent =new Intent(Upload.this,FriendsPanel.class);
            startActivity(intent);
        }
    }
}


