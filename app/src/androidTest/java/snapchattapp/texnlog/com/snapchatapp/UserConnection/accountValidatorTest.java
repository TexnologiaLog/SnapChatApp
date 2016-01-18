package snapchattapp.texnlog.com.snapchatapp.UserConnection;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import  static org.junit.Assert.*;
/**
 * Created by panagiotis on 1/17/2016.
 */
@RunWith(AndroidJUnit4.class)
public class accountValidatorTest {



    accountValidator validator;
    @Before
    public void setUp(){
        validator=new accountValidator();

    }
    @Test
    public void testRegisteredUser(){
        User checkedUser=new User("spectoras ","panagiotis","22","athinakis","panagiotis","photourl");

        assertTrue(validator.isRegisterValid(checkedUser));

    }


}
