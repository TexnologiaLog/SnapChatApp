package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

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
    private String response;
    private ArrayList<String> photoUrls;
    private ArrayList<String> senderIds;
    private static int k=1;
    private static int sizee=0;
    private TextView senderName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_snap);

        imageView   = (ImageView) findViewById(R.id.receiveSnapImageView);
//        chronometer = (Chronometer) findViewById(R.id.ReceiveSnapChronometer);
        photoUrls   = new ArrayList<>();
        senderName =(TextView) findViewById(R.id.sender_Name);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReceiveSnap.this, "Clicked", Toast.LENGTH_SHORT).show();
                if(k<sizee)new ReceiveSnap_Async(ReceiveSnap.this,false,k++).execute();
                else
                {
                    Toast.makeText(getBaseContext(),"No more snaps for view",Toast.LENGTH_SHORT).show();
                    System.exit(0);
                }
            }
        });

        new ReceiveSnap_Async(ReceiveSnap.this,true,0).execute();
    }

    private class ReceiveSnap_Async extends AsyncTask
    {
        private final Context context;
        private JSONParser jsonParser;
        private ArrayList<String> arrayList;
        private Bitmap bitmap;
        private Boolean flag;
        private int index;
        private String sender;
        public ReceiveSnap_Async(Context COntext,Boolean state,int ind)
        {
            context=COntext;
            userLocalStore=new UserLocalStore(context);
            user=userLocalStore.getLoggedInUser();
            jsonParser = new JSONParser();
            flag=state;
            index=ind;

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
            try
            {
                if(flag)
                {
                    String data = URLEncoder.encode("rec_id", "UTF-8") + "=" + URLEncoder.encode(user.getC_username(), "UTF-8");
                    HttpURLConnection connection = WebService.httpRequest(data, URL);
                    response = WebService.httpResponse(connection);
                    Log.d("response", response);
                    JSONArray jsonArray = (JSONArray) jsonParser.parse(response);
                    arrayList = JSONtoArrayListData(jsonArray);
                    Log.d("sds", arrayList.toString());
                    for (int i = 0; i < arrayList.size(); i = i + 2) {
                        photoUrls.add(arrayList.get(i));

                    }
                    for(int i=1;i<arrayList.size();i=i+2)
                    {
                        senderIds.add(arrayList.get(i));

                    }
                    sizee=photoUrls.size();
                    Log.d("GIa na dioume", photoUrls.toString());
                    bitmap = getBitmapFromURL(photoUrls.get(0));
                    sender=senderIds.get(0);
                }
                else
                {
                    bitmap = getBitmapFromURL(photoUrls.get(index));
                    sender = senderIds.get(index);

                }

            } catch (Exception e) { e.printStackTrace(); }

            return null;
        }


        @Override
        protected void onPostExecute(Object o)
        {
            super.onPostExecute(o);
            if(photoUrls.isEmpty())
            {
                Drawable myIcon = getResources().getDrawable( R.drawable.no_photo );
                imageView.setImageDrawable(myIcon);
                Log.d("YEAH", response);

            }
            else
            {
                senderName.setText(sender);
                imageView.setImageBitmap(bitmap);
                Log.d("YEAH",response);
            }
            dialog.dismiss();

        }



        public   ArrayList<String> JSONtoArrayListData(JSONArray jSONarray) throws JSONException {
            JSONObject tmp=null;

            ArrayList<String> usersArrayList=new ArrayList<String>();
            if(jSONarray!=null) {
                for (int i = 0; i < jSONarray.size(); i++) {
                    tmp = (JSONObject) jSONarray.get(i);


                    usersArrayList.add((String) tmp.get("photo_url"));

                    usersArrayList.add((String) tmp.get("sender_id")) ;

                }
            }
            return usersArrayList;
        }

        private Bitmap getBitmapFromURL(String url)
        {
            Bitmap bitmap=null;
            try
            {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
            catch (Exception e) {e.printStackTrace();}

            return bitmap;
        }
    }
}
