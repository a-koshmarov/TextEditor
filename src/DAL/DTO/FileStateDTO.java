package DAL.DTO;

public class FileStateDTO {
    private String fileName;
    private String ID;
    private String PID;
    private String OID;
    private String content;
    private String dateTime;
    private String version;
    private String message;
    private int access;
    private int personal;


    public FileStateDTO(String fileName,
                        String ID,
                        String PID,
                        String OID,
                        String content,
                        String dateTime,
                        String version,
                        String message,
                        int access,
                        boolean personal) {
        this.fileName = fileName;
        this.ID = ID;
        this.PID = PID;
        this.OID = OID;
        this.content = content;
        this.dateTime = dateTime;
        this.version = version;
        this.message = message;
        this.access = access;
        this.personal = personal ? 1 : 0;
    }

    public String getFileName() {
        return fileName;
    }

    public String getID() {
        return ID;
    }

    public String getPID() {
        return PID;
    }

    public String getOID() {
        return OID;
    }

    public String getContent() {
        return content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getVersion() {
        return version;
    }

    public String getMessage() {
        return message;
    }

    public int getAccess() {
        return access;
    }

    public int getPersonal() {
        return personal;
    }
}
