package demo.customcamera;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;

import android.widget.TextView;

/**
 * Created by Maria on 15/11/2015.
 */

public class EditPhoto extends Activity {

    //private static final int SELECTED_PIC = 1, ROTATE_PIC = 1;
    private static final String TAG = "Debug";
    int numClicks = 1;
    final int RQS_IMAGE1 = 1;
    ImageView imagePreview;
    Button btnGallery, btnProcessing, btnRotate;
    TextView textSource1;
    EditText editTextCaption;
    Uri source1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editphoto_layout);
        SetUp();
        SetUpButtonListeners();

    }


    private void ViewImage() {
        String value = "/storage/emulated/0/DCIM/MyCameraApp/Custom_.jpg";
        imagePreview = (ImageView) findViewById(R.id.photoEditPreview);
        imagePreview.setImageURI(Uri.parse(value));
        imagePreview.setRotation(90);
        imagePreview.setAdjustViewBounds(true);
        Log.d(TAG, "ImageView created");
        source1 = Uri.parse(value);
    }


    private void SetUp() {
        btnRotate = (Button) findViewById(R.id.btnRotate);
        editTextCaption = (EditText) findViewById(R.id.editTextCaption);
        btnProcessing = (Button) findViewById(R.id.btnProcessing);
        btnGallery = (Button) findViewById(R.id.btnGallery);
        textSource1 = (TextView) findViewById(R.id.textSource1);
        ViewImage();


    }

    private void SetUpButtonListeners() {

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePreview.setPivotX(imagePreview.getWidth() / 2);
                imagePreview.setPivotY(imagePreview.getHeight() / 2);
                imagePreview.setRotation(90 * numClicks);
                Toast.makeText(getApplicationContext(),
                        "rotate!",
                        Toast.LENGTH_LONG).show();
                numClicks++;
            }
        });


        btnProcessing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (source1 != null) {
                    Bitmap processedBitmap = ProcessingBitmap();
                    if (processedBitmap != null) {
                        imagePreview.setImageBitmap(processedBitmap);
                        Toast.makeText(getApplicationContext(),
                                "Done",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Something wrong in processing!",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Select both image!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RQS_IMAGE1);
            }
        });
    }


    private Bitmap ProcessingBitmap() {
        Bitmap bm1 = null;
        Bitmap newBitmap = null;

        try {
            bm1 = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(source1));

            Config config = bm1.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }

            newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
            Canvas newCanvas = new Canvas(newBitmap);

            newCanvas.drawBitmap(bm1, 0, 0, null);

            String captionString = editTextCaption.getText().toString();
            if (captionString != null) {

                Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintText.setColor(Color.BLACK);
                paintText.setTextSize(100);
                paintText.setStyle(Style.FILL);
                paintText.setShadowLayer(20f, 20f, 20f, Color.BLACK);

                Rect rectText = new Rect();
                paintText.getTextBounds(captionString, 0, captionString.length(), rectText);

                newCanvas.drawText(captionString,
                        0, rectText.height(), paintText);

                Toast.makeText(getApplicationContext(),
                        "drawText: " + captionString,
                        Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(),
                        "caption empty!",
                        Toast.LENGTH_LONG).show();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return newBitmap;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RQS_IMAGE1:
                    source1 = data.getData();
                    textSource1.setText(source1.toString());
                    break;
            }
        }
    }


}


