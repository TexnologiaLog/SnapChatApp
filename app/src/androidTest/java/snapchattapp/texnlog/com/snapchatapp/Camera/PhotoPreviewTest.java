package snapchattapp.texnlog.com.snapchatapp.Camera;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import junit.framework.TestResult;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import snapchattapp.texnlog.com.snapchatapp.EditPhoto.EditPhoto;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UploadImg.GalleryUpload;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class PhotoPreviewTest extends ActivityInstrumentationTestCase2<PhotoPreview> {


    private PhotoPreview activity;
    private Instrumentation instrumentation;
    public PhotoPreviewTest() {
        super(PhotoPreview.class);
    }


        @Rule
        public IntentsTestRule<PhotoPreview> intentsRule = new IntentsTestRule<>(PhotoPreview.class);




    @Before
    protected  void setUp() throws Exception {
        super.setUp();

        String string="null";
        Intent intent=new Intent();
        intent.putExtra("Picture",string);
        intent.putExtra("current_camera",0);
        setActivityIntent(intent);
        activity=getActivity();
    }


    @Test
    public void testSendGalleryButton()
    {

        Intents.init();
        onView(withId(R.id.btnGSend)).perform(click());
        onView(withId(R.id.btnGSend)).check(matches(isDisplayed()));

        intended(hasComponent(GalleryUpload.class.getName()));
        Intents.release();

    }


}
