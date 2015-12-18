package snapchattapp.texnlog.com.snapchatapp.UploadImg;

/**
 * Created by User on 17/12/2015.
 */
public class SendingData {

    private static SendingData instance;
    private String userId="";
    private String senderId="";
    private String uploadedImage="";

        public static SendingData getInstance(){

            if(instance==null)
            {
                instance =new SendingData();
            }
            return instance;
        }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
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
}
