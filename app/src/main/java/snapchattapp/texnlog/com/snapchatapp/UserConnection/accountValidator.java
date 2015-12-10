package snapchattapp.texnlog.com.snapchatapp.UserConnection;

/**
 * Created by thomas on 10/11/2015.
 */
public class accountValidator {
    String[] restrictedCharacters = {"!","@","#","$","%","^","&","*","(",")","-","+","*","/"};

    //Method that calls the rest of the account checks and reports its results to register.
    public boolean isRegisterValid(User user){
        boolean ageCheck = isAgeValid(Integer.parseInt(user.age));
        if(ageCheck == false)
            return false;

        boolean nameCheck = isNameValid(user.name);
        if(nameCheck == false)
            return false;

        boolean usernameCheck = isUsernameValid(user.username);
        if(usernameCheck == false)
            return false;

        boolean passwordCheck = isPasswordValid(user.password);
        if(passwordCheck == false)
            return false;
        return true;
    }

    // Method that checks the integrity of the age.
    public boolean isAgeValid(int age){
        boolean check = true;

        /*
        for(int i=0; i< restrictedCharacters.length; i++){
            if(age.contains(restrictedCharacters[i]))
                check = false;
        }
        if(age.contains(" "))
            check = false;
        if(age.equals(""))
            check = false;
        if(age.contains("a-zA-Z"))
            check= false;

        */
        return check;
    }


    //Method that checks the integrity of the name.
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

    //Method that checks the integrity of the password.
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
