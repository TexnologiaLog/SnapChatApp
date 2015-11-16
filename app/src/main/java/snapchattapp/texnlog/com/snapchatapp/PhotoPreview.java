package snapchattapp.texnlog.com.snapchatapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import snapchattapp.texnlog.com.snapchatapp.R;

/**
 * Created by SoRa1 on 11/11/2015.
 */
public class PhotoPreview extends Activity
{
    private static final String TAG ="Debug" ;
    ImageView imagePreview;
    Button btnNewSnap,btnSend,btnEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);
        ViewImage();
        SetUpButtons();
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

        SetUpButtonListeners();
    }


    private void SetUpButtonListeners()
    {
        btnNewSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TestingCameraActivity.class));
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
}
