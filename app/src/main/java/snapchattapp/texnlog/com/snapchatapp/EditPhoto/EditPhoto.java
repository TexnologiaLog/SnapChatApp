package snapchattapp.texnlog.com.snapchatapp.EditPhoto;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.File;

import snapchattapp.texnlog.com.snapchatapp.R;

/**
 * Created by Maria on 15/11/2015.
 */

public class EditPhoto extends Activity {

    private static final String TAG = "Debug";
    final int RQS_IMAGE1 = 1;
    public ImageView imagePreview;
    Button btnGallery, btnText, btnRotate, btnFrameProcessing, btnSave;
    TextView textSource1;
    EditText editTextCaption;
    Uri source1;
    Bitmap bitmapMaster, tempBitmap;
    Canvas canvasMaster;
    Paint paintDraw;
    File mediaStorageDir, cachePath;
    int prvX, prvY;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editphoto_layout);
        SetUp();
        SetUpButtonListeners();
    }


    private void ViewImage() {
        String value = "/storage/emulated/0/DCIM/MyCameraApp/Custom_.jpg";
        File file = new File(value);
        Uri uri = Uri.fromFile(file);
        source1 = uri;
        textSource1.setText(source1.toString());
        imagePreview = (ImageView) findViewById(R.id.photoEditPreview);
        imagePreview.setImageBitmap(null);
        try {
            tempBitmap = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(source1));

            Bitmap.Config config;
            if (tempBitmap.getConfig() != null) {
                config = tempBitmap.getConfig();
            } else {
                config = Bitmap.Config.ARGB_8888;
            }

            //bitmapMaster is Mutable bitmap
            bitmapMaster = Bitmap.createBitmap(
                    tempBitmap.getWidth(),
                    tempBitmap.getHeight(),
                    config);

            canvasMaster = new Canvas(bitmapMaster);
            canvasMaster.drawBitmap(tempBitmap, 0, 0, null);

            imagePreview.setImageBitmap(bitmapMaster);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imagePreview.setAdjustViewBounds(true);
        Log.d(TAG, "ImageView created");
    }


    private void SetUp() {
        btnRotate = (Button) findViewById(R.id.btnRotate);
        editTextCaption = (EditText) findViewById(R.id.editTextCaption);
        btnText = (Button) findViewById(R.id.btnProcessing);
        btnGallery = (Button) findViewById(R.id.btnGallery);
        textSource1 = (TextView) findViewById(R.id.textSource1);
        btnFrameProcessing = (Button) findViewById(R.id.btnFrameProcessing);
        btnSave = (Button) findViewById(R.id.btnSave);
        ViewImage();
        paintDraw = new Paint();
        paintDraw.setStyle(Paint.Style.FILL);
        paintDraw.setColor(Color.BLACK);
        paintDraw.setStrokeWidth(10);
    }

    private void SetUpButtonListeners() {

        btnRotate.setOnClickListener(new Rotate(imagePreview, EditPhoto.this));

        btnText.setOnClickListener(new DrawText(imagePreview, source1, editTextCaption, textSource1, EditPhoto.this));

        btnSave.setOnClickListener(new SaveEditPhoto(imagePreview, EditPhoto.this));

        btnFrameProcessing.setOnClickListener(new Frame(imagePreview, source1, textSource1, EditPhoto.this));

        imagePreview.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();
                int x = (int) event.getX();
                int y = (int) event.getY();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        prvX = x;
                        prvY = y;
                        drawOnProjectedBitMap((ImageView) v, bitmapMaster, prvX, prvY, x, y);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        drawOnProjectedBitMap((ImageView) v, bitmapMaster, prvX, prvY, x, y);
                        prvX = x;
                        prvY = y;
                        break;
                    case MotionEvent.ACTION_UP:
                        drawOnProjectedBitMap((ImageView) v, bitmapMaster, prvX, prvY, x, y);
                        break;
                }
    /*
     * Return 'true' to indicate that the event have been consumed.
     * If auto-generated 'false', your code can detect ACTION_DOWN only,
     * cannot detect ACTION_MOVE and ACTION_UP.
     */
                return true;
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

    /*
     * Project position on ImageView to position on Bitmap
     * draw on it
     */
    private void drawOnProjectedBitMap(ImageView iv, Bitmap bm, float x0, float y0, float x, float y) {
        if (x < 0 || y < 0 || x > iv.getWidth() || y > iv.getHeight()) {
            //outside ImageView
            return;
        } else {

            float bmw = bm.getWidth();
            float ivw = iv.getWidth();
            float bmh = bm.getHeight();
            float ivh = iv.getHeight();

            float ratioWidth = bmw / ivw;
            float ratioHeight = bmh / ivh;

            canvasMaster.drawLine(
                    x0 * ratioWidth,
                    y0 * ratioHeight,
                    x * ratioWidth,
                    y * ratioHeight,
                    paintDraw);
            imagePreview.invalidate();
        }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap tempBitmap;

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RQS_IMAGE1:
                    source1 = data.getData();
                    textSource1.setText(source1.toString());

                    try {
                        //tempBitmap is Immutable bitmap,
                        //cannot be passed to Canvas constructor
                        tempBitmap = BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(source1));

                        Bitmap.Config config;
                        if (tempBitmap.getConfig() != null) {
                            config = tempBitmap.getConfig();
                        } else {
                            config = Bitmap.Config.ARGB_8888;
                        }

                        //bitmapMaster is Mutable bitmap
                        bitmapMaster = Bitmap.createBitmap(
                                tempBitmap.getWidth(),
                                tempBitmap.getHeight(),
                                config);

                        canvasMaster = new Canvas(bitmapMaster);
                        canvasMaster.drawBitmap(tempBitmap, 0, 0, null);

                        imagePreview.setImageBitmap(bitmapMaster);
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }
}