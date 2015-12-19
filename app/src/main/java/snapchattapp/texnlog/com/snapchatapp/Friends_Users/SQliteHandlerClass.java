package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.UserConnection.UserLocalStore;

/**
 * Created by SoRa1 on 1/12/2015.
 */
public class SQliteHandlerClass extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static       String USER_ID;
    public static        String TABLE_FRIENDS ;
    private static final String DATABASE_NAME = "testing";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHOTO = "personal_photo";
    private              Context conText;


    public SQliteHandlerClass(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        conText=context;
        UserLocalStore localStore=new UserLocalStore(conText);
        Users tmp= localStore.getLoggedInUser();
        USER_ID=tmp.getC_username();
        TABLE_FRIENDS= "friends"+USER_ID;
        Log.d("SQliteHandlerClass..Table name:",TABLE_FRIENDS);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_FRIENDS_TABLE ="CREATE TABLE " + TABLE_FRIENDS + " ("
            + KEY_ID + " INTEGER NOT NULL ,"
            + KEY_NAME + " VARCHAR(32),"
            + KEY_AGE + " VARCHAR(10),"
            + KEY_USERNAME + " VARCHAR(32),"
            + KEY_PASSWORD + " VARCHAR(32),"
            + KEY_PHOTO + " VARCHAR(64), "
            + "UNIQUE("+KEY_USERNAME+"),"
            + "PRIMARY KEY ("+KEY_ID+")"+");";


        db.execSQL(CREATE_FRIENDS_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        onCreate(db);
    }


    public void addUser(Users user,String table)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, user.getC_id());
        values.put(KEY_NAME,user.getC_name());
        values.put(KEY_AGE,user.getC_age());
        values.put(KEY_USERNAME, user.getC_username());
        values.put(KEY_PASSWORD, user.getC_password());
        values.put(KEY_PHOTO, user.getC_photoPath());


        try{ db.insertOrThrow(table, null, values);}
        catch (Exception e){Log.d("SQLiteHandlerClass....", e.getMessage());}
        db.close();

    }


    public Users getUsers(String usernameToRead,String table) throws NullPointerException
    {
        Users tmp=null;
        SQLiteDatabase db = this.getReadableDatabase();

        String [] query=new String[] { KEY_ID, KEY_NAME,KEY_AGE,KEY_USERNAME,KEY_PASSWORD,KEY_PHOTO };
        String [] stringToSearch={usernameToRead};

        Cursor cursor = db.query(table,query, KEY_USERNAME + "=?",stringToSearch, null, null, null, null);

        if (cursor != null&&cursor.moveToFirst())
        {
            tmp = new Users(cursor.getString(0),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        }
        cursor.close();
        db.close();
        return tmp;
    }

    public ArrayList<Users> getAllUsers(String table)
    {
        ArrayList<Users> tmp=new ArrayList<Users>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " +table;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) do {
            Users users = new Users();
            users.setC_id(cursor.getString(0));
            users.setC_name(cursor.getString(1));
            users.setC_age(cursor.getString(2));
            users.setC_username(cursor.getString(3));
            users.setC_password(cursor.getString(4));
            users.setC_photoPath(cursor.getString(5));
            // Adding users to list
            tmp.add(users);
            Log.d("SQLiteHandlerClass...GetAllUsers", users.toString());
        } while (cursor.moveToNext());


        cursor.close();
        db.close();

        return tmp;
    }


    public boolean removeUser(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int i=-1;
        String[] whereArgs = new String[] { username };
        try{db.execSQL("DELETE  FROM "+TABLE_FRIENDS+" WHERE username='"+username+"'");}
        catch (Exception e){e.getMessage();Log.d("check",String.valueOf(i));}
        Log.d("SQLiteHandlerClass...RemoveUser...", String.valueOf(i));
        db.close();
        return i>0;
    }



}
