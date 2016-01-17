package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.UserProfileScreen_ChangeProfilePhoto_ASYNC;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.UserProfileScreen_LoadProfileImage_ASYNC;
import snapchattapp.texnlog.com.snapchatapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by SoRa1 on 13/1/2016.
 */
public class UserProfileScreenTest extends ActivityInstrumentationTestCase2<UserProfileScreen> {
    CountDownLatch signal;
    UserProfileScreen_ChangeProfilePhoto_ASYNC uploader;


    public UserProfileScreenTest() {
        super(UserProfileScreen.class);
    }

    @Rule
    public IntentsTestRule<UserProfileScreen> intentsRule = new IntentsTestRule<>(UserProfileScreen.class);

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    signal = new CountDownLatch(1);
                    Uri uri= Uri.parse("content://media/external/images/media/632");
                    uploader = new UserProfileScreen_ChangeProfilePhoto_ASYNC(uri,getActivity().getApplicationContext(), (ImageView) getActivity().findViewById(R.id.imageViewUserProfileScreen));
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }


    @Test
    public void testSearchButton()
    {
        Activity activity = getActivity();
        Intents.init();
        onView(withId(R.id.btnUserProfileScreenSearch)).perform(click());
        intended(hasComponent(SearchScreenActivity.class.getName()));
        Intents.release();
    }
    @Test
    public void testFriendsButton()
    {
        Activity activity = getActivity();
        Intents.init();
        onView(withId(R.id.btnUserProfileScreenFriends)).perform(click());
        intended(hasComponent(FriendsScreenActivity.class.getName()));
        Intents.release();
    }


    @Test
    public void testActivityExists() {
        UserProfileScreen activity = getActivity();
        assertNotNull(activity);
    }

    @Test
    public void testUserProfileImageNotNull() {
        Activity activity = getActivity();
        assertNotNull(activity.findViewById(R.id.imageViewUserProfileScreen));
    }
    @Test
    public void testUserDetailsNotNull()
    {
        assertNotNull(getActivity().findViewById(R.id.txtUserProfileScreenAge));
        assertNotNull(getActivity().findViewById(R.id.txtUserProfileScreenName));
        assertNotNull(getActivity().findViewById(R.id.txtUserProfileScreenUsername));
    }


    @UiThreadTest
    public void testDownloadImage() throws InterruptedException {  //NEEDS FIX
        uploader.execute();
        signal.await(30, TimeUnit.SECONDS);

        assertTrue("assert something meaningful here", true);

    }

    @UiThreadTest
    public void testImage()
    {
        Drawable drawable = null;
        new UserProfileScreen_LoadProfileImage_ASYNC(getActivity().getApplicationContext(), (ImageView) getActivity().findViewById(R.id.imageViewUserProfileScreen)).execute();
        ImageView imageView= (ImageView) getActivity().findViewById(R.id.imageViewUserProfileScreen);
        drawable = imageView.getDrawable();
        assertNotNull(drawable);

    }

    @Test
    public void testButtonClick() {    //NEEDS FIX
        Intent resultData = new Intent();
        Drawable bit = getActivity().getResources().getDrawable(R.drawable.no_photo);
        resultData.putExtra("image", R.drawable.no_photo);

        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // Set up result stubbing when an intent sent to "contacts" is seen.

        intending(hasAction(Intent.ACTION_PICK)).respondWith(result);

        // User action that results in "contacts" activity being launched.
        // Launching activity expects phoneNumber to be returned and displays it on the screen.

        onView(withId(R.id.btnUserProfileScreenChangeImageButton)).perform(click());
        // Assert that data we set up above is shown.
        onView(withId(R.id.imageViewUserProfileScreen)).equals(bit);


    }
}

