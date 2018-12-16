package dao;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FileState", schema = "main")
public class FileStateEntity {
    private short id;
    private String content;
    private int version;
    private String message;
    private String date;
    private boolean last;
    private BranchEntity branchByBid;

    public FileStateEntity(){}

    public FileStateEntity(String content){
        this.content = content;
        this.last = true;
    }

    public FileStateEntity(FileStateEntity other){
        this.content = other.getContent();
        this.version = other.getVersion()+1;
        this.last = true;
        this.branchByBid = other.getBranchByBid();
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "last", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    @ManyToOne
    @JoinColumn(name = "BID", referencedColumnName = "ID")
    public BranchEntity getBranchByBid() {
        return branchByBid;
    }

    public void setBranchByBid(BranchEntity branchByBid) {
        this.branchByBid = branchByBid;
    }

    @Override
    public String toString() {
        if (isLast()){
            return String.format("v. %s [latest]", version);
        }
        return String.format("v. %s [%s]", version, date);
    }
}
