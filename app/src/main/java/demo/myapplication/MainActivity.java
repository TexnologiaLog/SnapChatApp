package demo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private static final String DEBUG ="Debugging" ;

    LinearLayout homeScreen;
    TextView screenText;
    GestureDetector swipeDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
