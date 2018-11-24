package BL.Managers;

import BL.FileState;
import DAL.DAO.FileStateDAO;
import DAL.DTO.FileStateDTO;
import Utility.Logger;

import java.util.ArrayList;
import java.util.Set;

public class FileManager{

    private static FileStateDAO fileStateDAO = new FileStateDAO();

    public void removeFile(FileState fileState){
        Logger.getInstance().log(()->fileStateDAO.delete(fileState.getFileStateDTO()));
    }

    public void newFile(FileState fileState){
        Logger.getInstance().log(()->fileStateDAO.add(fileState.getFileStateDTO()));
    }

    public ArrayList<FileState> getBranch (String PID){
        ArrayList<FileState> files = new ArrayList<>();
        Set<FileStateDTO> fileStateDTOS = Logger.getInstance().logWithReturn(()-> fileStateDAO.getAllVersions(PID));
        for (FileStateDTO dto : fileStateDTOS){
            files.add(new FileState(dto));
        }
        return files;
    }


}
