
package hieupt.models;

import hieupt.conn.MyConnection;
import hieupt.dtos.RoomDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public RoomDAO() {
    }

    
    private void closeConnection() throws Exception{
        if(rs != null)
            rs.close();
        if(preStm != null)
            preStm.close();
        if(conn != null)
            conn.close();
    }
    
    public RoomDTO findByPrimaryKey(String room) throws Exception {
        RoomDTO dto = null;
        try {
            String sql = "Select RoomNo, Floor From Room Where RoomNo = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, room);
            rs = preStm.executeQuery();
            if(rs.next()){
                String roomNo = rs.getString("RoomNo");
                String floor = rs.getString("Floor");
                dto = new RoomDTO(roomNo, floor);
            }
        } finally{
            closeConnection();
        }
        return dto;
    }
    public List<RoomDTO> findByLikeRoomNo(String room) throws Exception {
        List<RoomDTO> result = null;
        String roomNo, floor;
        RoomDTO dto = null;
        try{
            String sql = "Select RoomNo, Floor From Room Where RoomNo like ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + room + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {                
                roomNo = rs.getString("RoomNo");
                floor = rs.getString("Floor");
                dto = new RoomDTO(roomNo, floor);
                result.add(dto);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    public List<RoomDTO> getAllRoom() throws Exception {
        List<RoomDTO> result = null;
        RoomDTO dto = null;
        String roomNo, floor;
        try {
            String sql = "Select RoomNo, Floor From Room";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                roomNo = rs.getString("RoomNo");
                floor = rs.getString("Floor");
                dto = new RoomDTO(roomNo, floor);
                result.add(dto);
            }
        } finally{
            closeConnection();
        }
        return result;
    }
    
    public boolean insert(RoomDTO dto) throws Exception {
        boolean check;
        try {
            String sql = "Insert Into Room(RoomNo, Floor) Values(?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getRoomNo());
            preStm.setString(2, dto.getFloor());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean update(RoomDTO dto) throws Exception {
        boolean check;
        try {
            String sql = "Update Room Set Floor = ? Where RoomNo = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(2, dto.getRoomNo());
            preStm.setString(1, dto.getFloor());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean delete(String room) throws Exception {
        boolean check;
        try {
            String sql = "Delete From Room Where RoomNo = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, room);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
