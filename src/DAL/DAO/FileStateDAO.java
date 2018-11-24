package DAL.DAO;

import DAL.Connector;
import DAL.DTO.FileStateDTO;
import Utility.Tuple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FileStateDAO implements DAO<FileStateDTO> {

    private Connection conn = Connector.getConnection();

    @Override
    public void add(FileStateDTO file) throws SQLException {
        String sql = "insert into FileState values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, file.getFileName());
        statement.setString(2, file.getID());
        statement.setString(3, file.getPID());
        statement.setString(4, file.getOID());
        statement.setString(5, file.getContent());
        statement.setString(6, file.getDateTime());
        statement.setString(7, file.getVersion());
        statement.setString(8, file.getMessage());
        statement.setInt(9, file.getAccess());
        statement.setInt(10, file.getPersonal());

        statement.executeUpdate();
        conn.commit();
    }

    @Override
    public void update(FileStateDTO file) throws SQLException {
        String sql = "update FileState set fileName=?, content=?, message=?, access=?  where ID=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, file.getFileName());
        statement.setString(2, file.getContent());
        statement.setString(3, file.getMessage());
        statement.setInt(4, file.getAccess());
        statement.setString(5, file.getID());

        statement.executeUpdate();
        conn.commit();
    }

    @Override
    public void delete(FileStateDTO file) throws SQLException {
        String sql = "delete from FileState where ID=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, file.getID());
        statement.executeUpdate();
        conn.commit();
    }

    @Override
    public FileStateDTO get(FileStateDTO file) throws SQLException {
        String sql = "select * from FileState where ID = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, file.getID());
        ResultSet rs = statement.executeQuery();
        conn.commit();

        if (rs.next()) {
            return extract(rs);
        }
        return null;
    }

    @Override
    public FileStateDTO extract(ResultSet rs) throws SQLException {
        return new FileStateDTO(
                rs.getString("fileName"),
                rs.getString("ID"),
                rs.getString("PID"),
                rs.getString("OID"),
                rs.getString("content"),
                rs.getString("dateTime"),
                rs.getString("version"),
                rs.getString("message"),
                rs.getInt("access"),
                rs.getInt("personal") == 1);
    }

    public Set<FileStateDTO> getAllVersions(String PID) throws SQLException {
        System.out.println("Transaction: Getting all versions of branch: " + PID);

        String sql = "select * from FileState where PID=?";
        Set<FileStateDTO> files = new HashSet<>();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, PID);
        ResultSet rs = statement.executeQuery();
        conn.commit();

        while (rs.next()) {
            files.add(extract(rs));
        }
        return files;
    }

    public static Set<Tuple<String, String>> getAllLastVersions() throws SQLException {
        Connection conn = Connector.getConnection();
        String sql = "select fileName, PID, ID, max(version) from FileState group by PID";
        Set<Tuple<String, String>> files = new HashSet<>();

        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        conn.commit();

        while (rs.next()) {
            files.add(new Tuple<>(rs.getString("fileName"), rs.getString("ID")));
        }
        return files;
    }
}
