package snapchattapp.texnlog.com.snapchatapp.Friends_Users;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.CustomListViewAdapter_GetImageFromLocalStorage_ASYNC;
import snapchattapp.texnlog.com.snapchatapp.R;


/**
 * Created by SoRa1 on 9/12/2015.
 */
public class CustomListViewAdapter extends ArrayAdapter<Users>
{
    private static final int FRIENDS_LISTVIEW = 0;
    private int ACTIVITY_TO_FILL_DATA;
    private  ArrayList<Users> usersArrayList;


    public CustomListViewAdapter(Context context, ArrayList<Users> users, int flaG)
    {
        super(context, R.layout.custom,users);
        usersArrayList=users;
        ACTIVITY_TO_FILL_DATA =flaG;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom, parent, false);

        String itemUsername  = usersArrayList.get(position).getC_username();
        String itemName      = usersArrayList.get(position).getC_name();
        String itemPhotoPath = usersArrayList.get(position).getC_photoPath();

        TextView txtUsername = (TextView) customView.findViewById(R.id.customListViewTxtUsername);
        TextView txtName     = (TextView) customView.findViewById(R.id.customListViewTxtName);
        ImageView imageView  = (ImageView) customView.findViewById(R.id.customListViewImage);

        Log.d("CustomListViewAdapter....FlagValue", String.valueOf(ACTIVITY_TO_FILL_DATA));

        if(ACTIVITY_TO_FILL_DATA ==FRIENDS_LISTVIEW)
        {
            try
            {
                FileInputStream fis=getContext().openFileInput(usersArrayList.get(position).getC_username());
                Bitmap bit=BitmapFactory.decodeStream(fis);
                imageView.setImageBitmap(bit);
            } catch (FileNotFoundException e) {e.printStackTrace();}

        }
        else new CustomListViewAdapter_GetImageFromLocalStorage_ASYNC(itemPhotoPath,imageView,getContext()).execute();

        txtUsername.setText(itemUsername);
        txtName.setText(itemName);

        return customView;
    }
}
