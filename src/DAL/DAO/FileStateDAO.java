package DAL.DAO;

import BL.FileState;
import BL.Session;
import BL.User;
import DAL.ConnectionFactory;
import DAL.DTO.FileStateDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FileStateDAO {
    public static final int INSERT = 0;
    public static final int UPDATE = 1;

    private static FileStateDTO extractFileState(ResultSet rs){
        FileStateDTO file = new FileStateDTO();

        try{
            file.setUserID(rs.getInt("userID"));
            file.setFileName(rs.getString("fileName"));
            file.setCursor(rs.getInt("cursorPos"));
            file.setColor(rs.getInt("color"));
            file.setItalic(rs.getInt("italic"));
            file.setBold(rs.getInt("bold"));
            file.setOpened(rs.getInt("opened"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return file;
    }



    public static FileStateDTO getFileState(int ID, String fileName){
        Connection conn = ConnectionFactory.getConnection();
        String sql="select * from FileState where userID=? and fileName=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ID);
            statement.setString(2, fileName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return extractFileState(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<FileStateDTO> getAllFileStates(int ID){
        Connection conn = ConnectionFactory.getConnection();
        String sql="select * from FileState where userID=?";
        ArrayList<FileStateDTO> files = new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                files.add(extractFileState(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return files;
    }

    public static boolean actionWithFileState(int state, FileStateDTO file) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement statement;
        String sql;
        try {
            switch (state) {
                case INSERT:
                    sql = "insert into FileState values (?, ?, ?, ?, ?, ?, ?)";
                    statement = conn.prepareStatement(sql);
                    statement.setInt(1, file.getUserID());
                    statement.setString(2, file.getFileName());
                    statement.setInt(3, file.getCursor());
                    statement.setInt(4, file.getColor());
                    statement.setInt(5, file.getItalic());
                    statement.setInt(6, file.getBold());
                    statement.setInt(7, file.getOpened());
                    break;
                case UPDATE:
                    sql = "update FileState set cursorPos=?, color=?, italic=?, bold=?, opened=? " +
                            "where userID=? and fileName=?";
                    statement = conn.prepareStatement(sql);
                    statement.setInt(1, file.getCursor());
                    statement.setInt(2, file.getColor());
                    statement.setInt(3, file.getItalic());
                    statement.setInt(4, file.getBold());
                    statement.setInt(5, file.getOpened());
                    statement.setInt(6, file.getUserID());
                    statement.setString(7, file.getFileName());
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

    public static boolean deleteFileState(String fileName){
        Connection conn = ConnectionFactory.getConnection();
        String sql="delete from FileState where fileName=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, fileName);
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
//        deleteFileState("gob");
//        ArrayList<FileStateDTO> files = getAllFileStates(0);
//        for (FileStateDTO file : files){
//            System.out.println(file.getFileName());
//        }
        Session session = new Session(new User(1, "boom", "12"));
    }

}
