package BL;

import DAL.DTO.FileStateDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FileState implements Comparable<FileState> {
    private String fileName;
    private String ID;
    private String PID;
    private String OID;
    private String content;
    private String dateTime;
    private int version = 0;
    private int access = 0;
    private boolean personal = false;


    public FileState(String fileName){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.fileName = fileName;
        this.content = "";
        this.ID = UUID.randomUUID().toString();
        this.PID = this.ID;
        this.dateTime = dtf.format(LocalDateTime.now());
    }

    public FileState(String ID, String fileName){
        this(fileName);
        this.ID = ID;
    }

    public FileState(String fileName, String OID, int access){
        this(fileName);
        if (!OID.isEmpty()) {
            this.personal = true;
            this.OID = OID;
        }
        this.access = access;
    }

    public FileState(FileStateDTO file){
        this.fileName = file.getFileName();
        this.ID = file.getID();
        this.PID = file.getPID();
        this.content = file.getContent();
        this.dateTime = file.getDateTime();
        this.version = file.getVersion();
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

    public String getDate() {
        return dateTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public FileStateDTO getFileStateDTO(){
        return new FileStateDTO(fileName, ID, PID, content, dateTime, version);
    }

    @Override
    public String toString() {
        return  "no formatting yet :(";
    }

    @Override
    public int compareTo(FileState o) {
        return 0;
    }
}
