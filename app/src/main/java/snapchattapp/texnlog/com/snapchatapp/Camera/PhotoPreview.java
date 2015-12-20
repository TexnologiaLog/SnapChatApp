package snapchattapp.texnlog.com.snapchatapp.Camera;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;

import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UploadImg.GalleryUpload;
import snapchattapp.texnlog.com.snapchatapp.UploadImg.RequestHandler;


/**
 * Created by SoRa1 on 11/11/2015.
 */
public class PhotoPreview extends Activity
{

    public static final String UPLOAD_URL = "http://projectdb.esy.es/upload.php";
    public static final String UPLOAD_KEY = "image";
    private static final String TAG ="Debug" ;

    ImageView imagePreview;
    Button btnNewSnap,btnSend,btnEdit,btnGSend;

    private int RESULT_LOAD_IMAGE = 1;

    private File mediaStorageDir, mediaFile;


    private Bitmap bitmap;

    private Uri filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);
        ViewImage();
        SetUpButtons();

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");

        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MyCameraApp");
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "Custom_" + ".jpg");
        bitmap = BitmapFactory.decodeFile(mediaFile.getPath());
    }

    private void ViewImage() {
        Intent intent = getIntent();
        String value = intent.getStringExtra("Picture"); //if it's a string you stored.
        imagePreview=(ImageView) findViewById(R.id.photo_preview);
        imagePreview.setImageURI(Uri.parse(value));
        imagePreview.setRotation(90);
        imagePreview.setAdjustViewBounds(true);
        Log.d(TAG, "ImageView created");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }

    private void SetUpButtons()
    {
        btnNewSnap=(Button) findViewById(R.id.btnNewSnap);
        btnEdit=(Button) findViewById(R.id.btnEdit);
        btnSend=(Button) findViewById(R.id.btnSend);
        btnGSend=(Button) findViewById(R.id.btnGSend);

        SetUpButtonListeners();
    }


    private void SetUpButtonListeners() {
        btnNewSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TestingCameraActivity.class));
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        btnGSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GalleryUpload.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Disabled Try New Snap",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Toast.makeText(this,"Disabled Try New Snap",Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    protected void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PhotoPreview.this, "Uploading Image", "Please wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);
                Log.d(TAG, uploadImage);
                HashMap<String, String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);

                String result = rh.sendPostRequest(UPLOAD_URL, data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }
}
