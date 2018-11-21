package BL;

import DAL.DTO.UserDTO;

import java.util.UUID;

public class User {
    private static final int USER = 0;
    private static final int ADMIN = 0;

    private String ID = UUID.randomUUID().toString();
    private String userName;
    private String password;
    private int position = USER;

    public User(UserDTO user){
        this.ID = user.getID();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.position = user.getPosition();
    }

    public User(String name, String pass){
        this.userName = name;
        this.password = pass;
    }

    public User(String name, String pass, int position){
        this.userName = name;
        this.password = pass;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String  getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO getUserDTO() {
        return new UserDTO(ID, userName, password, position);
    }

    @Override
    public String toString() {
        return String.format("%s %s", ID, userName);
    }
}
