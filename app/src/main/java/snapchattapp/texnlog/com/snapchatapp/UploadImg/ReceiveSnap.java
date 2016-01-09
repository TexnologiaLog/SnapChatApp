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
public class ReceiveSnap extends  Activity implements onBitmapReceived
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
    private Integer count=0;
    private ArrayList<Bitmap> bitMaps=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        onBitmapReceived Ibitmap;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_snap);
        Ibitmap=this;
        imageView   = (ImageView) findViewById(R.id.receiveSnapImageView);
        chronometer = (Chronometer) findViewById(R.id.ReceiveSnapChronometer);


        new ReceiveSnap_Async(ReceiveSnap.this).execute();


    }

    class ReceiveSnap_Async extends AsyncTask<Void,Void,ArrayList<Bitmap>>
    {


        private final Context context;
        private int i=0;

        public ReceiveSnap_Async(Context COntext)
        {
            Log.d("procedure", "enter_async");

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
        protected ArrayList  doInBackground(Void...count)
        {
            ArrayList<Bitmap> bitmaps =new ArrayList<>();
            Log.d("procedure", "doInBackground");
            try {


                String data= URLEncoder.encode("rec_id","UTF-8")+"="+URLEncoder.encode(user.getC_username(),"UTF-8");
                HttpURLConnection connection=WebService.httpRequest(data, phpurl);
                String response=WebService.httpResponse(connection);
                Log.d("response",response);
                JSONArray jsonArray= (JSONArray) jsonParser.parse(response);
                arrayList=JSONtoArrayListData(jsonArray);
                photoUrls=getPhotoUrls(arrayList);
                Log.d("procedure-photourls",photoUrls.toString());
                Log.d("arraylist",arrayList.toString());
                for(int i=0;i<photoUrls.size();i++){
                    bitmaps.add(getBitmapFromUrl(photoUrls.get(i)));


                }
                Log.d("procedure-bitmaplist",bitmaps.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }


            return bitmaps;
        }


        @Override
        protected void onPostExecute(ArrayList<Bitmap> bitmaps)
        {
             Integer photoCount =0;
            Log.d("procedure","onPostExecute");
            Log.d("procedure-setimage", bitmaps.get(photoCount).toString());
           imageView.setImageBitmap(bitmaps.get(photoCount));

            dialog.dismiss();
            super.onPostExecute(bitmaps);
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
                if (!isInteger(arrayList.get(i))){
                    photoUrls.add(arrayList.get(i));
                }
            }



            return photoUrls;
        }
        public Bitmap getBitmapFromUrl(String url){
            Log.d("procedure","enter bitmap function");
            try{
                URL photourl=new URL(url);
                HttpURLConnection connection=(HttpURLConnection)photourl.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input =connection.getInputStream();

                Bitmap bitmap= BitmapFactory.decodeStream(input);
                if(bitmap==null)
                Log.d("procedure ","bitmap is null");
                else
                Log.d("procedure",bitmap.toString()+bitmap.getByteCount());
                return bitmap;
            }catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

        public  boolean isInteger(String str) {
            if (str == null) {
                return false;
            }
            int length = str.length();
            if (length == 0) {
                return false;
            }
            int i = 0;
            if (str.charAt(0) == '-') {
                if (length == 1) {
                    return false;
                }
                i = 1;
            }
            for (; i < length; i++) {
                char c = str.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            return true;
        }


    }
    public void onPhotoReceived(ArrayList<Bitmap> bitmaps) {
        bitMaps=bitmaps;
    }

}
