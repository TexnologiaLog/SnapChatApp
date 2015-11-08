package snapchattapp.texnlog.com.snapchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.snapchatapp.R;

public class TestingHomeScreen extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private static final String DEBUG ="Debugging" ;

    LinearLayout homeScreen;
    TextView screenText;
    GestureDetector swipeDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Testing_layout);
        homeScreen=(LinearLayout) findViewById(R.id.homeScreenLayout);
        swipeDetector=new GestureDetector(this,this);
        screenText=(TextView) findViewById(R.id.textView);
    }



    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {

        if (e1.getX() > e2.getX())
        {
            Log.d(DEBUG, "Right to Left swipe performed");
            Intent SecondScreenIntent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivityForResult(SecondScreenIntent, 0);
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return swipeDetector.onTouchEvent(event);
    }



    @Override
    public boolean onDown(MotionEvent e) { return true;}

    @Override
    public void onShowPress(MotionEvent e) { }

    @Override
    public boolean onSingleTapUp(MotionEvent e) { return true; }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return true;}

    @Override
    public void onLongPress(MotionEvent e) { }


}
