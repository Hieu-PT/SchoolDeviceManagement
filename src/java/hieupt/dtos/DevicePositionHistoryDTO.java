
package hieupt.dtos;

import java.sql.Timestamp;


public class DevicePositionHistoryDTO {
    private String devicePositionID, DeviceID, roomNo, userMove, reasonMove;
    private Timestamp from, to;

    public DevicePositionHistoryDTO(String devicePositionID, String DeviceID, String roomNo, String userMove, String reasonMove, Timestamp from, Timestamp to) {
        this.devicePositionID = devicePositionID;
        this.DeviceID = DeviceID;
        this.roomNo = roomNo;
        this.userMove = userMove;
        this.reasonMove = reasonMove;
        this.from = from;
        this.to = to;
    }

    public String getDevicePositionID() {
        return devicePositionID;
    }

    public void setDevicePositionID(String devicePositionID) {
        this.devicePositionID = devicePositionID;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String DeviceID) {
        this.DeviceID = DeviceID;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getUserMove() {
        return userMove;
    }

    public void setUserMove(String userMove) {
        this.userMove = userMove;
    }

    public String getReasonMove() {
        return reasonMove;
    }

    public void setReasonMove(String reasonMove) {
        this.reasonMove = reasonMove;
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }
    
    
}
