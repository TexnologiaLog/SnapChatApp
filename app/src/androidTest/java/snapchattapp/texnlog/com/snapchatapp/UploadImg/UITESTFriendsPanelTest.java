package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import android.app.Activity;
import android.support.test.espresso.intent.Intents;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.SQliteHandlerClass;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.Users;
import snapchattapp.texnlog.com.snapchatapp.Friends_Users.WebService;
import snapchattapp.texnlog.com.snapchatapp.R;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.User;
import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

import static android.support.test.espresso.Espresso.onData;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by panagiotis on 1/16/2016.
 */
public class UITESTFriendsPanelTest extends ActivityInstrumentationTestCase2<FriendsPanel>
{

    @Before
            protected void setUp(){

    }
    Activity activity;
    public UITESTFriendsPanelTest() {
        super(FriendsPanel.class);
    }
    @Test
    public void testActivityExists() {
        Activity activity=getActivity();
        assertNotNull(activity);
    }
    @Test
    public void testimageView()

    {
        Activity activity=getActivity();
           onData(hasToString("giwrgos")).inAdapterView(withId(R.id.friend_panel)).check(matches(isDisplayed()));
    }
    @Test
    public void testDoneButton()
    {
        Activity activity=getActivity();
        Intents.init();
        onView(withId(R.id.doneButton)).perform(click());
        intended(hasComponent(Upload.class.getName()));
        Intents.release();
    }
    @Test
    public void testAdapterValues(){
        Activity activity=getActivity();
        SQliteHandlerClass sQliteHandlerClass= new SQliteHandlerClass(activity.getBaseContext());
        WebService webService=new WebService(activity.getBaseContext());
        String TABLE_FRIENDS ="friends";
        UserLocalStore userLocalStore=new UserLocalStore(activity.getBaseContext());
        Users user =userLocalStore.getLoggedInUser();
        ArrayList<Users> users=new ArrayList();
        users=sQliteHandlerClass.getAllUsers(TABLE_FRIENDS+user.getC_username());
        for(int i =0;i<users.size();i++){
            onData(anything()).atPosition(i).inAdapterView(withId(R.id.friend_panel)).check(matches(withText(users.get(i).getC_username())));
        }

    }
}
