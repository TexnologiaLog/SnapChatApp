package snapchattapp.texnlog.com.snapchatapp;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thomas on 10/11/2015.
 */
public class accountValidatorTest extends TestCase {

    @Test
    public void userNameValidationTest(){
        accountValidator av = new accountValidator();
        boolean results = av.isUsernameValid("asdfg");
        assertTrue(results);
    }

    @Test
    public void userNameValidation2Test(){
        accountValidator av = new accountValidator();
        boolean results = av.isUsernameValid("s33dfgj&^f$%d");
        assertFalse(results);
    }

    @Test
    public void passwordValidationTest(){
        accountValidator av = new accountValidator();
        boolean results = av.isPasswordValid("123456akdb523");
        assertFalse(results);
    }

    @Test
    public void passwordValidation2Test(){
        accountValidator av = new accountValidator();
        boolean results = av.isUsernameValid("sdf&*)jds#dsafi**da9__");
        assertFalse(results);
    }

}