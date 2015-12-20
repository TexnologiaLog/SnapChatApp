package snapchattapp.texnlog.com.snapchatapp.UserConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas
 */
public class UserExistInDatabase {

    // Check username or password are in database
    public boolean insertedUserDataExist(User user){
        boolean result = false;
        ReadingUserData rud = new ReadingUserData();
        List<User> userList = new ArrayList<>();
        userList = rud.usersReader();

        for(User us : userList){
            if((user.username.equals(us.username)))
                result = true;
        }
        return result;
    }
}
