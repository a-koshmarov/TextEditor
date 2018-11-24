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
    private String version;
    private String message;
    private int access = 0;
    private boolean personal = false;


    public FileState(String fileName){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.fileName = fileName;
        this.content = "";
        this.message = "";
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
        this.OID = file.getOID();
        this.content = file.getContent();
        this.dateTime = file.getDateTime();
        this.version = file.getVersion();
        this.message = file.getMessage();
        this.access = file.getAccess();
        this.personal = file.getPersonal() == 1;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public boolean isPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }

    public FileStateDTO getFileStateDTO(){
        return new FileStateDTO(fileName, ID, PID, OID, content, dateTime, version, message, access, personal);
    }

    @Override
    public String toString() {
        return  String.format("fileName:%s ID:%s PID:%s ver:%s commit:%s", fileName, ID, PID, version, message);
    }

    @Override
    public int compareTo(FileState o) {
        return 0;
    }
}
