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
import android.widget.TextView;

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

    public UserProfileScreenTest() {
        super(UserProfileScreen.class);
    }



    @Test
    public void testSearchButton()
    {
        Activity activity = getActivity();
        Intents.init();
        onView(withId(R.id.btnUserProfileScreenSearch)).perform(click());       // Perform click on "Search" button
        intended(hasComponent(SearchScreenActivity.class.getName()));           // Check if the right activity loads
        Intents.release();
    }

    @Test
    public void testFriendsButton()
    {
        Activity activity = getActivity();
        Intents.init();
        onView(withId(R.id.btnUserProfileScreenFriends)).perform(click());     // Perform click on "Friends" button
        intended(hasComponent(FriendsScreenActivity.class.getName()));         // Check uf the right activity loads
        Intents.release();
    }


    @Test
    public void testActivityExists() {
        UserProfileScreen activity = getActivity();
        assertNotNull(activity);                                             //  Assert that activity "UserProfileScreen" exists
    }

    @Test
    public void testUserProfileImageNotNull() {
        Activity activity = getActivity();
        Drawable drawable = null ;                                                                  //
        ImageView imageView = (ImageView) activity.findViewById(R.id.imageViewUserProfileScreen);   // Get the drawable object from
        drawable = imageView.getDrawable();                                                         // imageView and put it in
        assertNotNull(drawable);                                                                    // a nullable drawable object and assert that is not NULL
    }
    @Test
    public void testUserDetailsNotNull()
    {

        TextView txtAge      = (TextView) getActivity().findViewById(R.id.txtUserProfileScreenAge);       // Assign the actual
        TextView txtName     = (TextView) getActivity().findViewById(R.id.txtUserProfileScreenName);      // TextView's from activity
        TextView txtUsername = (TextView) getActivity().findViewById(R.id.txtUserProfileScreenUsername);  // to TextView objects

        assertNotSame("null",txtAge.getText());                                                           // Test that text fields
        assertNotSame("null",txtUsername.getText());                                                      // values are not "null"
        assertNotSame("null",txtName.getText());                                                          // which is the default value

    }


}

