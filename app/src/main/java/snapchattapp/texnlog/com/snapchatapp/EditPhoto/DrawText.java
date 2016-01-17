package snapchattapp.texnlog.com.snapchatapp.EditPhoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import android.content.Context;


/**
 * Created by Maria on 5/1/2016.
 */
public class DrawText extends EditPhoto implements View.OnClickListener {

    private final Context context;
    ImageView iv;
    EditText editText;
    Uri source1;


    @Override
    public void onClick(View v) {
        start();
    }

    public DrawText(ImageView imagePreview, Uri source, EditText editTextCaption, Context context) {

        this.context = context.getApplicationContext();
        iv = imagePreview;
        source1 = source;
        editText = editTextCaption;


    }

    public void start() {

        if (source1 != null) {
            Bitmap processedBitmap = ProcessingBitmap();
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
                    "Select both image!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private Bitmap ProcessingBitmap() {
        Bitmap bm1 = null;
        Bitmap newBitmap = null;

        try {
            bm1 = BitmapFactory.decodeStream(
                    context.getContentResolver().openInputStream(source1));

            Bitmap.Config config = bm1.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }

            newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
            Canvas newCanvas = new Canvas(newBitmap);

            newCanvas.drawBitmap(bm1, 0, 0, null);

            String captionString = editText.getText().toString();
            if (captionString != null) {

                Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintText.setColor(Color.BLUE);
                paintText.setTextSize(100);
                paintText.setStyle(Paint.Style.FILL);
                paintText.setShadowLayer(20f, 20f, 20f, Color.BLACK);

                Rect rectText = new Rect();
                paintText.getTextBounds(captionString, 0, captionString.length(), rectText);

                newCanvas.drawText(captionString,
                        0, rectText.height(), paintText);

                Toast.makeText(context.getApplicationContext(),
                        "drawText: " + captionString,
                        Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(context.getApplicationContext(),
                        "caption empty!",
                        Toast.LENGTH_LONG).show();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return newBitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RQS_IMAGE1:
                    source1 = data.getData();
                    textSource1.setText(source1.toString());
                    break;
            }
        }
    }


}
