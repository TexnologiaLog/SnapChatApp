package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.ImageView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

/**
 * Created by SoRa1 on 7/1/2016.
 */
public class ReceiveSnap extends Activity
{
    private ImageView   imageView;
    private Chronometer chronometer;
    private ProgressDialog dialog;
    private UserLocalStore userLocalStore;
    private Users user;
    private String URL="http://projectdb.esy.es/Android/GetSnap.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_snap);

        imageView   = (ImageView) findViewById(R.id.receiveSnapImageView);
        chronometer = (Chronometer) findViewById(R.id.ReceiveSnapChronometer);

        new ReceiveSnap_Async(getApplicationContext()).execute();
    }

    private class ReceiveSnap_Async extends AsyncTask
    {
        private final Context context;

        public ReceiveSnap_Async(Context COntext)
        {
            context=COntext;
            userLocalStore=new UserLocalStore(context);
            user=userLocalStore.getLoggedInUser();
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            dialog=new ProgressDialog(context);
            dialog.setMessage("Please Wait");
            dialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects)
        {
            try {
                String data= URLEncoder.encode("rec_id","UTF-8")+"="+URLEncoder.encode(user.getC_id(),"UTF-8");
                HttpURLConnection connection=WebService.httpRequest(data,URL);
                String response=WebService.httpResponse(connection);
                Log.d("response",response);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Object o)
        {
//            imageView.setImageURI();
            dialog.dismiss();
            super.onPostExecute(o);
        }
    }
}
