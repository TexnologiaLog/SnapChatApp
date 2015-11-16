package snapchattapp.texnlog.com.snapchatapp;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by SoRa1 on 10/11/2015.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
    private static final String TAG ="Debug" ;
    private SurfaceHolder sHolder;
    private Camera customCam;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        customCam=camera;
        sHolder=getHolder();
        sHolder.addCallback(this);
        sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        try
        {
            Log.d("Debug", "PhotoPreview");
            customCam.setPreviewDisplay(holder);
            customCam.startPreview();
            customCam.setDisplayOrientation(90);
        }
        catch (IOException e){e.printStackTrace();
            Log.d(TAG,"Error setting camera preview");}

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        Log.d(TAG, "Surface Changed");

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (customCam != null) {
            customCam.stopPreview();
            customCam.setPreviewCallback(null);
            customCam.release();
            customCam = null;
        }
    }
}
