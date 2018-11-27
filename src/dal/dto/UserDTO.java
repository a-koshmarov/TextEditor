package dal.dto;

public class UserDTO {
    private String ID;
    private String userName;
    private String password;
    private int position;

    public UserDTO(String ID, String userName, String password, int position){
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.position = position;
    }

    public String getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getPosition() {
        return position;
    }

}
