package snapchattapp.texnlog.com.snapchatapp.UserConnection;

import android.content.Context;
import android.content.SharedPreferences;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;


/**
 * Created by  ΕΙΡΗΝΗ on 7/11/2015.
 */

public class UserLocalStore {

    public static final  String SP_NAME ="userDetails";

    static SharedPreferences userLocalDatabase;


    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData (User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("user_id", user.user_id);
        spEditor.putString("age", String.valueOf(user.age));
        spEditor.putString("photo", user.photo);
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password",user.password);
        spEditor.commit();
    }

    public static  Users getLoggedInUser(){
        String id=userLocalDatabase.getString("user_id","");
        String name =userLocalDatabase.getString("name", "");
        String age =userLocalDatabase.getString("age","");
        String username =userLocalDatabase.getString("username", "");
        String password =userLocalDatabase.getString("password","");
        String photoPath=userLocalDatabase.getString("photo","");

        Users storedUser = new Users(id,name,age,username,password,photoPath);
        return storedUser;
    }

    public  void  setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.commit();
    }

    public static boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn",false) == true){
            return true;
        } else{
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }



}
