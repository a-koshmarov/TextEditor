package bl;

import java.util.UUID;

public class FileBranch {
    private String ID;
    private FileState HEAD;

    public FileBranch() {
        this.ID = UUID.randomUUID().toString();
    }

    public void setHEAD(FileState HEAD) {
        this.HEAD = HEAD;
    }

    public FileState getHEAD() {
        return HEAD;
    }

    public String getID() {
        return ID;
    }


}
