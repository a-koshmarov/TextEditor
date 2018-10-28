package BL;

import App.EditorTabMenu;
import DAL.DAO.FileStateDAO;
import DAL.DTO.FileStateDTO;

import java.util.HashSet;
import java.util.Set;

public class Session {
    private User user;
    private Set<FileState> files;
    private EditorTabMenu editorMenu;

    public Session(User user){
        this.user = user;
        this.files = new HashSet<>();

        Set<FileStateDTO> filesDTO = FileStateDAO.getAllFileStates(user.getID());
        for (FileStateDTO file : filesDTO){
            files.add(new FileState(file));
        }

        this.editorMenu = new EditorTabMenu(this);
    }

    public EditorTabMenu getEditorMenu(){
        return editorMenu;
    }

    public void close(){
        editorMenu.close();
    }

    public void newTab(){
        editorMenu.newTab();
    }

    public void open(){
        editorMenu.open();
    }

    public void save(int state){
        editorMenu.save(state);
    }

    public User getUser() {
        return user;
    }

    public Set<FileState> getFiles() {
        return files;
    }

}
