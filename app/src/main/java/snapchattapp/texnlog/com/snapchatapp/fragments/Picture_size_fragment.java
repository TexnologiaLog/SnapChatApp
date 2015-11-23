package snapchattapp.texnlog.com.snapchatapp.fragments;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import snapchattapp.texnlog.com.snapchatapp.Camera.CameraState;
import snapchattapp.texnlog.com.snapchatapp.Camera.TestingCameraActivity;
import snapchattapp.texnlog.com.snapchatapp.R;

/**
 * Created by User on 22/11/2015.
 */
public class Picture_size_fragment extends Fragment {
    @Nullable
    CameraState state=CameraState.getCameraState();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.picture_size_layout,container,false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        Camera camera=state.getCamera();



        final Camera.Parameters cameraParameters = camera.getParameters();

        final Spinner spinnerSupportedPictureSizes =(Spinner)getView().findViewById(R.id.supportedpicturesizes);



        final List<Camera.Size> listSupportedPictureSizes = cameraParameters.getSupportedPictureSizes();

        List<String> listStrSupportedPictureSizes = new ArrayList<String>();
        Camera.Size size=cameraParameters.getPictureSize();
        Log.d("Debug",String.valueOf(size.height)+" "+ String.valueOf(size.width));
        listSupportedPictureSizes.add(size);

        for (int i=0; i < listSupportedPictureSizes.size(); i++){

            String strSize = String.valueOf(i) + " : "
                    + String.valueOf(listSupportedPictureSizes.get(i).height)
                    + " x "
                    + String.valueOf(listSupportedPictureSizes.get(i).width);
            listStrSupportedPictureSizes.add(strSize);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, listStrSupportedPictureSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSupportedPictureSizes.setAdapter(adapter);

        spinnerSupportedPictureSizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Camera.Size size;

                int f1 = listSupportedPictureSizes.get(position).height;
                int f2 = listSupportedPictureSizes.get(position).width;

                cameraParameters.setPictureSize(f2, f1);


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}

