package snapchattapp.texnlog.com.snapchatapp.Friends_Users;


import android.test.AndroidTestCase;

import android.test.RenamingDelegatingContext;


import org.junit.Test;

import java.util.ArrayList;


/**
 * Created by SoRa1 on 17/1/2016.
 */
public class WebServiceSQLiteTest extends AndroidTestCase {



    @Test
    public void testUpdateMethodsToSQLite()
    {
        RenamingDelegatingContext screen = new RenamingDelegatingContext(getContext(), "test0");
        WebService webService = new WebService(screen.getApplicationContext());
        String TABLE_FRIENDS = webService.sQliteHandlerClass.TABLE_FRIENDS;
        ArrayList<Users> testingList = new ArrayList<>();
        testingList.add(new Users("99","test","99","test","test","null"));
        webService.addDataToLocalDatabase(testingList,TABLE_FRIENDS);
        Users user = webService.getUser("test",TABLE_FRIENDS);
        assertNotNull(user);
    }

}