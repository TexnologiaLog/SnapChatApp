package snapchattapp.texnlog.com.snapchatapp.UserConnection;

/**
 * Created by  ΕΙΡΗΝΗ on 7/11/2015.
 */
public class User {
    String name,username,password,photo,user_id;
    String age;

    public User (String id ,String name, String  age, String username, String password,String photo){
        this.name = name;
        this.age= age;
        this.username= username;
        this.password = password;
        this.photo=photo;
        this.user_id=id;
    }

    public User (String username, String password){
        this.username = username;
        this.password = password;
        this.age = "-1";
        this.name = "";
    }
}