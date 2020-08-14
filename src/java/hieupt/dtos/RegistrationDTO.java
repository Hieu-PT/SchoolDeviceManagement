
package hieupt.dtos;


public class RegistrationDTO {
    private String username, password, fullname, role, workingroom;

    public RegistrationDTO(String username, String password, String fullname, String role, String workingroom) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.workingroom = workingroom;
    }


    public RegistrationDTO(String username, String fullname, String role, String workingroom) {
        this.username = username;
        this.fullname = fullname;
        this.role = role;
        this.workingroom = workingroom;
    }
    
    
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getWorkingroom() {
        return workingroom;
    }

    public void setWorkingroom(String workingroom) {
        this.workingroom = workingroom;
    }
    
    
}
