
package hieupt.dtos;

import java.sql.Timestamp;


public class FeedbackDTO {
    private String feedbackID, deviceID, sender, requiredContent, repairer, repairContent, result;
    private Timestamp time, repairFrom, repairTo;

    
    public FeedbackDTO(String deviceID, String sender, String requiredContent) {
        this.deviceID = deviceID;
        this.sender = sender;
        this.requiredContent = requiredContent;
    }

    public FeedbackDTO(String deviceID, String sender, String requiredContent, String repairer, String repairContent, String result, Timestamp time) {
        this.deviceID = deviceID;
        this.sender = sender;
        this.requiredContent = requiredContent;
        this.repairer = repairer;
        this.repairContent = repairContent;
        this.result = result;
        this.time = time;
    }

    public FeedbackDTO(String feedbackID, String deviceID, String sender, String requiredContent, String repairer, String repairContent, String result, Timestamp time) {
        this.feedbackID = feedbackID;
        this.deviceID = deviceID;
        this.sender = sender;
        this.requiredContent = requiredContent;
        this.repairer = repairer;
        this.repairContent = repairContent;
        this.result = result;
        this.time = time;
    }

    public FeedbackDTO(String feedbackID, String repairer) {
        this.feedbackID = feedbackID;
        this.repairer = repairer;
    }

    public FeedbackDTO(String feedbackID, String deviceID, String sender, String requiredContent, String result, Timestamp time, Timestamp repairFrom) {
        this.feedbackID = feedbackID;
        this.deviceID = deviceID;
        this.sender = sender;
        this.requiredContent = requiredContent;
        this.result = result;
        this.time = time;
        this.repairFrom = repairFrom;
    }
    
    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRequiredContent() {
        return requiredContent;
    }

    public void setRequiredContent(String requiredContent) {
        this.requiredContent = requiredContent;
    }

    public String getRepairer() {
        return repairer;
    }

    public void setRepairer(String repairer) {
        this.repairer = repairer;
    }

    public String getRepairContent() {
        return repairContent;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getRepairFrom() {
        return repairFrom;
    }

    public void setRepairFrom(Timestamp repairFrom) {
        this.repairFrom = repairFrom;
    }

    public Timestamp getRepairTo() {
        return repairTo;
    }

    public void setRepairTo(Timestamp repairTo) {
        this.repairTo = repairTo;
    }
    
    
}
