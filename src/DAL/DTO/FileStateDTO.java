package DAL.DTO;

import BL.FileState;
import Utility.CustomColor;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FileStateDTO {
    private String fileName;
    private String ID;
    private String PID;
    private String content;
    private String  dateTime;
    private int version;

    public FileStateDTO(String fileName, String ID, String PID, String content, String dateTime, int version){
        this.fileName = fileName;
        this.ID = ID;
        this.PID = PID;
        this.content = content;
        this.dateTime = dateTime;
        this.version = version;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
