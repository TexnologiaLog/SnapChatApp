package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
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
    private String phpurl ="http://projectdb.esy.es/Android/GetSnap.php";
    private ArrayList<String> arrayList=new ArrayList<>();
    private JSONParser jsonParser=new JSONParser();
    private  ArrayList<String> photoUrls=new ArrayList();
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_snap);

        imageView   = (ImageView) findViewById(R.id.receiveSnapImageView);
        chronometer = (Chronometer) findViewById(R.id.ReceiveSnapChronometer);

        new ReceiveSnap_Async(ReceiveSnap.this).execute();

    }

    private class ReceiveSnap_Async extends AsyncTask
    {
        private final Context context;
        private int i=0;

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
                String data= URLEncoder.encode("rec_id","UTF-8")+"="+URLEncoder.encode(user.getC_username(),"UTF-8");
                HttpURLConnection connection=WebService.httpRequest(data, phpurl);
                String response=WebService.httpResponse(connection);
                Log.d("response",response);


                    JSONArray jsonArray= (JSONArray) jsonParser.parse(response);
                    arrayList=JSONtoArrayListData(jsonArray);
                    photoUrls=getPhotoUrls(arrayList);





                Log.d("arraylist",arrayList.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Object o)
        {
            if(!photoUrls.isEmpty()) {
                imageView.setImageBitmap(getBitmapFromUrl(photoUrls.get(i)));
            }else{
                Log.d("imageView","No photo to show");
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!photoUrls.get(i+1).isEmpty()) {
                        i=i+1;
                        imageView.setImageBitmap(getBitmapFromUrl(photoUrls.get(i)));
                    }else{
                        i=0;
                        imageView.setImageBitmap(getBitmapFromUrl(photoUrls.get(i)));
                    }
                }
            });
            dialog.dismiss();
            super.onPostExecute(o);
        }
        public   ArrayList<String> JSONtoArrayListData(JSONArray jSONarray) throws JSONException {
            JSONObject tmp=null;

            ArrayList<String> usersArrayList=new ArrayList<String>();
            if(jSONarray!=null) {
                for (int i = 0; i < jSONarray.size(); i++) {
                    tmp = (JSONObject) jSONarray.get(i);


                        usersArrayList.add((String) tmp.get("photo_url"));

                    usersArrayList.add((String) tmp.get("timer")) ;

                }
            }
            return usersArrayList;
    }
        public ArrayList<String> getPhotoUrls(ArrayList<String> arrayList){
            ArrayList photoUrls=new ArrayList();


            for(int i=0;i<arrayList.size();i++) {
                if (!arrayList.get(i).equalsIgnoreCase("timer")) {
                    photoUrls.add(arrayList.get(i));
                }
            }



            return photoUrls;
        }
        public Bitmap getBitmapFromUrl(String url){
            try{
                URL photourl=new URL(url);
                HttpURLConnection connection=(HttpURLConnection)photourl.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input =connection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(input);
                return bitmap;
            }catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
}

}
