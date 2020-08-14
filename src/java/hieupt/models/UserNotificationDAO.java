
package hieupt.models;

import hieupt.conn.MyConnection;
import hieupt.dtos.UserNotificationDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserNotificationDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    private void closeConnection() throws Exception {
        if(rs != null){
            rs.close();
        }
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public List<UserNotificationDTO> getAllUserNotiByUserID(String userID) throws Exception {
        List<UserNotificationDTO> list = null;
        UserNotificationDTO dto = null;
        String content;
        Timestamp time;
        try {
            String sql = "Select Content, Time From UserNotification Where UserID = ? ORDER BY Time DESC;";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {                
                content = rs.getString("Content");
                time = rs.getTimestamp("Time");
                dto = new UserNotificationDTO(userID, content + " " + time, time);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
         return list;
    }
}
