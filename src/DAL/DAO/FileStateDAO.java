package DAL.DAO;

import DAL.Connector;
import DAL.DTO.FileStateDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FileStateDAO implements DAO<FileStateDTO> {

    private Connection conn;

    public FileStateDAO() {
        conn = Connector.getConnection();
    }

    public Set<FileStateDTO> getAllFileStates(int ID) throws SQLException {
        System.out.println("Transaction: Getting file states");

        String sql = "select * from FileState where userID=?";
        Set<FileStateDTO> files = new HashSet<>();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, ID);
        ResultSet rs = statement.executeQuery();
        conn.commit();

        while (rs.next()) {
            files.add(extract(rs));
        }
        return files;
    }

    public static Set<String> getAllFileNames(int ID) throws SQLException {
        Connection conn = Connector.getConnection();
        String sql = "select fileName from FileState where userID=?";
        Set<String> files = new HashSet<>();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, ID);
        ResultSet rs = statement.executeQuery();
        conn.commit();

        while (rs.next()) {
            files.add(rs.getString("fileName"));
        }
        return files;
    }

    @Override
    public void add(FileStateDTO file) throws SQLException {
        String sql = "insert into FileState values (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, file.getUserID());
        statement.setString(2, file.getFileName());
        statement.setInt(3, file.getCursor());
        statement.setInt(4, file.getColor());
        statement.setInt(5, file.getItalic());
        statement.setInt(6, file.getBold());
        statement.setInt(7, file.getOpened());
        statement.executeUpdate();
        conn.commit();
    }

    @Override
    public void update(FileStateDTO file) throws SQLException {
        String sql = "update FileState set cursorPos=?, color=?, italic=?, bold=?, opened=? " +
                "where userID=? and fileName=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, file.getCursor());
        statement.setInt(2, file.getColor());
        statement.setInt(3, file.getItalic());
        statement.setInt(4, file.getBold());
        statement.setInt(5, file.getOpened());
        statement.setInt(6, file.getUserID());
        statement.setString(7, file.getFileName());
        statement.executeUpdate();
        conn.commit();
    }

    @Override
    public void delete(FileStateDTO file) throws SQLException {
        String sql = "delete from FileState where fileName=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, file.getFileName());
        statement.executeUpdate();
        conn.commit();
    }

    @Override
    public FileStateDTO get(FileStateDTO file) throws SQLException {
        String sql = "select * from FileState where userID=? and fileName=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, file.getUserID());
        statement.setString(2, file.getFileName());
        ResultSet rs = statement.executeQuery();
        conn.commit();

        if (rs.next()) {
            return extract(rs);
        }
        return null;
    }

    @Override
    public FileStateDTO extract(ResultSet rs) throws SQLException {
        FileStateDTO file = new FileStateDTO();

        file.setUserID(rs.getInt("userID"));
        file.setFileName(rs.getString("fileName"));
        file.setCursor(rs.getInt("cursorPos"));
        file.setColor(rs.getInt("color"));
        file.setItalic(rs.getInt("italic"));
        file.setBold(rs.getInt("bold"));
        file.setOpened(rs.getInt("opened"));
        return file;
    }
}
