package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * Created by SoRa1 on 4/12/2015.
 */
public class   WebService
{
    private static final int READ_TIMEOUT =10000 ;
    private static final int CONNECTION_TIMEOUT =10000;
    public static final String ADD_FRIEND_URL = "http://192.168.1.4/android/AddFriend.php"; //Testing at localhost
    public static final String DELETE_FRIEND_URL = "http://192.168.1.4/android/DeleteFriend.php";
    //public static final String ADD_FRIEND_URL = "http://projectdb.esy.es/Android/AddFriend.php";  //Working on remote Database
    //public static final String DELETE_FRIEND_URL = "http://projectdb.esy.es/Android/DeleteFriend.php";
    private static SQliteHandlerClass sQliteHandlerClass;


    public WebService(Context context)
    {

        sQliteHandlerClass=new SQliteHandlerClass(context);
    }

    public static JSONArray getDataFromRemoteDatabase(String serviceURL) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        HttpURLConnection conn=(HttpURLConnection) new URL(serviceURL).openConnection();
        String response=httpResponse(conn);
        return (JSONArray) parser.parse(response);
    }

    public static  void addDataToLocalDatabase(Context context,ArrayList<Users> usersArrayList,String table)
    {
        for(int i=0;i<usersArrayList.size();i++)
        {
            sQliteHandlerClass.addUser(usersArrayList.get(i),table);
            Log.d("DATABASE", "Table:" + table + "\n" +usersArrayList.get(i).toString());
        }
        sQliteHandlerClass.close();
    }

    public static ArrayList<Users> getUsersFromLocalDatabase(Context context,String TABLE)
    {
        ArrayList<Users> arrayListToReturn=null;
        return  arrayListToReturn=sQliteHandlerClass.getAllUsers(TABLE);
    }

   public  static ArrayList<Users> JSONtoArrayListData(JSONArray jSONarray) {
        JSONObject tmp=null;
        Users tmpUser=null;
        ArrayList<Users> usersArrayList=new ArrayList<Users>();
        if(jSONarray!=null) {
            for (int i = 0; i < jSONarray.size(); i++) {
                tmp = (JSONObject) jSONarray.get(i);
                tmpUser = new Users();
                tmpUser.setC_id((String) tmp.get("user_id"));
                tmpUser.setC_name((String) tmp.get("name"));
                tmpUser.setC_age((String) tmp.get("age"));
                tmpUser.setC_username((String) tmp.get("username"));
                tmpUser.setC_password((String) tmp.get("password"));
                tmpUser.setC_photoPath((String) tmp.get("photo"));
                usersArrayList.add(tmpUser);
                tmpUser = null;
            }
        }
        return  usersArrayList;
    }

    public static  JSONArray getFriendsListFromRemoteDatabase(String SendDataWebServiceURL,String user_ID) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        String user_id=user_ID;
        String data="";
        String fields[]={"user_id","name","age","username","password","photo"};

            data += URLEncoder.encode("user_id", "UTF-8")+ "=" + URLEncoder.encode(user_id, "UTF-8");

        Log.d("WebServiceGetFriendsRemote...",data.toString());

        HttpURLConnection connection=httpRequest(data,SendDataWebServiceURL);
        String FriendsJSONstring =httpResponse(connection);


        return (JSONArray) parser.parse(FriendsJSONstring);

    }


    public static Users getUser(String username,String table)
    {
        Users tmp=null;
        try{tmp=sQliteHandlerClass.getUsers(username,table);}
        catch (NullPointerException e){e.printStackTrace();}
        return tmp;
    }
    public static boolean removeUser(String username)
    {
        return sQliteHandlerClass.removeUser(username);
    }

    public static boolean deleteFromRemote(String user_id) throws IOException {
        String data=null;
        data = URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8");

        HttpURLConnection conn=httpRequest(data,DELETE_FRIEND_URL);
        String response=httpResponse(conn);


        Log.d("WebServiceDeleteRemoteRESPONSE",response);
        if(response.equals("ok")) return true;
        return false;

    }
    public static boolean addFriendToRemoteDatabase(String friend_id,String user_id) throws IOException
    {
        String data = URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8");
        data+="&"+URLEncoder.encode("friend_id","UTF-8")+"="+URLEncoder.encode(friend_id,"UTF-8");

        Log.d("WebServiceAddFriendRemoteREQUEST:",data.toString());

        HttpURLConnection conn=httpRequest(data, ADD_FRIEND_URL);
        String response= httpResponse(conn);


        Log.d("WebServiceAddFriendRemoteRESPONSE",response);
        if(response.equals("ok")) return true;
        return false;
    }

    public static HttpURLConnection httpRequest(String dataToSend,String ServiceURL) throws IOException {
        BufferedReader reader=null;
        URL url = new URL(ServiceURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(dataToSend);
        wr.flush();
        return conn;
    }
    public static String httpResponse(HttpURLConnection con) throws IOException {
        BufferedReader reader= null;
        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = reader.readLine()) != null)
        {
            sb.append(line + "\n");
        }
        reader.close();
        Log.d("WebServiceHttpRESPONSE",sb.toString());
        return sb.toString();
    }


    public static boolean checkForInternet(Context context)
    {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mMobile = connManager .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (mWifi.isConnected()){
            Log.d("INTERNET","goodWIFI");
            return true;
        }
        else if (mMobile.isConnected()) {
            Log.d("INTERNET","good3G");
            return true;
        }
        else
        {
            Toast.makeText(context,"No Internet Connection , Retry...",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
