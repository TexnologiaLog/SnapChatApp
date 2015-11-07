package demo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class SecondActivity extends Activity implements GestureDetector.OnGestureListener{
    private static final int MAIN_SCREEN_CODE = 14,REQUEST_TAKE_PHOTO = 1;
    LinearLayout secondLayout;
    GestureDetector detector;
    ImageView imageView;
    File photoFile,storageDir,image;


    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.second_layout);
        TakePictureIntent(); // Start Photo Capture Intent

        secondLayout = (LinearLayout) findViewById(R.id.second_layout);
        imageView=(ImageView) findViewById(R.id.image);
        detector=new GestureDetector(this,this);

    }


    private File createImageFile()      // Create an image file name
    {
        image = null;
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); // Date Stamp for later usage
        String imageFileName = "JPEG_Demo";
        storageDir = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DCIM);
        try
        {
            image = File.createTempFile(imageFileName, ".jpg", storageDir);
            Log.i("image",image.getAbsolutePath());
        }
        catch (IOException e) {Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show();}

        return image;
    }




    private void TakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = null;   // Create the File where the photo should go
        photoFile = createImageFile();
        if (photoFile != null)  // Continue only if the File was successfully created
        {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_TAKE_PHOTO&&resultCode==RESULT_OK)
        {
            imageView.setImageURI(Uri.fromFile(photoFile));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            imageView.setRotation(90);
        }


    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) // Swipe Event
    {
        if (e1.getX() < e2.getX()) { //Swipe Left to Right
            Intent mainScreenIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(mainScreenIntent,MAIN_SCREEN_CODE);
        }

        if (e1.getX() > e2.getX()) { //Swipe Right to Left
            TakePictureIntent();    //Start Capture Photo Intent
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public  void onDestroy()
    {
        super.onDestroy();
        if(!image.equals(null))
        {
            image.delete();
            photoFile.delete();
            Toast.makeText(this, "Image Deleted", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public  void onPause()
    {
        super.onPause();
        if(!image.equals(null))
        {
            image.delete();
            photoFile.delete();
            Toast.makeText(this, "Image Deleted", Toast.LENGTH_LONG).show();
        }


    }










    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }


}
