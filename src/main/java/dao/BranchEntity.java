package dao;

import client.Context;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Branch", schema = "main")
public class BranchEntity {
    private short id;
    private FileStateEntity fileStateByHead;
    private UserEntity userByUid;
    private String fileName;
    private Collection<FileStateEntity> fileStatesById = new ArrayList<>();
    public BranchEntity(){}

    public BranchEntity(String fileName){
        this.fileName = fileName;
        this.userByUid = Context.getInstance().getUser();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fileName", nullable = true)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @ManyToOne
    @JoinColumn(name = "HEAD", referencedColumnName = "ID", nullable = true)
    public FileStateEntity getFileStateByHead() {
        return fileStateByHead;
    }

    public void setFileStateByHead(FileStateEntity fileStateByHead) {
        this.fileStateByHead = fileStateByHead;
    }

    @ManyToOne
    @JoinColumn(name = "UID", referencedColumnName = "ID", nullable = false)
    public UserEntity getUserByUid() {
        return userByUid;
    }

    public void setUserByUid(UserEntity userByUid) {
        this.userByUid = userByUid;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "branchByBid")
    public Collection<FileStateEntity> getFileStatesById() {
        return fileStatesById;
    }

    public void setFileStatesById(Collection<FileStateEntity> fileStatesById) {
        this.fileStatesById = fileStatesById;
    }

    @Override
    public String toString() {
        return fileName;
    }
}

