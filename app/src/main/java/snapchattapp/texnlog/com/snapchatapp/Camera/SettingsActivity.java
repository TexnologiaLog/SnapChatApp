package snapchattapp.texnlog.com.snapchatapp.Camera;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import snapchattapp.texnlog.com.snapchatapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private CheckBox faceBox,eyeBox;


    private CameraParameters parameters=CameraParameters.getInstance();
    private String optionValue,sharpness="sharness".trim();
//    private static HashMap paramstable=new HashMap();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        Intent intent=getIntent();
      String  param=  intent.getStringExtra("ss");
        parameters.init(param);
     if(parameters.getParameters()!=null){
         Log.d("maria","malakia");
         Log.d("maria",parameters.getParameters());
     }


        Button goingBack=(Button)findViewById(R.id.button);

        SpinnerResources();


}

    public void onClickCheckBox(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        faceBox=(CheckBox)findViewById(R.id.faceBox);
        eyeBox=(CheckBox) findViewById(R.id.redeyeBox);
        switch (view.getId()) {
            case R.id.faceBox:
                if (checked) {
                    optionValue = "on";
                    parameters.setFacedetection(optionValue);
                } else {
                    faceBox.setChecked(false);
                    optionValue = "off";
                    parameters.setFacedetection(optionValue);
                }
            case R.id.redeyeBox:
                if (checked) {
                    optionValue = "enabled";
                    parameters.setRedeye(optionValue);
                } else {
                    eyeBox.setChecked(false);
                    optionValue = "disabled";
                    parameters.setRedeye(optionValue);
                }


        }
    }
    public void SpinnerResources() {
        //initialization
        Spinner sharpSpinner = (Spinner) findViewById(R.id.SharpnessSpinner);
        Spinner sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        Spinner effectSpinner = (Spinner) findViewById(R.id.effectSpinner);
        Spinner contrastSpinner = (Spinner) findViewById(R.id.contrastSpinner);
        Spinner saturSpinner = (Spinner) findViewById(R.id.SaturationSpinner);
        //adapters
        ArrayAdapter<String> adapterSharp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,parameters.parameterNumericValues("sharpness".trim()));
        ArrayAdapter<String> adapterSize = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parameters.parameterValues("picture-size-values".trim()));
        ArrayAdapter<String> adapterEffect = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parameters.parameterValues("effect-values".trim()));
        ArrayAdapter<String> adapterContrast = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,parameters.parameterNumericValues("contrast".trim()));
        ArrayAdapter<String> adapterSatur = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parameters.parameterNumericValues("saturation".trim()));

        adapterSharp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterEffect.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterContrast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSatur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //place the adapters to spinners
        sharpSpinner.setAdapter(adapterSharp);
        sizeSpinner.setAdapter(adapterSize);
        effectSpinner.setAdapter(adapterEffect);
        contrastSpinner.setAdapter(adapterContrast);
        saturSpinner.setAdapter(adapterSatur);
        //actions
        sizeSpinner.setOnItemSelectedListener(this);
        effectSpinner.setOnItemSelectedListener(this);
        contrastSpinner.setOnItemSelectedListener(this);
        effectSpinner.setOnItemSelectedListener(this);
        saturSpinner.setOnItemSelectedListener(this);

    }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.contrastSpinner:
            parameters.setContrast(Integer.parseInt(parent.getItemAtPosition(position).toString()));
                break;
            case R.id.SaturationSpinner:
                parameters.setSaturation(Integer.parseInt(parent.getItemAtPosition(position).toString()));
                break;
            case R.id.SharpnessSpinner:
                parameters.setSharpness(Integer.parseInt(parent.getItemAtPosition(position).toString()));
                break;
            case R.id.effectSpinner:
                parameters.setEffect(parent.getItemAtPosition(position).toString());
                break;
            case R.id.sizeSpinner:
                parameters.setPicturesize(parent.getItemAtPosition(position).toString());
                break;
    }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void onGoingBack(View view){

        Intent n=new Intent(getApplicationContext(),TestingCameraActivity.class);
        startActivity(n);

    }

}

