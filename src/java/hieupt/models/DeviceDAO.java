
package hieupt.models;

import hieupt.conn.MyConnection;
import hieupt.dtos.DeviceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DeviceDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public DeviceDAO() {
    }

    
    private void closeConnection() throws Exception{
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public DeviceDTO findByPrimaryKey(String key) throws Exception {
        DeviceDTO dto = null;
        try{
            String sql = "Select DeviceName, DeviceDescription, DeviceType, RoomNo, DeviceImage, DateOfPurchase, WarrantyPeriod, NumberOfCorrections, DeviceStatus"
                    + " From Device dv JOIN DevicePositionHistory dp ON dv.DevicePositionID = dp.DevicePositionID Where dv.DeviceID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, key);
            rs = preStm.executeQuery();
            if(rs.next()){
                String name = rs.getString("DeviceName");
                String des = rs.getString("DeviceDescription");
                String type  = rs.getString("DeviceType");
                String room = rs.getString("RoomNo");
                String image = rs.getString("DeviceImage");
                String purchase = rs.getString("DateOfPurchase");
                String warranty = rs.getString("WarrantyPeriod");
                int corrections = rs.getInt("NumberOfCorrections");
                String status = rs.getString("DeviceStatus");
                dto = new DeviceDTO(key, name, des, type, room, image, status, purchase, warranty, corrections);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public String getPositionID(String key) throws Exception {
        //dung trong move Device
        String position = null;
        try {
            String sql = "Select DevicePositionID From Device Where DeviceID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, key);
            rs = preStm.executeQuery();
            if(rs.next()){
                position = rs.getString("DevicePositionID");
            }
        } finally {
            closeConnection();
        }
        return position;
    }
    
    public List<DeviceDTO> findByLikeName(String name) throws Exception {
        List<DeviceDTO> result = null;
        String deviceID, deviceName, deviceDescription, deviceType, room, deviceImage, deviceStatus;
        String dateOfPurchase, warrantyPeriod;
        int numberOfCorrections;
        DeviceDTO dto;
        try{ 
            String sql = "Select dv.DeviceID, DeviceName, DeviceDescription, DeviceType, RoomNo, DeviceImage, DateOfPurchase, WarrantyPeriod, NumberOfCorrections, DeviceStatus"
                    + " From Device dv JOIN DevicePositionHistory dp ON dv.DevicePositionID = dp.DevicePositionID Where DeviceName Like ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + name + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {                
                deviceID = rs.getString("DeviceID");
                deviceName = rs.getString("DeviceName");
                deviceDescription = rs.getString("DeviceDescription");
                deviceType = rs.getString("DeviceType");
                room = rs.getString("RoomNo");
                deviceImage = rs.getString("DeviceImage");
                dateOfPurchase = rs.getString("DateOfPurchase");
                warrantyPeriod = rs.getString("WarrantyPeriod");
                numberOfCorrections = rs.getInt("NumberOfCorrections");
                deviceStatus = rs.getString("DeviceStatus");
                dto = new DeviceDTO(deviceID, deviceName, deviceDescription, deviceType, room, deviceImage, deviceStatus, dateOfPurchase, warrantyPeriod, numberOfCorrections);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<DeviceDTO> findByLikeNameStatistical(String name) throws Exception {
        List<DeviceDTO> result = null;
        String deviceID, deviceName, deviceDescription, deviceType, room, deviceImage, deviceStatus;
        String dateOfPurchase, warrantyPeriod;
        int numberOfCorrections;
        DeviceDTO dto;
        try{ 
            String sql = "Select dv.DeviceID, DeviceName, DeviceDescription, DeviceType, RoomNo, DeviceImage, DateOfPurchase, WarrantyPeriod, NumberOfCorrections, DeviceStatus"
                    + " From Device dv JOIN DevicePositionHistory dp ON dv.DevicePositionID = dp.DevicePositionID Where DeviceName Like ? ORDER BY NumberOfCorrections DESC;";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + name + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {                
                deviceID = rs.getString("DeviceID");
                deviceName = rs.getString("DeviceName");
                deviceDescription = rs.getString("DeviceDescription");
                deviceType = rs.getString("DeviceType");
                room = rs.getString("RoomNo");
                deviceImage = rs.getString("DeviceImage");
                dateOfPurchase = rs.getString("DateOfPurchase");
                warrantyPeriod = rs.getString("WarrantyPeriod");
                numberOfCorrections = rs.getInt("NumberOfCorrections");
                deviceStatus = rs.getString("DeviceStatus");
                dto = new DeviceDTO(deviceID, deviceName, deviceDescription, deviceType, room, deviceImage, deviceStatus, dateOfPurchase, warrantyPeriod, numberOfCorrections);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
        public List<DeviceDTO> findByLikeNameUser(String username, String name) throws Exception {
        List<DeviceDTO> result = null;
        String deviceID, deviceName, deviceDescription, deviceType, room, deviceImage, deviceStatus;
        String dateOfPurchase, warrantyPeriod;
        int numberOfCorrections;
        DeviceDTO dto; 
        try{ 
            String sql = "Select dv.DeviceID, DeviceName, DeviceDescription, DeviceType, RoomNo, DeviceImage, DateOfPurchase, WarrantyPeriod, NumberOfCorrections, DeviceStatus\n" +
                        "From Registration rg JOIN DevicePositionHistory dp ON rg.WorkingRoom = dp.RoomNo\n" +
                        "JOIN Device dv ON dp.DeviceID = dv.DeviceID\n" +
                        "Where Username = ? And DeviceName LIKE ? And dv.DevicePositionID = dp.DevicePositionID";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, "%" + name + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {                
                deviceID = rs.getString("DeviceID");
                deviceName = rs.getString("DeviceName");
                deviceDescription = rs.getString("DeviceDescription");
                deviceType = rs.getString("DeviceType");
                room = rs.getString("RoomNo");
                deviceImage = rs.getString("DeviceImage");
                dateOfPurchase = rs.getString("DateOfPurchase");
                warrantyPeriod = rs.getString("WarrantyPeriod");
                numberOfCorrections = rs.getInt("NumberOfCorrections");
                deviceStatus = rs.getString("DeviceStatus");
                dto = new DeviceDTO(deviceID, deviceName, deviceDescription, deviceType, room, deviceImage, deviceStatus, dateOfPurchase, warrantyPeriod, numberOfCorrections);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean insert(DeviceDTO dto) throws Exception {
        boolean check = false;
        Calendar cal = Calendar.getInstance();
        Timestamp time = new Timestamp(cal.getTimeInMillis());
        String tmp = dto.getRoom() + time;
        String[] devicePositionID = tmp.split("[.]");
        try{
            String sql = "Insert Into Device(DeviceID, DeviceName , DeviceDescription, DeviceType, DevicePositionID, DeviceImage, DateOfPurchase, WarrantyPeriod, DeviceStatus) Values(?,?,?,?,?,?,?,?,?)\n"
                    + "Insert Into DevicePositionHistory(DevicePositionID, DeviceID, RoomNo, [From]) Values(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getDeviceID());
            preStm.setString(2, dto.getDeviceName());
            preStm.setString(3, dto.getDeviceDescription());
            preStm.setString(4, dto.getDeviceType());
            preStm.setString(5, devicePositionID[0]);
            preStm.setString(6, dto.getDeviceImage());
            preStm.setString(7, dto.getDateOfPurchase());
            preStm.setString(8, dto.getWarrantyPeriod());
            preStm.setString(9, dto.getDeviceStatus());
            preStm.setString(10, devicePositionID[0]);
            preStm.setString(11, dto.getDeviceID());
            preStm.setString(12, dto.getRoom());
            preStm.setTimestamp(13, time);
            check = preStm.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public boolean delete(String deviceID) throws Exception {
        boolean check = false;
        try{
            String sql = "Delete From Device Where DeviceID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, deviceID);
            check = preStm.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public boolean update(DeviceDTO dto) throws Exception {
        boolean check = false;
        try{
            String sql = "Update Device Set DeviceName = ?, DeviceDescription = ?, DeviceType = ?, DeviceImage = ?,"
                    + " DateOfPurchase = ?, WarrantyPeriod = ?, DeviceStatus = ? Where DeviceID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getDeviceName());
            preStm.setString(2, dto.getDeviceDescription());
            preStm.setString(3, dto.getDeviceType());
            preStm.setString(4, dto.getDeviceImage());
            preStm.setString(5, dto.getDateOfPurchase());
            preStm.setString(6, dto.getWarrantyPeriod());
            preStm.setString(7, dto.getDeviceStatus());
            preStm.setString(8, dto.getDeviceID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean validate(String date) throws Exception {
        boolean check = false;
        String[] dateArray = date.split("[/]");
        int day = Integer.parseInt(dateArray[1]);
        int month = Integer.parseInt(dateArray[0]);
        int year = Integer.parseInt(dateArray[2]);
        if(dateArray.length != 3){
            return check;
        }else {
            if((day < 1) || (day > 31) || (month < 1) || (month > 12))
                return check;
            if(month == 4 || month == 6 || month == 9 || month == 11){
                if(day > 30) return check;
            } else if(month == 2){
                if(year % 4 == 0){
                    if(day > 29) return check;
                } else {
                    if(day > 28) return check;
                }
            }
        }
        return true;
    }
}
