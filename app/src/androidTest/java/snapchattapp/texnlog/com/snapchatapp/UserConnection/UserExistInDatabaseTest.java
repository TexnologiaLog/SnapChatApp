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
public class UserExistInDatabaseTest extends TestCase{
    String mock;
    List<User> userList = new ArrayList<>();

    @Before
    public void setData(){
        mock = "1 john 18  johnwayne 1234hfj\n";
        mock += "2 sheldon 28 sheldoncoop 54fg53g4";

        String[] dataOnRows = mock.split("\n");
        User user = new User();
        for(int i=0; i<dataOnRows.length; i++){
            String[] dataIndex = dataOnRows[i].split(" ");
            user.user_id = dataIndex[0];
            user.name = dataIndex[1];
            user.age = dataIndex[2];
            user.username = dataIndex[3];
            user.password = dataIndex[4];
            userList.add(user);
        }
    }

    @Test
    public void usernameExist(){
        User user = new User();
        user.user_id = "1";
        user.name = "john";
        user.age = "18";
        user.username = "johnwayne";
        user.password = "1234hfj";
        boolean result = false;

        for(User userData: userList){
            if(user.username.equals(userData.username))
                result = true;
        }
        assertTrue(result);
    }


    @Test
    public void usernameNotExist(){
        User user = new User();
        user.user_id = "2";
        user.name = "sheldon";
        user.age = "28";
        user.username = "shedoncooper";
        user.password = "54fg53g4";
        boolean result = false;

        for(User userData: userList){
            if(user.username.equals(userData.username))
                result = true;
        }
        assertFalse(result);
    }
}