package BL;

import DAL.DTO.UserDTO;

public class User {
    private int ID;
    private String userName;
    private String password;

    public User(UserDTO user){
        this.ID = user.getID();
        this.userName = user.getUserName();
        this.password = user.getPassword();
    }

    public User(int ID, String name, String pass){
        this.ID = ID;
        this.userName = name;
        this.password = pass;
    }

    public int getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("%s %s", ID, userName);
    }
}
