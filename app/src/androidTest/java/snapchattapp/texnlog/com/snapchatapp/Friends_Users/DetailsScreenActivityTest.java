package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;
import android.widget.TextView;
import org.junit.Test;
import java.util.ArrayList;
import snapchattapp.texnlog.com.snapchatapp.R;



/**
 * Created by SoRa1 on 13/1/2016.
 */
public class DetailsScreenActivityTest extends ActivityInstrumentationTestCase2<DetailsScreenActivity>
{
    private DetailsScreenActivity activity;
    private WebService webService;
    private String TABLE_FRIENDS;

    public DetailsScreenActivityTest()
    {
        super(DetailsScreenActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        Intent intent=new Intent();

        ArrayList<Users> tmpList = new ArrayList<>();
        tmpList.add(new Users("test","test","test","test","test","null"));
        intent.putExtra("data", tmpList);
        intent.putExtra("id", 0);
        intent.putExtra("request_code",0);
        setActivityIntent(intent);
        activity=getActivity();
        getActivity();
        webService    = new WebService(activity.getApplicationContext());
        TABLE_FRIENDS = webService.sQliteHandlerClass.TABLE_FRIENDS;
    }
    @Test
    public  void testNotNullData()
    {
        ImageView imageView   = (ImageView) activity.findViewById(R.id.imageViewDetails);
        TextView  txtId       = (TextView)  activity.findViewById(R.id.txtUserIdDetails);
        TextView  txtUserName = (TextView)  activity.findViewById(R.id.txtUsernameDetails);
        TextView  txtAge      = (TextView)  activity.findViewById(R.id.txtAgeDetails);
        assertEquals("test", txtUserName.getText());
        assertEquals("test",txtId.getText());
        assertEquals("test", txtAge.getText());
        assertNotNull(imageView.getDrawable());
    }

    @Test
    public void testIfUserExists()
    {


        String LEGIT_USER = "panagiotis";
        String FAKE_USER  = "test";

        assertFalse(activity.checkIfExists(FAKE_USER, TABLE_FRIENDS));
        assertTrue(activity.checkIfExists(LEGIT_USER, TABLE_FRIENDS));
    }

    @Test
    public void testDoActionToDatabase() // Needs FIX

    {

        ArrayList<Users> tmpList = new ArrayList<>();
        tmpList.add(new Users("test1", "test1", "test1", "test1", "test1", "null1"));


        assertNull(webService.getUser("test1", TABLE_FRIENDS));   // User does not exist yet

        webService.addDataToLocalDatabase(tmpList, TABLE_FRIENDS);  // User added to database

        assertNotNull(webService.getUser("test1", TABLE_FRIENDS));   // User does not exist yet
    }
}
