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

import snapchattapp.texnlog.com.snapchatapp.Friends_Users.AsyncTask.AsyncTaskSearchFriendsImage;
import snapchattapp.texnlog.com.snapchatapp.R;


/**
 * Created by SoRa1 on 9/12/2015.
 */
public class ListViewAdapter extends ArrayAdapter<Users>
{
    private static final int FRIENDS_LISTVIEW = 0;
    ArrayList<Users> usersArrayList;
    int flag;


    public ListViewAdapter(Context context,ArrayList<Users> users,int flaG) {
        super(context, R.layout.custom,users);
        usersArrayList=users;
        flag=flaG;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View custom= inflater.inflate(R.layout.custom, parent, false);

        String itemUsername=usersArrayList.get(position).getC_username();
        String itemName=usersArrayList.get(position).getC_name();
        String itemPhotoPath= usersArrayList.get(position).getC_photoPath();

        TextView txtUsername= (TextView) custom.findViewById(R.id.customListViewTxtUsername);
        TextView txtName= (TextView) custom.findViewById(R.id.customListViewTxtName);



        ImageView imageView = (ImageView) custom.findViewById(R.id.customListViewImage);

        Log.d("FLAG", String.valueOf(flag));
        if(flag==FRIENDS_LISTVIEW)
        {
            try {
                FileInputStream fis=getContext().openFileInput(usersArrayList.get(position).getC_username());
                Bitmap bit=BitmapFactory.decodeStream(fis);
                imageView.setImageBitmap(bit);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        else new AsyncTaskSearchFriendsImage(itemPhotoPath,imageView).execute();

        txtUsername.setText(itemUsername);
        txtName.setText(itemName);

        return custom;
    }
}
