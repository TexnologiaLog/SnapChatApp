package snapchattapp.texnlog.com.snapchatapp.Camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import snapchattapp.texnlog.com.snapchatapp.UserConnection.MainActivity;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;


public  class TestingCameraActivity extends Activity {

    private static final String TAG ="Debug" ;
    private static final String PICTURE_TAKEN ="Picture" ;
    private static final String ZOOM ="ZOOM" ;
    private static  int zoom =10 ;
    private  static   Camera customCamera=null;
    private Camera.Parameters customCameraParam;
    private SurfaceView camPreview;
    private ImageButton btnCamera,btnPreviewImage,btnSettings;
    public static ImageView image;
    private File mediaStorageDir,mediaFile;
    private FrameLayout preview;
    private SeekBar zoomBar;
    LinearLayout layout;
    private ImageButton btnFrontCamera;
    private static int currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
    private Button btnLogout;
    private TestingCameraActivity instance;
    private CameraState state;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_layout);
        state=CameraState.getCameraState();
        InitializeButtons();
        CameraButtonAction();
        ShowImageAction();
        zoomChangeAction();
        SwitchCameraAction();
        LogoutAction();
        SettingsButtonAction();
    }

    private void zoomChangeAction()
    {
        zoomBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                customCameraParam.setZoom(zoomBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                customCamera.setParameters(customCameraParam);
            }
        });
    }




//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        MenuInflater blowup=getMenuInflater();
//        blowup.inflate(R.menu.settings_menu, menu);
//        return true;
//
//
//
//    }



    private void SettingsButtonAction()
    {
        Log.d(TAG, "geia xara");
        Intent intent =new Intent(TestingCameraActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
    private void ShowImageAction()
    {
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

                preview.removeView(zoomBar);



                preview.removeView(zoomBar);

            }
        });
    }





    private void InitializeCameraPreview()
    {
        camPreview=new CameraPreview(this,customCamera);
        preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(camPreview);

        preview.removeView(btnCamera);
        preview.addView(btnCamera);

        preview.removeView(zoomBar);
        preview.addView(zoomBar);



        preview.removeView(btnFrontCamera);
        preview.addView(btnFrontCamera);

        preview.removeView(btnLogout);
        preview.addView(btnLogout);

        preview.removeView(btnSettings);
        preview.addView(btnSettings);


    }

    private void SwitchCameraAction()
    {
        btnFrontCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customCamera.stopPreview();
                customCamera.release();
                customCamera = null;
                InitializeCamera();
                try {
                    customCamera.setPreviewDisplay(camPreview.getHolder());
                    customCamera.setDisplayOrientation(90);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                customCamera.startPreview();
            }
        });
    }

    private void LogoutAction()
    {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(LogOut()) Toast.makeText(getApplicationContext(),"Successfully Logedout",Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(),"Error occured",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TestingCameraActivity.this, MainActivity.class));
                System.exit(0);
            }
        });

    }

    private boolean LogOut() {
        UserLocalStore tmp= MainActivity.userLocalStore;
        tmp.clearUserData();
        tmp.setUserLoggedIn(false);
        if(tmp.getUserLoggedIn()) return false;
        return true;

    }


    private void InitializeButtons()
    {

        btnCamera=(ImageButton) findViewById(R.id.fab);
        btnPreviewImage=(ImageButton) findViewById(R.id.btnFlash);
        image=(ImageView) findViewById(R.id.image);
        zoomBar=(SeekBar) findViewById(R.id.zoomBar);
        layout=(LinearLayout) findViewById(R.id.cam_layout);
        btnFrontCamera=(ImageButton) findViewById(R.id.btnFrontCam);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnSettings=(ImageButton)findViewById(R.id.settings_button);






        zoomBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d(ZOOM, "onProgress Change");
                customCameraParam.setZoom(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(ZOOM, "onSTARTTrackiing");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(ZOOM, "onStopTracking");
                customCamera.setParameters(customCameraParam);
            }
        });


        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MyCameraApp");
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "Custom_"+ ".jpg");
        btnPreviewImage.setEnabled(false);

    }

    private void InitializeCamera()
    {
        Camera.Size size;
        checkCameraHardware(this);
        customCamera=getCameraInstance();
        if(customCamera==null) Toast.makeText(this, "Camera not availlable", Toast.LENGTH_LONG).show();
        customCameraParam=customCamera.getParameters();
        customCameraParam.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        customCameraParam.setJpegQuality(100);
        state.setCamera(customCamera);
//        size=customCameraParam.getPictureSize();
//        String string=String.valueOf(size.height)+ " "+String.valueOf(size.width);
//        Log.d(TAG,string);

        zoomBar.setMax(customCameraParam.getMaxZoom());
        if(currentCameraId==Camera.CameraInfo.CAMERA_FACING_BACK) customCamera.setParameters(customCameraParam);
        zoomBar.setMax(customCameraParam.getMaxZoom());



        customCamera.setParameters(customCameraParam);
        zoomBar.setMax(customCameraParam.getMaxZoom());
}



       public   static Camera getCameraInstance(){
        Camera c = null;
        try
        {   if(currentCameraId== Camera.CameraInfo.CAMERA_FACING_BACK)
            {
                currentCameraId= Camera.CameraInfo.CAMERA_FACING_FRONT;
            }
            else currentCameraId= Camera.CameraInfo.CAMERA_FACING_BACK;
            c = Camera.open(currentCameraId); // attempt to get a Camera instance

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
    protected void onStop() {
        super.onStop();
        UserLocalStore tmp=MainActivity.userLocalStore;
        tmp.clearUserData();
        tmp.setUserLoggedIn(false);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        InitializeCamera();
        InitializeCameraPreview();
        btnPreviewImage.setEnabled(false);
        btnCamera.setEnabled(true);
        if(zoomBar==null)Log.d("y","yatta");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
        {
            //zoom up
            customCameraParam.setFlashMode("torch");
            customCamera.setParameters(customCameraParam);
        }
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            //zoom down
            customCameraParam.setFlashMode("off");
            customCamera.setParameters(customCameraParam);
        }
        return false;
    }

}
