package snapchattapp.texnlog.com.snapchatapp.UserConnection;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by thomas
 */
public class SeparateUserTest extends TestCase{
    User mockUser = new User();
    List<User> userList = new ArrayList<>();
    String mock;

    @Before
    public void setMockData(){
        mock = "1 john 18  johnwayne 1234hfj\n";
        mock += "2 sheldon 28 sheldoncoop 54657ngd";

    }

    @Before
    public void setMockObjects(){
        String[] dataOnRows = mock.split("\n");

        for(int i=0; i<dataOnRows.length; i++){
            String[] dataIndex = dataOnRows[i].split(" ");
            mockUser.user_id = dataIndex[0];
            mockUser.name = dataIndex[1];
            mockUser.age = dataIndex[2];
            mockUser.username = dataIndex[3];
            mockUser.password = dataIndex[4];
            userList.add(mockUser);
        }
    }


    @Test
    public void splitMockDataOnRows(){
        SeparateUser su = new SeparateUser();
        boolean check = true;
        String[] result = su.splitDataOnRows(mock);
        String[] expectingResult = mock.split("\n");

        for(int i=0; i<result.length; i++){
            if(!(expectingResult[i].equals(result[i]))){
                check = false;
            }
        }
        assertTrue(check);
    }

    @Test
    public void splitMockDataIndex(){
        String[] dataOnRows = mock.split("\n");
        SeparateUser su = new SeparateUser();
        User user = new User();
        List<User> result = new ArrayList<>();

        for(int i=0; i<dataOnRows.length; i++) {
            user = su.splitLineIndex(dataOnRows[i]);
            result.add(user);
        }
        assertEquals(userList,result);
    }
}