package gui.model;

import bl.FileState;
import bl.managers.FileManager;
import bl.managers.UserManager;

public class EditorModel {

    public static final int SAVE = 0;
    public static final int SAVE_AS = 1;

    private FileManager fileManager;
    private UserManager userManager;

    public EditorModel(){
        this.fileManager = new FileManager();
//        this.userManager = new UserManager();
    }

    public void save(FileState fileState, int state){
        // TODO: 29.11.2018 create update
    }

    public void create(FileState fileState){
        fileManager.newFile(fileState);
    }

    public boolean exists(String ID){
        return fileManager.exists(ID);
    }
}

