package snapchattapp.texnlog.com.snapchatapp.UserConnection;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;

import snapchattapp.texnlog.com.snapchatapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by panagiotis on 1/17/2016.
 */
public class loginTest extends ActivityInstrumentationTestCase2<login> {

    private login login;

    public loginTest() {
        super(login.class);
    }


    @Before
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        login = getActivity();
    }

    @UiThreadTest
    public void testEditTextCanBeModified() {
        EditText username = (EditText)getActivity().findViewById(R.id.etUsername);
        EditText password = (EditText)getActivity().findViewById(R.id.etPassword);

        assertNotNull(username);
        assertNotNull(password);
        final String expectedUsername = "panagiotis";
        final String expectedPassword="athinakis";
        username.setText(expectedUsername);
        password.setText(expectedPassword);
        String actualUsername = username.getText().toString();
        String actualPassword = password.getText().toString();
        assertEquals(expectedUsername, actualUsername);
        assertEquals(expectedPassword, actualPassword);

    }

   @Test
    public void testLoginFunction() {

        String username = "panagiotis", password = "athinakis";

        onView(withId(R.id.etUsername)).perform(clearText()).perform(typeText(username));
        onView(withId(R.id.etPassword)).perform(clearText()).perform(typeText(password));

        onView(withId(R.id.bLogin)).perform(click());
        onView(withId(R.id.camera_preview)).check(matches(isDisplayed()));
    }


}
