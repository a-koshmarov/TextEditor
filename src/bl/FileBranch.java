package bl;

import java.util.UUID;

public class FileBranch {
    private String ID;
    private FileState HEAD;

    public FileBranch(FileState fileState) {
        this.ID = UUID.randomUUID().toString();
        this.HEAD = fileState;
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
