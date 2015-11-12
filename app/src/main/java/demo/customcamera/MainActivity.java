package demo.customcamera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public  class MainActivity extends Activity {

    private static final String TAG ="Debug" ;
    private  Camera customCamera=null;
    private Camera.Parameters customCameraParam;
    private CameraPreview camPreview;
    private ImageButton btnCamera,btnFlash;
    private List<String> flashModes;
    public static ImageView image;
    private PopupMenu popUp;
    File mediaStorageDir;
    File mediaFile;
    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_layout);
        InitializeCamera();
        InitializeButtons();
        InitializeCameraPreview();
        SetCameraCharacteristics();
        CameraButtonAction();
        FlashButtonAction();


        popUp=new PopupMenu(this,findViewById(R.id.camera_preview));
        MenuInflater inflate=popUp.getMenuInflater();
        inflate.inflate(R.menu.menu_main, popUp.getMenu());


        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MyCameraApp");
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "Custom_"+ ".jpg");

    }



    private void SetCameraCharacteristics() {
        flashModes=customCameraParam.getSupportedFlashModes();
        for (String e:flashModes) Log.i(TAG, e);
        customCameraParam.setFlashMode("off");
        customCamera.setParameters(customCameraParam);
    }

    private void FlashButtonAction() {
        btnFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked");
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

                popUp.show();

            }


        });
    }

    private void showPopUp(View v)
    {

        popUp.show();
    }
    private void CameraButtonAction()
    {
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked");
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                customCamera.takePicture(null, null, null, new CameraCallback(customCamera));
                Intent myIntent = new Intent(MainActivity.this, PhotoPreview.class);
                myIntent.putExtra("data", mediaFile.getAbsolutePath());
                MainActivity.this.startActivity(myIntent);
                Log.d(TAG, "PhotoPreview Started");


            }
        });
    }

    private void InitializeCameraPreview() {
        camPreview=new CameraPreview(this,customCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(camPreview);
        preview.removeView(btnCamera);
        preview.addView(btnCamera);
        preview.removeView(btnFlash);
        preview.addView(btnFlash);
    }

    private void InitializeButtons() {

        btnCamera=(ImageButton) findViewById(R.id.fab);
        btnFlash=(ImageButton) findViewById(R.id.btnFlash);
        image=(ImageView) findViewById(R.id.image);

    }

    private void InitializeCamera() {
        checkCameraHardware(this);
        customCamera=getCameraInstance();
        if(customCamera==null) Toast.makeText(this, "Camera not availlable", Toast.LENGTH_LONG).show();

        customCameraParam=customCamera.getParameters();
        customCameraParam.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        customCameraParam.setJpegQuality(100);
        customCamera.setParameters(customCameraParam);
}


    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
           Log.d(TAG,"Camera not availlable");
           e.printStackTrace();
        }
        return c; // returns null if camera is unavailable
    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            Log.d(TAG,"Has Camera");
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(customCamera!=null) customCamera.release(); Log.d(TAG, "Camera Released OnDestroy");
    }



}
