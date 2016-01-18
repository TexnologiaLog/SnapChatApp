package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;

import junit.framework.TestResult;

import org.junit.Before;
import org.junit.Test;

import snapchattapp.texnlog.com.snapchatapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by panagiotis on 1/17/2016.
 */
public class ReceiveSnapTest extends ActivityInstrumentationTestCase2<ReceiveSnap>{
    public ReceiveSnapTest() {
        super(ReceiveSnap.class);
    }

    @Before
    protected void setUp() throws Exception {
        super.setUp();

    }


    public void testImageViewListener(){

       Activity activity=getActivity();
       ImageView Initialimage=(ImageView)getActivity().findViewById(R.id.receiveSnapImageView);
       Drawable Initialdrawable=Initialimage.getDrawable();
       onView(withId(R.id.receiveSnapImageView)).perform(click());
       ImageView expectedImage=(ImageView)getActivity().findViewById(R.id.receiveSnapImageView);
       Drawable expectedDrawable=expectedImage.getDrawable();
       assertEquals(Initialdrawable,expectedDrawable);
   }
}
