package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SoRa1 on 9/12/2015.
 */
public class AsyncTaskSearchFriendsImage extends AsyncTask {
    private static final BitmapFactory factory=null;
    private static  Bitmap bitmap=null;
    private final String photo;
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
        }
        return null;
    }

    public AsyncTaskSearchFriendsImage(String photoPath, ImageView imgView)
    {
        photo=photoPath;
        imageView=imgView;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
