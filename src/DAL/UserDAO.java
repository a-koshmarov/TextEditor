package DAL;

import DTO.UserDTO;

import java.sql.*;

public class UserDAO {
    public static final int INSERT = 0;
    public static final int UPDATE = 1;
    public static final int DELETE = 2;


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

    public static boolean actionWithUser(int state, UserDTO user) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement statement;
        String sql;
        try {
            switch (state) {
                case INSERT:
                    sql = "insert into Users(userName, password) values (?, ?)";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, user.getUserName());
                    statement.setString(2, user.getPassword());
                    break;
                case UPDATE:
                    sql = "update Users set userName=?, password=? where ID=?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, user.getUserName());
                    statement.setString(2, user.getPassword());
                    statement.setInt(3, user.getID());
                    break;
                default:
                    return false;
            }
            int i = statement.executeUpdate();

            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
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
