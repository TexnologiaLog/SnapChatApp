package snapchattapp.texnlog.com.snapchatapp.Friends_Users;


import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;



/**
 * Created by SoRa1 on 13/1/2016.
 */


public class WebServiceTest extends ActivityInstrumentationTestCase2<FriendsScreenActivity> {
    private static final String GetFriendsServiceURL ="http://projectdb.esy.es/Android/GetFriends.php";     //Remote location of web service
    private static final String USER_ID_TO_TEST = "1";
    private FriendsScreenActivity screen;
    private String TABLE_FRIENDS;
    private WebService webService;



    public WebServiceTest() {
        super(FriendsScreenActivity.class);
    }

    public void setUp()
    {
       screen = getActivity();
       webService = new WebService(screen.getApplicationContext());
       TABLE_FRIENDS = webService.sQliteHandlerClass.TABLE_FRIENDS;
    }

    @Test
    public void testGetFriendsFromServer() throws IOException, ParseException
    {

        JSONArray array =  webService.getFriendsListFromRemoteDatabase(GetFriendsServiceURL, USER_ID_TO_TEST); // Get Friends from database
        Log.d("ServerResponse", String.valueOf(array));
        String response = array.toString();

        assertNotNull("Doesn't have any friends", response);         // Connection with Server is successful
        assertTrue("Contains JSON data", response.startsWith("[{")); // Response is valid
        assertTrue("Contains JSON data", response.endsWith("}]"));   // JSON format
    }

    @Test
    public void testGetFriendsFromSQLite()
    {


        ArrayList <Users> usersArrayList = webService.getUsersFromLocalDatabase(TABLE_FRIENDS);
        ArrayList<Users> expectedList = new ArrayList<>();

        expectedList.add(new Users("1","harris","22","root","toor","null"));                                // Adding your friends into
        expectedList.add(new Users("41", "panagiotis_athin", "23", "panagiotis", "athinakis", "null"));     // an ArrayList<Users>
        expectedList.add(new Users("47", "xaris_tsatsaris", "22", "xaris", "tsatsaris", "null"));           //


        assertEquals(expectedList.toString(), usersArrayList.toString());                                   // Assert that you get the right list of friends
    }









}

