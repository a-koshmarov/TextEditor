package gui.model;

import bl.FileState;
import bl.Managers.FileManager;
import bl.Managers.UserManager;

public class EditorModel {

    public static final int SAVE = 0;
    public static final int SAVE_AS = 1;

    private FileManager fileManager;
    private UserManager userManager;

    public void save(FileState fileState, String content, int state){
        if (fileState == null) {
            fileState = new FileState("new");
            fileState.setContent(content);
            fileManager.newFile(fileState);
        } else {
            fileState.setContent(content);
        }
    }
}
