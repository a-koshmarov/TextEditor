package DAL.DTO;

import BL.User;

public class UserDTO {
    private int ID;
    private String userName;
    private String password;

    public UserDTO(){
    }

    public UserDTO(User user){
        this.ID = user.getID();
        this.userName = user.getUserName();
        this.password = user.getPassword();
    }

    public UserDTO(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
