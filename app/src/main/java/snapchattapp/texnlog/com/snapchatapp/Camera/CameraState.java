package snapchattapp.texnlog.com.snapchatapp.Camera;

import android.app.Activity;
import android.hardware.Camera;

/**
 * Created by User on 23/11/2015.
 */
public class CameraState  {

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    Camera camera=null;
    private static CameraState instance;
    public static CameraState getCameraState(){
        if(instance==null){
            instance=new CameraState();

        }
        return instance;
    }

}
