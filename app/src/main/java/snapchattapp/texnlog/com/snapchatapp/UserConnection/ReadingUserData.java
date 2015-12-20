package snapchattapp.texnlog.com.snapchatapp.UserConnection;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas
 */
public class ReadingUserData {
    public static final String SERVER_ADDRESS = "http://projectdb.esy.es/";

    //The method gets all users data from database.
    public String getUsers() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(SERVER_ADDRESS + "getUsers.php");
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());

        return result;
    }


    public List<User> usersReader(){
        String result = "";

        try{
            result = getUsers();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        List<User> userList = new ArrayList<>();
        userList = getUserObject(result);

        return userList;

    }

    // Method separate user data
    public List<User> getUserObject(String data){
        List<User> userList = new ArrayList<>();
        SeparateUser su = new SeparateUser();
        userList = su.separateData(data);

        return userList;
    }

}
