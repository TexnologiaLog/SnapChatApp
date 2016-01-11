package snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;


/**
 * Created by SoRa1 on 11/12/2015.
 */
public class UserProfileScreen_ChangeProfilePhoto_ASYNC extends AsyncTask
{
    //    private static finalString serverURL="http://192.168.1.4/android/UploadImage.php";    //Local location of Web Service
    private static final String serverURL="http://projectdb.esy.es/Android/UploadImage.php";    //Remote Location of Web Service

    private static final String IMAGE_PATH = "http://projectdb.esy.es/Android/upload/";         //Remote Absolute Image Path
    //private static final String IMAGE_PATH = "http://192.168.1.4/android/upload/";              //Local Absolute Image Path

    private        Uri selectedImageUri;
    private        ImageView imageView;
    private        Context context;
    private        Users user;
    private        WebService webService;
    private        ContentResolver contentResolver;
    private static UserLocalStore userLocalStore;
    private        String username;
    private        Bitmap bit;


    public UserProfileScreen_ChangeProfilePhoto_ASYNC(Uri uri, Context con, ImageView view)
    {
        context=con;
        webService=new WebService(context);
        userLocalStore=new UserLocalStore(context);
        user=userLocalStore.getLoggedInUser();
        contentResolver=context.getContentResolver();
        username=user.getC_username();
        selectedImageUri=uri;
        imageView=view;

    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=3;
        bit=BitmapFactory.decodeFile(new File(getPath(selectedImageUri,contentResolver)).getAbsolutePath(),options);



        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

        ArrayList<NameValuePair> dataToSend = new ArrayList<>();
        dataToSend.add(new BasicNameValuePair("image",encodedImage));
        dataToSend.add(new BasicNameValuePair("username",username));

        HttpParams httpRequstParams = getHttpRequestParams();

        HttpClient client = new DefaultHttpClient(httpRequstParams);
        HttpPost post =new HttpPost(serverURL);

        try
        {
            post.setEntity(new UrlEncodedFormEntity(dataToSend));
            client.execute(post);
        }
        catch (Exception e){e.printStackTrace();}


        webService.updateLocalDatabase(IMAGE_PATH +username+".jpg",username);   //Absolute Path to Image on remote server

        return null;
    }
    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
        imageView.setImageBitmap(bit);

    }

    private HttpParams getHttpRequestParams()
    {
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams, 20000);
        HttpConnectionParams.setSoTimeout(httpRequestParams, 20000);
        return httpRequestParams;
    }


    private  String getPath(Uri uri, ContentResolver contentResolver) {

        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = contentResolver.query(uri, projection, null, null, null);

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(column_index);

    }
}
