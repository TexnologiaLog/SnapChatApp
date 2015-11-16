package demo.customcamera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Maria on 15/11/2015.
 */
public class EditPhoto extends Activity {

    private static final String TAG = "Debug";
    ImageView imagePreview;
    Button btnText, btnDraw;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editphoto_layout);
        //ViewImage();
        SetUpButtons();
    }

    private void ViewImage() {
        Intent intent = getIntent();
        String value = intent.getStringExtra("Picture"); //if it's a string you stored.
        imagePreview = (ImageView) findViewById(R.id.photo_edit_preview);
        imagePreview.setImageURI(Uri.parse(value));
        imagePreview.setRotation(90);
        imagePreview.setAdjustViewBounds(true);
        Log.d(TAG, "ImageView created");
    }

    private void SetUpButtons() {
        btnText = (Button) findViewById(R.id.btnText);
        btnDraw = (Button) findViewById(R.id.btnDraw);

        SetUpButtonListeners();
    }

    private void SetUpButtonListeners() {
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}