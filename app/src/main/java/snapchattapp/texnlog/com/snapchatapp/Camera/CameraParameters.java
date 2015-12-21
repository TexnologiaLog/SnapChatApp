package snapchattapp.texnlog.com.snapchatapp.Camera;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by User on 3/12/2015.
 */
public class CameraParameters {

    private static CameraParameters instance;
    private String redeye,facedetection,picturesize,effect,currentvalue;
    private int contrast,sharpness,saturation;
    private static String parameters;
    private boolean settingsChanged;


    public static CameraParameters getInstance()
    {

        if (instance == null)
        {

            instance = new CameraParameters();

        }
        return instance;
    }

    public static void init(String param)
    {

        if(parameters==null)
        {

            parameters=param;

        }
    }

    private  HashMap seperateParams(String parameters)throws ArrayIndexOutOfBoundsException{

        HashMap table=new HashMap();
        String[] pairs=parameters.split(";");
        String novalue="no value found";

        for(int i=0;i<pairs.length;i++)
        {

            String pair = pairs[i];
            try {

                String[] splitter=pair.split("=");
                table.put(splitter[0],splitter[1]);

            }catch(ArrayIndexOutOfBoundsException e){

                table.put(pair,novalue);

            }

        }

        return table;
    }

    public ArrayList parameterValues(String parameter){
        Iterator it =seperateParams(parameters).entrySet().iterator();
        ArrayList array =new ArrayList();
        array.add("choose an option");

        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry)it.next();

            if(pair.getKey().toString().equalsIgnoreCase(parameter))
            {

                String values = pair.getValue().toString();
                String[] stringsplit = values.split(",");

                for(int i=0;i<stringsplit.length;i++){

                    array.add(stringsplit[i]);

                }
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        return array;
    }

    public ArrayList parameterNumericValues(String parameter){

        Integer i=0;
        ArrayList array=new ArrayList();
        array.add("choose an option");
        String step=null;
        String max=null;
        String min=null;
        String currentvalue=null;
        int value=0;
        Iterator it=seperateParams(parameters).entrySet().iterator();

        while (it.hasNext() ) {
            Map.Entry pair = (Map.Entry)it.next();

            if(pair.getKey().toString().equalsIgnoreCase(parameter.trim()+"-step"))
            {

                step =pair.getValue().toString();
                i++;

            }
            if(pair.getKey().toString().equalsIgnoreCase("min-"+parameter.trim())){

                min=pair.getValue().toString();
                i++;

            }
            if(pair.getKey().toString().equalsIgnoreCase("max-"+parameter.trim())){


                String key=pair.getKey().toString();
                max=pair.getValue().toString();
                i++;

            }

            if(i==3)
            {
                int j=0;
                value=Integer.parseInt(min);

                while(j<=Integer.parseInt(max))
                {

                    array.add(value);
                    value=value+Integer.parseInt(step);
                    j=j+Integer.parseInt(step);

                }
                i=4;
            }

            it.remove(); // avoids a ConcurrentModificationException
        }
        return array;
    }


    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getRedeye() {
        return redeye;
    }

    public void setRedeye(String redeye) {
        this.redeye = redeye;
    }

    public String getFacedetection() {
        return facedetection;
    }

    public void setFacedetection(String facedetection) {
        this.facedetection = facedetection;
    }

    public String getPicturesize() {
        return picturesize;
    }

    public void setPicturesize(String picturesize) {
        this.picturesize = picturesize;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getContrast() {
        return contrast;
    }

    public void setContrast(int contrast) {
        this.contrast = contrast;
    }

    public int getSharpness() {
        return sharpness;
    }

    public void setSharpness(int sharpness) {
        this.sharpness = sharpness;
    }

    public int getSaturation() {
        return saturation;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public ArrayList getStringValues(String string)
    {
        return parameterValues(string);
    }

    public ArrayList getNumericValues(String string){
        ArrayList arrayList=parameterNumericValues(string);
            return arrayList;

    }

    public void SettingsState(boolean string){

       this.settingsChanged= string;
    }
    public boolean getState(){
        return settingsChanged;
    }

}
