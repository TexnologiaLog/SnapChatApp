package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import snapchattapp.texnlog.com.snapchatapp.R;

/**
 * Created by SoRa1 on 9/12/2015.
 */
public class AsyncTaskSearchFriendsImage extends AsyncTask {
    private static  Bitmap bitmap=null;
    private final Context context;
    private  String photo;
    private ImageView imageView;

    @Override
    protected Object doInBackground(Object[] objects)
    {
        try {
            HttpURLConnection connection= (HttpURLConnection) new URL(photo).openConnection();
            InputStream reader=connection.getInputStream();
            bitmap=BitmapFactory.decodeStream(reader);
        } catch (IOException e) {
            e.printStackTrace();
            photo="no_photo";
        }
        return null;
    }

    public AsyncTaskSearchFriendsImage(String photoPath, ImageView imgView,Context COntext)
    {
        photo=photoPath;
        imageView=imgView;
        context=COntext;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d("AsyncTaskSearchFriendImage.....photo value", photo);
        if(photo.equals("no_photo"))
        {

            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.no_photo));
        }
        else imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
