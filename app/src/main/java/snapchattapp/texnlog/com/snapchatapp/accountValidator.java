package snapchattapp.texnlog.com.snapchatapp;

/**
 * Created by thomas on 10/11/2015.
 */
public class accountValidator {
    String[] restrictedCharacters = {"!","@","#","$","%","^","&","*","(",")","-","+","*","/"};

    //Method that checks the integrity of the the name.
    public boolean isNameValid(String name){
        boolean check = true;
        for(int i=0; i< restrictedCharacters.length; i++){
            if(name.contains(restrictedCharacters[i]))
                check = false;
        }
        if(name.contains(" "))
            check = false;
        if(name.equals(""))
            check = false;
        return check;
    }

    //Method that checks the integrity of the username.
    public boolean isUsernameValid(String username){
        boolean check = true;

        for(int i=0; i< restrictedCharacters.length; i++){
            if(username.contains(restrictedCharacters[i]))
                check = false;
        }
        if(username.contains(" "))
            check = false;
        if(username.equals(""))
            check = false;
        return check;
    }

    //Method that checks the integrity of the the password.
    public boolean isPasswordValid(String password){
        boolean check = true;
        for(int i=0; i< restrictedCharacters.length; i++){
            if(password.contains(restrictedCharacters[i]))
                check = false;
        }
        if(password.contains(" "))
            check = false;
        if(password.equals(""))
            check = false;
        return check;
    }
}
