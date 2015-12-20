package snapchattapp.texnlog.com.snapchatapp.UserConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas
 */
public class SeparateUser {


    public List<User> separateData(String data){
        String[] rows;
        List<User> userList = new ArrayList<>();
        User us = new User();
        rows = splitDataOnRows(data);
        for(int i=0; i<rows.length; i++ ){
            us = splitLineIndex(rows[i]);
            userList.add(us);
        }
        return userList;
    }

    // split with \n
    public String[] splitDataOnRows(String dataOnRow){
        String[] dataOnRows = dataOnRow.split("\n");
        return dataOnRows;
    }

    // Method splits in lines
    public User splitLineIndex(String dataRow){
        String[] lineData = dataRow.split(" ");
        User us = new User();
        us.user_id = lineData[0];
        us.name = lineData[1];
        us.age = lineData[2];
        us.username = lineData[3];
        us.password = lineData[4];
        us.photo = lineData[5];
        return us;
    }
}
