
package hieupt.models;

import hieupt.conn.MyConnection;
import hieupt.dtos.NotificationDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class NotificationDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public NotificationDAO() {
    }
    
    private void closeConnection() throws Exception{
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public List<NotificationDTO> getAllNotification() throws Exception {
        List<NotificationDTO> result = null;
        NotificationDTO dto;
        try {
            String sql = "Select Notification From Notifications ORDER BY Time DESC;";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {                
                String noti = rs.getString("Notification");
                dto = new NotificationDTO(noti);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
