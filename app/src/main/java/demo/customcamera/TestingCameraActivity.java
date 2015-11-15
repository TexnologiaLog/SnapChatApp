package demo.customcamera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public  class TestingCameraActivity extends Activity {

    private static final String TAG ="Debug" ;
    private static final String PICTURE_TAKEN ="Picture" ;
    private  static   Camera customCamera=null;
    private Camera.Parameters customCameraParam;
    private CameraPreview camPreview;
    private ImageButton btnCamera,btnPreviewImage;
    public static ImageView image;
    private PopupMenu popUp;
    private File mediaStorageDir,mediaFile;
    private FrameLayout preview;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_layout);
        InitializeButtons();
        CameraButtonAction();
        ShowImageAction();



    }




    private void ShowImageAction() {
        btnPreviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked");
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TestingCameraActivity.this, PhotoPreview.class);
                intent.putExtra(PICTURE_TAKEN, mediaFile.getAbsolutePath());
                startActivity(intent);
                Log.d(TAG, "Starting Photo preview Activity");
            }


        });
    }

    private void CameraButtonAction()
    {
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked");
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                customCamera.takePicture(null, null, null, new CameraCallback(customCamera));
                Log.d(TAG, "Returned from CameraCallback");
                btnPreviewImage.setEnabled(true);
                btnCamera.setEnabled(false);
                preview.removeView(btnPreviewImage);
                preview.addView(btnPreviewImage);
            }
        });
    }



    private void InitializeCameraPreview() {
        camPreview=new CameraPreview(this,customCamera);
        preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(camPreview);
        preview.removeView(btnCamera);
        preview.addView(btnCamera);

    }

    private void InitializeButtons() {

        btnCamera=(ImageButton) findViewById(R.id.fab);
        btnPreviewImage=(ImageButton) findViewById(R.id.btnFlash);
        image=(ImageView) findViewById(R.id.image);
        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MyCameraApp");
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "Custom_"+ ".jpg");
        btnPreviewImage.setEnabled(false);

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


    @Override
    protected void onStart() {
        super.onStart();
        InitializeCamera();
        InitializeCameraPreview();
        btnPreviewImage.setEnabled(false);
        btnCamera.setEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }


}
