package snapchattapp.texnlog.com.snapchatapp.EditPhoto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;


/**
 * Created by Maria on 4/1/2016.
 */
public class Frame extends EditPhoto implements Button.OnClickListener {

    private final Context context;
    ImageView iv;
    Uri path;
    TextView ts;

    public Frame(ImageView imagePreview, Uri source1, TextView textSource1, Context context) {

        this.context = context.getApplicationContext();
        iv = imagePreview;
        path = source1;
        ts = textSource1;
    }

    @Override
    public void onClick(View v) {

        if (path != null) {
            Bitmap processedBitmap = FrameProcessingBitmap();
            if (processedBitmap != null) {
                iv.setImageBitmap(processedBitmap);
                Toast.makeText(context.getApplicationContext(),
                        "Done",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context.getApplicationContext(),
                        "Something wrong in processing!",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context.getApplicationContext(),
                    "Select image!",
                    Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RQS_IMAGE1:
                    path = data.getData();
                    ts.setText(path.toString());
                    break;
            }
        }
    }

    private Bitmap FrameProcessingBitmap() {
        Bitmap bm1 = null;
        Bitmap newBitmap = null;

        try {
            bm1 = BitmapFactory.decodeStream(
                    context.getContentResolver().openInputStream(path));

            int w = bm1.getWidth();
            int h = bm1.getHeight();

            Bitmap.Config config = bm1.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }

            newBitmap = Bitmap.createBitmap(w, h, config);
            Canvas newCanvas = new Canvas(newBitmap);
            newCanvas.drawColor(Color.WHITE);

            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            Rect frame = new Rect(
                    (int) (w * 0.05),
                    (int) (w * 0.05),
                    (int) (w * 0.95),
                    (int) (h * 0.95));
            RectF frameF = new RectF(frame);
            newCanvas.drawRoundRect(frameF, (float) (w * 0.05), (float) (h * 0.05), paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
            newCanvas.drawBitmap(bm1, 0, 0, paint);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return newBitmap;
    }

}
