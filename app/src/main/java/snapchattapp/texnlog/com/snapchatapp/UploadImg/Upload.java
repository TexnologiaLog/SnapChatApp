package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Camera.TestingCameraActivity;
import snapchattapp.texnlog.com.snapchatapp.R;

/**
 * Created by Charis on 3/12/2015.
 */

public class Upload extends  AppCompatActivity implements View.OnClickListener {

        private static final int RESULT_LOAD_IMAGE =1 ;
        private static final String SERVER_ADDRESS = "http://projectdb.esy.es/";

        ImageView imageToUpload;
        Button bUploadImage, bcamera;
        EditText upLoadImageName;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            imageToUpload = (ImageView) findViewById(R.id.imageToUpload);
            bUploadImage= (Button) findViewById(R.id.bUploadImage);
            upLoadImageName = (EditText) findViewById(R.id.etUpLoadName);
            bcamera = (Button) findViewById(R.id.camera);

            imageToUpload.setOnClickListener(this);
            bUploadImage.setOnClickListener(this);
            bcamera.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            switch(v.getId()){
                case R.id.imageToUpload:
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
                    break;
                case R.id.bUploadImage:
                    Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
                    new UploadImage(image, upLoadImageName.getText().toString()).execute();
                    break;
                case R.id.camera:
                    startActivity(new Intent(Upload.this,TestingCameraActivity.class));
                    break;

            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data !=null){
                Uri selectedImage = data.getData();
                imageToUpload.setImageURI(selectedImage);

            }
        }

        private class UploadImage extends AsyncTask<Void,Void,Void>{
            Bitmap image;
            String name;

            public UploadImage(Bitmap image,String name){
                this.image = image;
                this.name = name;
            }

            @Override
            protected Void doInBackground(Void... params) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

                ArrayList<NameValuePair> dataToSend = new ArrayList<>();
                dataToSend.add(new BasicNameValuePair("image",encodedImage));
                dataToSend.add(new BasicNameValuePair("name",name));

                HttpParams httpRequstParams = getHttpRequestParams();

                HttpClient client = new DefaultHttpClient(httpRequstParams);
                HttpPost post =new HttpPost(SERVER_ADDRESS + "upload.php");

                try{
                    post.setEntity(new UrlEncodedFormEntity(dataToSend));
                    client.execute(post);
                }catch (Exception e){
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_SHORT).show();
            }
        }

        private HttpParams getHttpRequestParams(){
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 30);
            HttpConnectionParams.setSoTimeout(httpRequestParams, 1000*30);
            return httpRequestParams;
        }
    }
