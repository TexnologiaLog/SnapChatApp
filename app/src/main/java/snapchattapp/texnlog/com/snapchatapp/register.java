package snapchattapp.texnlog.com.snapchatapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ΕΙΡΗΝΗ on 6/11/2015.
 */

public class register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etName, etAge, etUsername,etPassword;


    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName= (EditText)findViewById(R.id.etName);
        etUsername= (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etAge = (EditText)findViewById(R.id.etAge);
        bRegister =(Button)findViewById(R.id.bRegister);


        bRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bRegister:

                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                User us = new User(name, age,username,password);
                accountValidator av = new accountValidator();
                boolean accountValidationCheck = av.isRegisterValid(us);

                if(accountValidationCheck == true) {
                    registerUser(us);
                }
                else{
                    showErrorMessage();
                }

        }
    }
    private void registerUser(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(register.this, login.class));
            }
        });

    }

    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(register.this);
        dialogBuilder.setMessage("There are invalid characters. Please check the fields.");
        dialogBuilder.setPositiveButton("Ok",null);
        dialogBuilder.show();

    }
}




