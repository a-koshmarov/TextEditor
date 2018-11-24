package BL;

import BL.Managers.FileManager;

import java.util.ArrayList;
import java.util.List;

public class FileBranch {
    private FileState lastVersion;
    private String PID;
    private List<FileState> files = new ArrayList<>();
    private FileManager fileManager;

    public FileBranch(FileState lastVersion){
        this.lastVersion = lastVersion;
        this.PID = lastVersion.getPID();
        this.files = fileManager.getBranch(PID);
    }




}
