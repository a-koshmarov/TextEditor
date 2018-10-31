package DAL.DAO;

import DAL.Connector;
import DAL.DTO.UserDTO;

import java.sql.*;
import java.util.Set;

public class UserDAO implements DAO<UserDTO> {

    private Connection conn;

    public UserDAO(){
        conn = Connector.getConnection();
    }

    public boolean exists(String userName) throws SQLException {
        String sql = "select * from Users where userName=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, userName);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return true;
        }

        return false;
    }

    @Override
    public void add(UserDTO user) throws SQLException {
        String sql = "insert into Users(userName, password) values (?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        statement.executeUpdate();
    }

    @Override
    public void update(UserDTO user) throws SQLException {
        String sql = "update Users set userName=?, password=? where ID=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getID());
        statement.executeUpdate();

    }

    @Override
    public void delete(UserDTO user) throws SQLException {
        String sql = "delete from Users where ID=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, user.getID());
        statement.executeUpdate();
    }

    @Override
    public UserDTO get(UserDTO user) throws SQLException {
        String sql = "select * from Users where userName=? and password=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return extract(rs);
        }
        return null;
    }

    @Override
    public UserDTO extract(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();

        user.setID(rs.getInt("ID"));
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));

        return user;
    }
}
