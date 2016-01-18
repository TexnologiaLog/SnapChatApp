package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.renderscript.Sampler;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.Press;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.widget.ImageView;

import org.junit.Test;

import snapchattapp.texnlog.com.snapchatapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by SoRa1 on 18/1/2016.
 */
public class ReceiveSnapTest extends ActivityInstrumentationTestCase2<ReceiveSnap> {

    ReceiveSnap activity;

    public ReceiveSnapTest()
    {
        super(ReceiveSnap.class);
    }

    public void setUp() throws InterruptedException {
        activity = getActivity();
    }

    @Test
    public void testImageChangeOnClick() throws InterruptedException {
        ImageView imageView = (ImageView) activity.findViewById(R.id.receiveSnapImageView);
        Drawable before = imageView.getDrawable();

        onView(withId((R.id.receiveSnapImageView))).perform(click());
        Drawable after  = imageView.getDrawable();
        assertNotSame(before,after);
    }


}
