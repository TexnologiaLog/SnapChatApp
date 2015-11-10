package snapchattapp.texnlog.com.videoapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.VideoView;

public class MainVideoActivity extends AppCompatActivity {
    private Button recordView,playView;
    private VideoView videoView;
    private int ActivityStart=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_video);

        recordView= (Button)findViewById(R.id.RecordButton);
        playView = (Button)findViewById(R.id.PlayButton);
        videoView = (VideoView)findViewById(R.id.videoView);

        recordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callVideoApp =new Intent();
                callVideoApp.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(callVideoApp,ActivityStart);

            }

        });
        playView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();

            }
        });

    }
    protected void onActivityResult(int requestCode ,int resultCode,Intent data){
        if(requestCode==ActivityStart&&resultCode==RESULT_OK){
            Uri VideoUri= data.getData();
            videoView.setVideoURI(VideoUri);
        }
    }


}
