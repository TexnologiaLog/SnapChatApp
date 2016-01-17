package snapchattapp.texnlog.com.snapchatapp.EditPhoto;


import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.TypeTextAction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import snapchattapp.texnlog.com.snapchatapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Maria on 17/1/2016.
 */

@RunWith(AndroidJUnit4.class)
public class EditPhotoTest {

    private String mStringToBetyped, source;


    @Rule
    public final ActivityTestRule<EditPhoto> main = new ActivityTestRule<>(EditPhoto.class);


    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "Maria";
        source = "/storage/emulated/0/DCIM/MyCameraApp/Custom_.jpg";
    }

    @Test
    public void fromGallery() {
        onView(withId(R.id.btnGallery))
                .perform(click())
                .check(ViewAssertions.matches(isDisplayed()));

    }

    @Test
    public void checkText() {
        // Type text and then press the button.
        source = getText(withId(R.id.textSource1));
        onView(withId(R.id.editTextCaption))
                .perform(setTextInTextView(source), closeSoftKeyboard());
        onView(withId(R.id.btnProcessing))
                .perform(click());

        // Check that the text was changed.
        onView(withId(R.id.editTextCaption))
                .check(ViewAssertions.matches(withText(mStringToBetyped)));
    }

    @Test
    public void checkRotate() {
        onView(withId(R.id.btnRotate))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click())               // click() is a ViewAction
                .check(ViewAssertions.matches(isDisplayed()));

    }

    @Test
    public void checkSave() {
        onView(withId(R.id.btnSave))
                .perform(click())
                .check(ViewAssertions.matches(isDisplayed()));
    }

    public static ViewAction setTextInTextView(final String value) {
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {

                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }

        };
    }

    String getText(final Matcher<View> matcher) {
        final String[] stringHolder = { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView)view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }

}