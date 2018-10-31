package BL;

import DAL.DAO.FileStateDAO;
import DAL.DTO.FileStateDTO;
import Utility.Logger;

public class FileManager{

    public static FileStateDAO fileStateDAO = new FileStateDAO();

    static void removeFile(FileState fileState){
        Logger.log(()->fileStateDAO.delete(new FileStateDTO(fileState)));
    }

    static void newFile(FileState fileState){
        Logger.log(()->fileStateDAO.add(new FileStateDTO(fileState)));
    }
}
