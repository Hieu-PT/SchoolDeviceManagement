
package hieupt.dtos;


public class DeviceDTO {
    private String deviceID, deviceName, deviceDescription, deviceType, room, deviceImage, deviceStatus;
    private String dateOfPurchase, warrantyPeriod;
    private int numberOfCorrections;

    public DeviceDTO(String deviceID, String deviceName, String deviceDescription, String deviceType, String room, String deviceImage, String deviceStatus, String dateOfPurchase, String warrantyPeriod) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.deviceDescription = deviceDescription;
        this.deviceType = deviceType;
        this.room = room;
        this.deviceImage = deviceImage;
        this.deviceStatus = deviceStatus;
        this.dateOfPurchase = dateOfPurchase;
        this.warrantyPeriod = warrantyPeriod;
    }

    public DeviceDTO(String deviceID, String deviceName, String deviceDescription, String deviceType, String room, String deviceImage, String deviceStatus, String dateOfPurchase, String warrantyPeriod, int numberOfCorrections) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.deviceDescription = deviceDescription;
        this.deviceType = deviceType;
        this.room = room;
        this.deviceImage = deviceImage;
        this.deviceStatus = deviceStatus;
        this.dateOfPurchase = dateOfPurchase;
        this.warrantyPeriod = warrantyPeriod;
        this.numberOfCorrections = numberOfCorrections;
    }

    

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

   

    public String getDeviceImage() {
        return deviceImage;
    }

    public void setDeviceImage(String deviceImage) {
        this.deviceImage = deviceImage;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    
    
    public int getNumberOfCorrections() {
        return numberOfCorrections;
    }

    public void setNumberOfCorrections(int numberOfCorrections) {
        this.numberOfCorrections = numberOfCorrections;
    }
    
    
}
