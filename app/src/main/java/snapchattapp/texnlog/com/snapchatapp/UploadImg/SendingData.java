package snapchattapp.texnlog.com.snapchatapp.UploadImg;

import java.util.ArrayList;

/**
 * Created by User on 17/12/2015.
 */
public class SendingData {

    private static SendingData instance;
    private String userId="";
    private ArrayList<String> senderId = new ArrayList<>();
    private String uploadedImage="";



    private int timer;

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    private String firstImage=" ";


        public static SendingData getInstance(){

            if(instance==null)
            {
                instance =new SendingData();
            }
            return instance;
        }

    public ArrayList getSenderId() {
        return senderId;
    }



    public String getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(String uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
