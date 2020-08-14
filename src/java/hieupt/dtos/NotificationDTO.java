
package hieupt.dtos;

import java.sql.Timestamp;


public class NotificationDTO {
    private String notification;
    private Timestamp time;

    public NotificationDTO(String notification, Timestamp time) {
        this.notification = notification;
        this.time = time;
    }

    public NotificationDTO(String notification) {
        this.notification = notification;
    }
    
    

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    
}
