package BL;

import DAL.DAO.FileStateDAO;
import DAL.DAO.UserDAO;
import DAL.DTO.FileStateDTO;

import java.util.ArrayList;

public class Session {
    private User user;
    private ArrayList<FileStateDTO> filesDTO;
    private ArrayList<FileState> files;

    public Session(User user){
        this.user = user;
        this.filesDTO = FileStateDAO.getAllFileStates(user.getID());
        this.files = new ArrayList<>();
        for (FileStateDTO file : filesDTO){
            FileState f = new FileState(file);
            System.out.println(f);
            files.add(f);
        }
    }
}
