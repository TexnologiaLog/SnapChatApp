package demo.customcamera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by SoRa1 on 11/11/2015.
 */
public class PhotoPreview extends Activity
{
    ImageView imagePreview;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);
        Intent intent = getIntent();
        String value = intent.getStringExtra("data"); //if it's a string you stored.
        imagePreview=(ImageView) findViewById(R.id.photo_preview);

        imagePreview.setImageURI(Uri.parse(value));
        imagePreview.setRotation(90);
        imagePreview.setAdjustViewBounds(true);
    }
}
