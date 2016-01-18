package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;

import junit.framework.TestResult;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.CountDownLatch;

import snapchattapp.texnlog.com.snapchatapp.EditPhoto.EditPhoto;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.UserProfileScreen_ChangeProfilePhoto_ASYNC;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.SearchScreenActivity;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.UserProfileScreen;
import snapchattapp.texnlog.com.snapchatapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by panagiotis on 1/14/2016.
 */
public class UITESTUploadTest extends ActivityInstrumentationTestCase2<Upload> {

    Activity activity;


    public UITESTUploadTest() {
        super(Upload.class);
    }

    @Rule
    public IntentsTestRule<Upload> intentsRule = new IntentsTestRule<>(Upload.class);

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent=new Intent();
        intent.putExtra("image","string");
        setActivityIntent(intent);
        activity=getActivity();

    }
    @Test
    public void testActivityExists() {
        Activity activity=getActivity();
        assertNotNull(activity);
    }

  @Test
    public void testUsersButton(){
      Activity activity=getActivity();
      Intents.init();
      onView(withId(R.id.optionUserButton)).perform(click());
      intended(hasComponent(FriendsPanel.class.getName()));
      Intents.release();

  }

    @Test
    public void testUploadButton(){
        onView(withId(R.id.buttonUpload)).check(matches(withText("Upload Image")));
        onView(withId(R.id.buttonUpload)).check(matches(isDisplayed()));



    }




}
