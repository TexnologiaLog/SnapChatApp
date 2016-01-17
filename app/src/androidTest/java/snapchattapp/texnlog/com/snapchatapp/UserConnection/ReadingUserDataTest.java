package snapchattapp.texnlog.com.snapchatapp.UserConnection;

import android.test.AndroidTestCase;

//import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by thomas
 */
public class ReadingUserDataTest extends AndroidTestCase{

    String mock;
    List<User> userList = new ArrayList<>();


    @Before
    public void setUp() {
        mock = "1 john 18  johnwayne 1234hfj";
        User user = new User();
        String[] userIndex = mock.split(" ");
        user.user_id = userIndex[0];
        user.name = userIndex[1];
        user.age = userIndex[2];
        user.username = userIndex[3];
        user.password = userIndex[4];
        userList.add(user);
    }

    @Test
    public void userObjectList(){
        ReadingUserData rud = new ReadingUserData();
        List<User> result = new ArrayList<>();
        result = rud.getUserObject(mock);
        this.assertEquals(userList,result);
    }
}