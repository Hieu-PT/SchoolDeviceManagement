
package hieupt.models;

import hieupt.conn.MyConnection;
import hieupt.dtos.FeedbackDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FeedbackDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public FeedbackDAO() {
    }

    private void closeConnection() throws Exception{
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public FeedbackDTO findByPrimaryKey(String feedbackID) throws Exception {
        FeedbackDTO dto = null;
        String  deviceID, sender, request, result;
        Timestamp time, repairFrom;
        try {
            String sql = "Select DeviceID, Sender, RequiredContent, Time, Result, RepairFrom From Feedback Where FeedbackId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, feedbackID);
            rs = preStm.executeQuery();
            if(rs.next()){
                deviceID = rs.getString("DeviceID");
                sender = rs.getString("Sender");
                request = rs.getString("RequiredContent");
                time = rs.getTimestamp("Time");
                result = rs.getString("Result");
                repairFrom = rs.getTimestamp("RepairFrom");
                dto = new FeedbackDTO(feedbackID, deviceID, sender, request, result, time, repairFrom);
            }
        } finally{
            closeConnection();
        }
        return dto;
    }
    
    public List<FeedbackDTO> getAllFeedbackByDeviceID(String deviceID) throws Exception {
        List<FeedbackDTO> list = null;
        FeedbackDTO dto = null;
        String sender, request, repairer, response, result;
        Timestamp time;
        try {
            String sql = "Select Sender, RequiredContent, Time, Repairer, RepairContent, Result From Feedback Where DeviceID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, deviceID);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                sender = rs.getString("Sender");
                request = rs.getString("RequiredContent");
                time = rs.getTimestamp("Time");
                repairer = rs.getString("Repairer");
                response = rs.getString("RepairContent");
                result = rs.getString("Result");
                dto = new FeedbackDTO(deviceID, sender, request, repairer, response, result, time);
                list.add(dto);
            }
        } finally{
            closeConnection();
        }
        return list;
    }
    
    public List<FeedbackDTO> getAllFeedbackWaitResponse() throws Exception {
        List<FeedbackDTO> list = null;
        FeedbackDTO dto = null;
        String feedbackID, deviceID, sender, request, repairer, response, result;
        Timestamp time;
        try {
            String sql = "Select FeedbackID, DeviceID, Sender, RequiredContent, Time, Repairer, RepairContent, Result From Feedback Where Result = 'Pending' OR Result = 'Accepted'";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                feedbackID = rs.getString("FeedbackID");
                deviceID = rs.getString("DeviceID");
                sender = rs.getString("Sender");
                request = rs.getString("RequiredContent");
                time = rs.getTimestamp("Time");
                repairer = rs.getString("Repairer");
                response = rs.getString("RepairContent");
                result = rs.getString("Result");
                dto = new FeedbackDTO(feedbackID,deviceID, sender, request, repairer, response, result, time);
                list.add(dto);
            }
        } finally{
            closeConnection();
        }
        return list;
    }
    
    public boolean insert(FeedbackDTO dto) throws Exception {
        boolean check;
        Calendar cal = Calendar.getInstance();
        Timestamp time = new Timestamp(cal.getTimeInMillis());
        String tmp = dto.getDeviceID() + time;
        String[] feedbackID = tmp.split("[.]");
        try {
            String sql = "Insert Into Feedback(FeedbackID, DeviceID, Sender, RequiredContent, Time, Result) Values(?,?,?,?,?,?)"
                    + " Insert Into Notifications(Notification, Time) Values(?,?)"
                    + " Insert Into UserNotification(UserID, Content, Time) Values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, feedbackID[0]);
            preStm.setString(2, dto.getDeviceID());
            preStm.setString(3, dto.getSender());
            preStm.setString(4, dto.getRequiredContent());
            preStm.setTimestamp(5, time);
            preStm.setString(6, "Pending");
            preStm.setString(7, "User " + dto.getSender() + " give feed back for " + dto.getDeviceID() + ": " + dto.getRequiredContent() + "    " + time);
            preStm.setTimestamp(8, time);
            preStm.setString(9, dto.getSender());
            preStm.setString(10, "Your feedback about " + dto.getDeviceID() + "(" + dto.getRequiredContent() + ") has been sent.");
            preStm.setTimestamp(11, time);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean accept(FeedbackDTO dto, String userID) throws Exception {
        boolean check;
        Calendar cal = Calendar.getInstance();
        Timestamp time = new Timestamp(cal.getTimeInMillis());
        try {
            String sql = "Update Feedback Set Repairer = ?, RepairFrom = ?, Result = ? Where FeedbackID = ?"
                    + " Insert INTO UserNotification(UserID, Content, Time) Values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getRepairer());
            preStm.setTimestamp(2, time);
            preStm.setString(3, "Accepted");
            preStm.setString(4, dto.getFeedbackID());
            preStm.setString(5, userID);
            preStm.setString(6, "Your feedback has been accepted.");
            preStm.setTimestamp(7, time);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean finish(String id, String user, String response, String repairFrom, int fixTimes, String deviceID, String sender) throws Exception {
        boolean check;
        Calendar cal = Calendar.getInstance();
        Timestamp time = new Timestamp(cal.getTimeInMillis());
        try {
            if(repairFrom.length() == 0){
                String sql = "Update Feedback Set Repairer = ?, RepairContent = ?, RepairFrom = ?, RepairTo = ?, Result = ? Where FeedbackID = ?"
                        + " Update Device Set NumberOfCorrections = ? Where DeviceID = ?"
                        + " Insert INTO UserNotification(UserID, Content, Time) Values(?,?,?)";
                conn = MyConnection.getMyConnection();
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, user);
                preStm.setString(2, response);
                preStm.setTimestamp(3, time);
                preStm.setTimestamp(4, time);
                preStm.setString(5, "Fixed");
                preStm.setString(6, id);
                preStm.setInt(7, fixTimes + 1);
                preStm.setString(8, deviceID);
                preStm.setString(9, sender);
                preStm.setString(10, "Your feedback has been processed.");
                preStm.setTimestamp(11, time);
            } else {
                String sql = "Update Feedback Set RepairContent = ?, RepairTo = ?, Result = ? Where FeedbackID = ?"
                        + " Update Device Set NumberOfCorrections = ? Where DeviceID = ?"
                        + " Insert INTO UserNotification(UserID, Content, Time) Values(?,?,?)";
                conn = MyConnection.getMyConnection();
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, response);
                preStm.setTimestamp(2, time);
                preStm.setString(3, "Fixed");
                preStm.setString(4, id);
                preStm.setInt(5, fixTimes + 1);
                preStm.setString(6, deviceID);
                preStm.setString(7, sender);
                preStm.setString(8, "Your feedback has been processed.");
                preStm.setTimestamp(9, time);
            }
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean reject(FeedbackDTO dto, String userID) throws Exception {
        boolean check;
        Calendar cal = Calendar.getInstance();
        Timestamp time = new Timestamp(cal.getTimeInMillis());
        try {
            String sql = "Update Feedback Set Repairer = ?, RepairFrom = ?, Result = ? Where FeedbackID = ?"
                    + " Insert INTO UserNotification(UserID, Content, Time) Values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getRepairer());
            preStm.setTimestamp(2, time);
            preStm.setString(3, "Rejected");
            preStm.setString(4, dto.getFeedbackID());
            preStm.setString(5, userID);
            preStm.setString(6, "Your feedback has been rejected.");
            preStm.setTimestamp(7, time);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public void checkExpired() throws Exception {
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        Timestamp twoDayAfter;
        try {
            List<FeedbackDTO> list = getAllFeedbackWaitResponse();
            for (FeedbackDTO feedbackDTO : list) {
                twoDayAfter = new Timestamp(feedbackDTO.getTime().getTime() + 86400000*2);
                if(twoDayAfter.before(now)){
                    expired(feedbackDTO, feedbackDTO.getSender());
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public boolean expired(FeedbackDTO dto, String userID) throws Exception {
        boolean check;
        Calendar cal = Calendar.getInstance();
        Timestamp time = new Timestamp(cal.getTimeInMillis());
        try {
            String sql = "Update Feedback Set Result = ? Where FeedbackID = ?"
                    + " Insert INTO UserNotification(UserID, Content, Time) Values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "Expired");
            preStm.setString(2, dto.getFeedbackID());
            preStm.setString(3, userID);
            preStm.setString(4, "Your feedback has been expired after 2 days.");
            preStm.setTimestamp(5, time);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
