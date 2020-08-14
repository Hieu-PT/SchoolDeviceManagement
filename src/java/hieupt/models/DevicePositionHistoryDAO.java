
package hieupt.models;

import hieupt.conn.MyConnection;
import hieupt.dtos.DevicePositionHistoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DevicePositionHistoryDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public DevicePositionHistoryDAO() {
    }
    
    private void closeConnection() throws Exception{
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public boolean update(String positionID, String userMove, String reasonMove, String moveTo, String deviceID) throws Exception {
        boolean check = false;
        try {
            Calendar cal = Calendar.getInstance();
            Timestamp time = new Timestamp(cal.getTimeInMillis());
            String tmp = moveTo + time;
            String[] devicePositionID = tmp.split("[.]");
            String sql = "Update DevicePositionHistory Set UserMove = ?, ReasonMove = ?, [To] = ? Where DevicePositionID = ? "
                    + "Insert Into DevicePositionHistory(DevicePositionID, DeviceID, RoomNo, [From]) Values(?,?,?,?) "
                    + "Update Device Set DevicePositionID = ? Where DeviceID =?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userMove);
            preStm.setString(2, reasonMove);
            preStm.setTimestamp(3, time);
            preStm.setString(4, positionID);
            preStm.setString(5, devicePositionID[0]);
            preStm.setString(6, deviceID);
            preStm.setString(7, moveTo);
            preStm.setTimestamp(8, time);
            preStm.setString(9, devicePositionID[0]);
            preStm.setString(10, deviceID);
            check = preStm.executeUpdate() > 0;
        }finally {
            closeConnection();
        }
        return check;
    }
    
    public List<DevicePositionHistoryDTO> getPositionHistoryByDeviceID(String deviceID) throws Exception {
        List<DevicePositionHistoryDTO> result = null;
        DevicePositionHistoryDTO dto;
        String positionID, room, userMove, reasonMove;
        Timestamp from , to;
        try {
            String sql = "Select DevicePositionID, RoomNo, [From], [To], UserMove, ReasonMove From DevicePositionHistory Where DeviceID = ? ORDER BY [From];";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, deviceID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            
            while (rs.next()) {                
                positionID = rs.getString("DevicePositionID");
                room = rs.getString("RoomNo");
                from = rs.getTimestamp("From");
                to = rs.getTimestamp("To");
                userMove = rs.getString("UserMove");
                reasonMove = rs.getString("ReasonMove");
                dto = new DevicePositionHistoryDTO(positionID, deviceID, room, userMove, reasonMove, from, to);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
