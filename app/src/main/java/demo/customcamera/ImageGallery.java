package demo.customcamera;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Maria on 16/11/2015.
 */


public class ImageGallery extends Activity {

    private static final int SELECTED_PIC = 2, ROTATE_PIC = 1;
    ImageView iv;
    Button btnRotate,btnSelectGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);

        iv = (ImageView) findViewById(R.id.imgView);
        SetUpButtons();
    }

    private void SetUpButtons() {
        btnSelectGallery = (Button) findViewById(R.id.btnSelectGallery);
        btnRotate = (Button) findViewById(R.id.btnRotate);

        SetUpButtonListeners();
    }

    private void SetUpButtonListeners(){
        final Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        btnSelectGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


                startActivityForResult(i, SELECTED_PIC);
            }
        });

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                startActivityForResult(i, ROTATE_PIC);
            }
        });
    }

    public void btnClick(View v) {

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    protected void onActivityResult(int requestCode, int resaultCode, Intent data) {
        super.onActivityResult(requestCode, resaultCode, data);

        switch (requestCode) {
            case SELECTED_PIC:
                if (resaultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    Drawable d = new BitmapDrawable(yourSelectedImage);

                    iv.setBackground(d);

                }

                break;
            case ROTATE_PIC:
                //if (resaultCode == RESULT_OK){
                    RotateAnimation rotate = new RotateAnimation(0, 300);
                    rotate.setDuration(500);
                    iv.startAnimation(rotate);

                //}

                break;
        }
    }


}
