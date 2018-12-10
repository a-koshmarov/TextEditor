package dal.dao;

import dal.Connector;
import dal.dto.UserDTO;

import java.sql.*;

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
        String sql = "insert into Users values (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getID());
        statement.setString(2, user.getUserName());
        statement.setString(3, user.getPassword());
        statement.setInt(4, user.getPosition());
        statement.executeUpdate();
    }

    @Override
    public void connect() throws SQLException {

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public UserDTO get(String ID) throws SQLException {
        return null;
    }

    @Override
    public void update(UserDTO user) throws SQLException {
        String sql = "update Users set userName=?, password=?, positon=? where ID=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getPosition());
        statement.setString(4, user.getID());
        statement.executeUpdate();

    }

    @Override
    public void delete(UserDTO user) throws SQLException {
        String sql = "delete from Users where ID=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getID());
        statement.executeUpdate();
    }

    public UserDTO get(UserDTO user) throws SQLException {
        System.out.println("Getting user");
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
        return new UserDTO(
                rs.getString("ID"),
                rs.getString("userName"),
                rs.getString("password"),
                rs.getInt("position"));
    }
}
