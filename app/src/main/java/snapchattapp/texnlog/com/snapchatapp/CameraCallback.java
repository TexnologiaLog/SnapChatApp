package snapchattapp.texnlog.com.snapchatapp;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by SoRa1 on 10/11/2015.
 */
public class CameraCallback extends Activity implements Camera.PictureCallback
{
    private static final int MEDIA_TYPE_IMAGE =1 ;
    private static final String TAG ="Debug" ;
    public static  Camera cam=null;

    private PhotoPreview preview;
    public CameraCallback(Camera camera)
    {
        cam=camera;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera)
    {
        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);


        if (pictureFile == null){
            Log.d(TAG, "Error creating media file, check storage permissions: ");
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
        Log.d(TAG,"Returning from CameraCallback");
    }

    private static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "MyCameraApp");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d(TAG, "failed to create directory");
                return null;
            }
        }

        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE)
        {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "Custom_"+ ".jpg");
        } else return null;

        return mediaFile;
    }
}

