package demo.customcamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
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

/**
 * Created by Maria on 15/11/2015.
 */

public class EditPhoto extends Activity {

    private static final String TAG = "Debug";
    ImageView imagePreview;
    int numClicks = 1;
    Button btnGallery, btnProcessing, btnRotate;
    EditText editTextCaption;

    final int RQS_IMAGE1 = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editphoto_layout);
        ViewImage();
        SetUpButtons();
    }

    private void ViewImage() {
        imagePreview = (ImageView) findViewById(R.id.photo_edit_preview);
        //imagePreview.setImageDrawable();
    }

    /*
    private void ViewImage() {
        Intent intent = getIntent();
        String value = intent.getStringExtra("Picture"); //if it's a string you stored.
        imagePreview = (ImageView) findViewById(R.id.photo_edit_preview);
        imagePreview.setImageURI(Uri.parse(value));
        imagePreview.setRotation(90);
        imagePreview.setAdjustViewBounds(true);
        Log.d(TAG, "ImageView created");
        image=value;
    }
    */

    private void SetUpButtons() {
        //btnText = (Button) findViewById(R.id.btnText);
        //btnDraw = (Button) findViewById(R.id.btnDraw);
        btnRotate = (Button) findViewById(R.id.btnRotate);
        editTextCaption = (EditText) findViewById(R.id.caption);
        btnProcessing = (Button) findViewById(R.id.processing);
        btnGallery = (Button) findViewById(R.id.btnGallery);

        SetUpButtonListeners();
    }

    private void SetUpButtonListeners() {
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePreview.setPivotX(imagePreview.getWidth() / 2);
                imagePreview.setPivotY(imagePreview.getHeight() / 2);
                imagePreview.setRotation(30 * numClicks);
                numClicks++;
            }
        });

        btnProcessing.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
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

            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ImageGallery.class));
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }


    private Bitmap ProcessingBitmap() {
        Bitmap bm1 = null;
        Bitmap newBitmap = null;

        Config config = bm1.getConfig();

        config = Bitmap.Config.ARGB_8888;


        newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
        Canvas newCanvas = new Canvas(newBitmap);

        newCanvas.drawBitmap(bm1, 0, 0, null);

        String captionString = editTextCaption.getText().toString();
        if (captionString != null) {

            Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintText.setColor(Color.BLUE);
            paintText.setTextSize(50);
            paintText.setStyle(Style.FILL);
            paintText.setShadowLayer(10f, 10f, 10f, Color.BLACK);

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

        return newBitmap;
    }

}

