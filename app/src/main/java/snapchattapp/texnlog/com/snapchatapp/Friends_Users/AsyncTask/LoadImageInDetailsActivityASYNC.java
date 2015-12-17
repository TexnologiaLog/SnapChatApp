package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import snapchattapp.texnlog.com.snapchatapp.R;

/**
 * Created by SoRa1 on 17/12/2015.
 */
public class LoadImageInDetailsActivityASYNC extends AsyncTask {


    private final ImageView imageview;
    private final String photoURL;
    private final Context context;
    private Bitmap bitmap;
    private String photo;

    public LoadImageInDetailsActivityASYNC(String PhotoURL,ImageView image,Context contex)
    {
        photoURL=PhotoURL;
        imageview=image;
        context=contex;
    }


    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        try
        {
            HttpURLConnection connection= (HttpURLConnection) new URL(photoURL).openConnection();
            bitmap= BitmapFactory.decodeStream(connection.getInputStream());

        }
        catch (IOException e)
        {
            e.printStackTrace();
            photo="no_photo";
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(photo.equals("no_photo"))
        {

            imageview.setImageDrawable(context.getResources().getDrawable(R.drawable.no_photo));
        }
        else imageview.setImageBitmap(bitmap);
    }
}
