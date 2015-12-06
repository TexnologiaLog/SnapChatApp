package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import java.io.Serializable;

/**
 * Created by SoRa1 on 1/12/2015.
 */
public class Users implements Serializable
{
    private String c_id, c_name,c_age,c_username,c_password;

    public Users(){}

    public Users(String c_id, String c_name, String c_age, String c_username, String c_password) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_age = c_age;
        this.c_username = c_username;
        this.c_password = c_password;
    }

    @Override
    public String toString() {
        if(c_name ==null)return "null entry";
        else return "\nID:"+ c_id+"\nFirst name:"+ c_name +"\nAge:"+c_age+"\nUsername:"+c_username+"\nPassword:"+c_password+"\n";
    }

    //Get and Set methods



    public String getC_password() {return c_password;}

    public void setC_password(String c_password) {this.c_password = c_password;}

    public String getC_id() {return c_id;}

    public void setC_id(String c_id) {this.c_id = c_id;}

    public String getC_name() {return c_name;}

    public void setC_name(String c_name) {this.c_name = c_name;}

    public String getC_age() {return c_age;}

    public void setC_age(String c_age) {this.c_age = c_age;}

    public String getC_username() {return c_username;}

    public void setC_username(String c_username) { this.c_username = c_username;}
}
