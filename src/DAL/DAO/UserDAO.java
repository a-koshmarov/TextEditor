package DAL.DAO;

import BL.AuthorizationWizard;
import DAL.ConnectionFactory;
import DAL.DTO.UserDTO;

import java.sql.*;

public class UserDAO {
    public static final int INSERT = 0;
    public static final int UPDATE = 1;

    private static UserDTO extractUser(ResultSet rs) {
        UserDTO user = new UserDTO();

        try {
            user.setID(rs.getInt("ID"));
            user.setUserName(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static UserDTO getUser(String userName, String pass) {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "select * from Users where userName=? and password=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, pass);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return extractUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean exists(String userName){
        Connection conn = ConnectionFactory.getConnection();
        String sql="select * from Users where userName=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addUser(String userName, String pass){
        Connection conn = ConnectionFactory.getConnection();
        String sql="insert into Users(userName, password) values (?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, pass);
            int i = statement.executeUpdate();

            if (i==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateUser(UserDTO user){
        Connection conn = ConnectionFactory.getConnection();
        String sql="update Users set userName=?, password=? where ID=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getID());
            int i = statement.executeUpdate();

            if (i==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteUser(int ID){
        Connection conn = ConnectionFactory.getConnection();
        String sql="delete from Users where ID=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ID);
            int i = statement.executeUpdate();

            if (i==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
//        UserDTO user = getUser("jane", "54321");
//        System.out.println(user.getID() + user.getUserName() + user.getPassword());

    }

}
