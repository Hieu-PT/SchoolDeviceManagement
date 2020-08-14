
package hieupt.dtos;


public class RoomDTO {
    private String roomNo, floor;

    public RoomDTO(String roomNo) {
        this.roomNo = roomNo;
    }

    public RoomDTO(String roomNo, String floor) {
        this.roomNo = roomNo;
        this.floor = floor;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
    
    
}
