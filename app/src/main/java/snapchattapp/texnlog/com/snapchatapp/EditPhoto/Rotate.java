package snapchattapp.texnlog.com.snapchatapp.EditPhoto;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Maria on 4/1/2016.
 */
public class Rotate extends Activity implements Button.OnClickListener {

    private final Context context;
    int numClicks = 1;
    ImageView iv;

    public Rotate(ImageView imagePreview, Context context) {

        this.context = context.getApplicationContext();
        iv = imagePreview;
    }

    @Override
    public void onClick(View v) {
        RotateStart();
    }

    public void RotateStart() {

        iv.setPivotX(iv.getWidth() / 2);
        iv.setPivotY(iv.getHeight() / 2);
        iv.setRotation(90 * numClicks);
        numClicks++;
        Toast.makeText(context.getApplicationContext(),
                "Rotated",
                Toast.LENGTH_LONG).show();
    }

}