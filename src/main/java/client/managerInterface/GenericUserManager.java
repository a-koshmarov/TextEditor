package client.managerInterface;

import dao.BranchEntity;
import dao.UserEntity;

import java.util.Collection;

public interface GenericUserManager extends GenericManager<UserEntity> {
    UserEntity getUserEntity(String userName, String pass);
    boolean exists(String userName);
    void addBranch(UserEntity user, BranchEntity branch);
    Collection<BranchEntity> getBranches(UserEntity user);
    void authorize(String userName, String pass);
    void createNewUser(String userName, String pass);
}
