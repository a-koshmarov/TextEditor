package client.managerInterface;

import dao.BranchEntity;
import dao.FileStateEntity;

import java.util.Collection;
import java.util.List;

public interface GenericBranchManager extends GenericManager<BranchEntity> {
    void addFileState(BranchEntity branch, FileStateEntity fileState);
    Collection<FileStateEntity> getFiles(BranchEntity branch);
}
