
package hieupt.dtos;


public class RegistrationErrorObj {
    private String usernameError, passwordError, confirmError, fullnameError, roleError, workingRoomError, oldPassError, newPassError;

    public RegistrationErrorObj() {
    }

    public String getOldPassError() {
        return oldPassError;
    }

    public void setOldPassError(String oldPassError) {
        this.oldPassError = oldPassError;
    }

    public String getNewPassError() {
        return newPassError;
    }

    public void setNewPassError(String newPassError) {
        this.newPassError = newPassError;
    }

    
    
    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getFullnameError() {
        return fullnameError;
    }

    public void setFullnameError(String fullnameError) {
        this.fullnameError = fullnameError;
    }

    public String getRoleError() {
        return roleError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    public String getWorkingRoomError() {
        return workingRoomError;
    }

    public void setWorkingRoomError(String workingRoomError) {
        this.workingRoomError = workingRoomError;
    }
    
    
}
