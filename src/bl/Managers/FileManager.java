package bl.managers;

import bl.FileState;
import dal.dao.EditorFileStateDAO;
import dal.dao.FileStateDAO;
import dal.dto.FileStateDTO;
import utility.Logger;

import java.util.ArrayList;
import java.util.Set;

public class FileManager{

    private static EditorFileStateDAO editorFileStateDAO = new EditorFileStateDAO();

    public void removeFile(FileState fileState){
        Logger.getInstance().log(()-> editorFileStateDAO.delete(fileState.getFileStateDTO()));
    }

    public void newFile(FileState fileState){
        Logger.getInstance().log(()-> editorFileStateDAO.add(fileState.getFileStateDTO()));
    }

    public ArrayList<FileState> getBranch (String PID){
        ArrayList<FileState> files = new ArrayList<>();
        Set<FileStateDTO> fileStateDTOS = Logger.getInstance().logWithReturn(()-> editorFileStateDAO.getAllVersions(PID));
        for (FileStateDTO dto : fileStateDTOS){
            files.add(new FileState(dto));
        }
        return files;
    }

    public boolean exists(String ID){
        FileStateDTO dto = Logger.getInstance().logWithReturn(() -> editorFileStateDAO.get(ID));
        if (dto != null){
           return true;
        } else {
            return false;
        }
    }


}
