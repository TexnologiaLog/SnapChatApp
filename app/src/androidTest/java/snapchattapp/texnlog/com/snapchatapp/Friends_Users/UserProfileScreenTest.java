package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.support.test.espresso.intent.*;
import org.junit.Rule;
import org.junit.Test;
import snapchattapp.texnlog.com.snapchatapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by SoRa1 on 13/1/2016.
 */
public class UserProfileScreenTest extends ActivityInstrumentationTestCase2<UserProfileScreen> {

    public UserProfileScreenTest()
    {
        super(UserProfileScreen.class);
    }

    @Rule
    public IntentsTestRule<UserProfileScreen> intentsRule = new IntentsTestRule<>(UserProfileScreen.class);

    @Test
    public void testActivityExists()
    {
        UserProfileScreen activity =  getActivity();
        assertNotNull(activity);
    }

    @Test
    public void testUserProfileImageNotNull()
    {
        UserProfileScreen activity =  getActivity();
        assertNotNull(activity.findViewById(R.id.imageViewUserProfileScreen));
    }


    @Test
    public void testButtonClick()
    {
        Intents.init();
        Intent resultData = new Intent();
        Drawable bit = getActivity().getResources().getDrawable(R.drawable.no_photo);
        resultData.putExtra("image", R.drawable.no_photo);

        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // Set up result stubbing when an intent sent to "contacts" is seen.

        intending(hasAction(Intent.ACTION_PICK)).respondWith(result);

        // User action that results in "contacts" activity being launched.
        // Launching activity expects phoneNumber to be returned and displays it on the screen.

        onView(withId(R.id.btnUserProfileScreenChangeImageButton)).perform(ViewActions.click());
        Intents.release();
        // Assert that data we set up above is shown.
        onView(withId(R.id.imageViewUserProfileScreen)).equals(bit);



    }


}
