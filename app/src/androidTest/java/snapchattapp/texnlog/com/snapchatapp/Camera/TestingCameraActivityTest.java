package snapchattapp.texnlog.com.snapchatapp.Camera;

import android.support.test.espresso.intent.Intents;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.UserProfileScreen;
import snapchattapp.texnlog.com.snapchatapp.R;

import snapchattapp.texnlog.com.snapchatapp.UploadImg.ReceiveSnap;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by SoRa1 on 13/1/2016.
 */
public class TestingCameraActivityTest extends ActivityInstrumentationTestCase2<TestingCameraActivity>
{

    public TestingCameraActivityTest() {
        super(TestingCameraActivity.class);
    }


    public void setUp()
    {
        getActivity();
    }


    @Test
    public void testSettingsButton()
    {
        Intents.init();
        onView(withId(R.id.settings_button)).perform(click());

        intended(hasComponent(SettingsActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void testCheckSnapButton()
    {
        Intents.init();
        onView(withId(R.id.UserPhotos)).perform(click());

        intended(hasComponent(ReceiveSnap.class.getName()));
        Intents.release();
    }

    @Test
    public void testUserProfileButton()
    {
        Intents.init();
        onView(withId(R.id.btnUsers)).perform(click());

        intended(hasComponent(UserProfileScreen.class.getName()));
        Intents.release();
    }


}
