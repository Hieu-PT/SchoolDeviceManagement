
package hieupt.dtos;

import java.sql.Timestamp;


public class UserNotificationDTO {
    private String userID, content;
    private Timestamp time;

    public UserNotificationDTO(String userID, String content, Timestamp time) {
        this.userID = userID;
        this.content = content;
        this.time = time;
    }

    

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    
}
