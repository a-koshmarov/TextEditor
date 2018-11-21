package BL.Managers;

import BL.FileState;
import DAL.DAO.FileStateDAO;
import DAL.DTO.FileStateDTO;
import Utility.Logger;

public class FileManager{

    private static FileStateDAO fileStateDAO = new FileStateDAO();

    static void removeFile(FileState fileState){
        Logger.getInstance().log(()->fileStateDAO.delete(fileState.getFileStateDTO()));
    }

    static void newFile(FileState fileState){
        Logger.getInstance().log(()->fileStateDAO.add(fileState.getFileStateDTO()));
    }
}
