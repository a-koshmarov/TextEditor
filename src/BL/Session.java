package BL;

import App.EditorTabMenu;
import DAL.DAO.FileStateDAO;
import DAL.DTO.FileStateDTO;
import Utility.Logger;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Session {
    private User user;
    private UserManager userManager;
    private Set<FileState> files;
    private EditorTabMenu editorMenu;
    private FileState currentFile;

    public Session(User user){
        this.user = user;
        this.files = new HashSet<>();
        this.userManager = new UserManager(user);

        FileStateDAO fileStateDAO = new FileStateDAO();
        Set<FileStateDTO> fileStateDTOS = new HashSet<>();

        fileStateDTOS = Logger.logWithReturn(()->fileStateDAO.getAllFileStates(user.getID()));

        for (FileStateDTO file : fileStateDTOS){
            FileState fileState = new FileState(file);
            files.add(fileState);
            if (currentFile == null){
                currentFile = fileState;
            }
        }
//        this.editorMenu = new EditorTabMenu(this);
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

    public void delete() {
        FileManager.removeFile(currentFile);
        files.remove(currentFile);
    }

    public void newFile(String fileName){
        FileState newFileState = new FileState(user.getID(), fileName);
        FileManager.newFile(newFileState);
        files.add(newFileState);
    }

    public User getUser() {
        return user;
    }

    public FileState getCurrentFile() {
        return currentFile;
    }



    public Set<FileState> getFiles() {
        return files;
    }

    public void printFiles() {
        System.out.println(String.format("Printing files of %s", user.getUserName()));
        for (FileState file : files){
            System.out.println(file);
        }
    }



}
