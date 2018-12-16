package dao;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "User", schema = "main")
public class UserEntity {
    private short id;
    private String userName;
    private String password;
    private Collection<BranchEntity> branchesById;

    public UserEntity(){}

    public UserEntity(String userName, String password){
        this.userName = userName;
        this.password = password;
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
    @Column(name = "userName", nullable = false, length = -1)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password);
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userByUid")
    public Collection<BranchEntity> getBranchesById() {
        return branchesById;
    }

    public void setBranchesById(Collection<BranchEntity> branchesById) {
        this.branchesById = branchesById;
    }
}
