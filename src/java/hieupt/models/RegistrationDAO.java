
package hieupt.models;

import hieupt.conn.MyConnection;
import hieupt.dtos.RegistrationDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class RegistrationDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public RegistrationDAO() {
    }
    
    private void closeConnection() throws Exception{
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public String checkLogin(String username, String password) throws Exception {
        String role = "failed";
        String fullname;
        String sql = "Select Role, Fullname From Registration Where Username = ? and Password = ?";
        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if(rs.next()){
                fullname = rs.getString("Fullname");
                role = rs.getString("Role") + "." + fullname;
            }
        } finally {
            closeConnection();
        }
        return role;
    }
    
    public RegistrationDTO findByPrimaryKey(String key) throws Exception {
        RegistrationDTO dto = null;
        try{
            String sql = "Select Fullname, Role, WorkingRoom From Registration Where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, key);
            rs = preStm.executeQuery();
            if(rs.next()){
                String fullname = rs.getString("Fullname");
                String role = rs.getString("Role");
                String workingRoom = rs.getString("WorkingRoom");
                dto = new RegistrationDTO(key, fullname, role, workingRoom);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public List<RegistrationDTO> findByLikeName(String name) throws Exception {
        List<RegistrationDTO> result = null;
        String username, fullname, role, workingRoom;
        RegistrationDTO dto = null;
        try{
            String sql = "Select Username, Fullname, Role, WorkingRoom From Registration Where Fullname Like ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + name + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {                
                username = rs.getString("Username");
                fullname = rs.getString("Fullname");
                role = rs.getString("Role");
                workingRoom = rs.getString("WorkingRoom");
                dto = new RegistrationDTO(username, fullname, role, workingRoom);
                result.add(dto);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    
    public boolean insert(RegistrationDTO dto) throws Exception {
        boolean check = false;
        try{
            String sql = "Insert Into Registration(Username, Password, Fullname, Role) Values(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getPassword());
            preStm.setString(3, dto.getFullname());
            preStm.setString(4, dto.getRole());
            check = preStm.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public boolean delete(String username) throws Exception {
        boolean check = false;
        try{
            String sql = "Delete From Registration Where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            check = preStm.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public boolean update(RegistrationDTO dto) throws Exception {
        boolean check = false;
        try{
            String sql = "Update Registration Set Fullname = ?, Role = ?, WorkingRoom = ? Where Username = ?"
                    + " Insert INTO UserNotification(UserID, Content, Time) Values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getFullname());
            preStm.setString(2, dto.getRole());
            preStm.setString(3, dto.getWorkingroom());
            preStm.setString(4, dto.getUsername());
            preStm.setString(5, dto.getUsername());
            preStm.setString(6, "Something of your account has been updated by admin.");
            preStm.setTimestamp(7, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean register(RegistrationDTO dto) throws Exception {
        boolean check = false;
        try{
            String sql = "Insert Into Registration(Username, Password, Fullname, Role) Values(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getPassword());
            preStm.setString(3, dto.getFullname());
            preStm.setString(4, "User");
            check = preStm.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public boolean changePassword(String id, String newPass) throws Exception {
        boolean check = false;
        try{
            String sql = "Update Registration Set Password = ? Where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, newPass);
            preStm.setString(2, id);
            check = preStm.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
}
