package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.app.Activity;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import org.junit.Test;

import snapchattapp.texnlog.com.snapchatapp.Camera.TestingCameraActivity;
import snapchattapp.texnlog.com.snapchatapp.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by SoRa1 on 13/1/2016.
 */
public class FriendsScreenActivityTest extends ActivityInstrumentationTestCase2<FriendsScreenActivity> {
    public FriendsScreenActivityTest()
    {
        super(FriendsScreenActivity.class);
    }



//    @Test
//    public void testCameraButton()
//    {
//        Activity activity = getActivity();
//
//        Intents.init();
//        onView(withId(R.id.btnFriendsScreenCamera)).perform(click());
//
//        intended(hasComponent(TestingCameraActivity.class.getName()));
//        Intents.release();
//
//    }

    @Test
    public void testSearchFriendsButton()
    {
        Activity activity = getActivity();
        Intents.init();
        onView(withId(R.id.btnFriendsScreenSearch)).perform(click());

        intended(hasComponent(SearchScreenActivity.class.getName()));
        Intents.release();

    }

    @Test
    public void testListViewItem()
    {
        Activity activity = getActivity();

        Intents.init();
        onView(withText("panagiotis")).perform(click());

        intended(hasComponent(DetailsScreenActivity.class.getName()));
        Intents.release();

    }
}
