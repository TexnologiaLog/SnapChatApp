package snapchattapp.texnlog.com.snapchatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Button bLogout;
    EditText etName;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);

        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserName();
        }else{
            startActivity(new Intent(MainActivity.this,login.class));
        }
    }

    private  boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    private  void  displayUserName(){
        User user = userLocalStore.getLoggedInUser();

        etName.setText(user.name);
        ;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);


                startActivity(new Intent(this, login.class));
                break;

        }
    }

}
