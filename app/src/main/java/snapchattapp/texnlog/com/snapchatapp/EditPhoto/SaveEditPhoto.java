package snapchattapp.texnlog.com.snapchatapp.EditPhoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Maria on 5/1/2016.
 */
public class SaveEditPhoto extends EditPhoto implements Button.OnClickListener {

    private final Context context;
    ImageView iv;

    public SaveEditPhoto(ImageView imagePreview, Context context) {

        this.context = context.getApplicationContext();
        iv = imagePreview;
    }


    @Override
    public void onClick(View v) {
        SaveImage();
    }

    public void SaveImage() {

        Bitmap bitmap = iv.getDrawingCache();
        iv.setDrawingCacheEnabled(true);
        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MyCameraApp");
        cachePath = new File(mediaStorageDir.getPath() + File.separator + "Custom_" + ".jpg");
        Toast.makeText(context.getApplicationContext(),
                "Saved",
                Toast.LENGTH_LONG).show();
        try {
            cachePath.createNewFile();
            FileOutputStream ostream = new FileOutputStream(cachePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
