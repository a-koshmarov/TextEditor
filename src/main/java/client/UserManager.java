package client;

import client.managerInterface.GenericUserManager;
import dao.BranchEntity;
import dao.HibernateSessionFactory;
import dao.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import utility.HashGenerator;
import utility.Logger;

import java.util.Collection;
import java.util.List;

public class UserManager implements GenericUserManager {
    private SessionFactory sessionFactory;

    public UserManager() {
        this.sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void saveOrUpdate(UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Logger.getInstance().log(()->session.saveOrUpdate(user));
        session.getTransaction().commit();
    }

    @Override
    public void delete(UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Logger.getInstance().log(()->session.delete(user));
        session.getTransaction().commit();
    }

    @Override
    public UserEntity getUserEntity(String userName, String pass) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        UserEntity userEntity = null;
        List userList = Logger.getInstance().logWithReturn(()->session.createCriteria(UserEntity.class)
                .add(Restrictions.eq("userName", userName))
                .add(Restrictions.eq("password", pass))
                .list());
        session.getTransaction().commit();
        if(!userList.isEmpty()) {
            userEntity = (UserEntity)userList.get(0);
        }
        return userEntity;
    }

    @Override
    public boolean exists(String userName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List userList = Logger.getInstance().logWithReturn(()->session.createCriteria(UserEntity.class)
                .add(Restrictions.eq("userName", userName))
                .list());
        session.getTransaction().commit();
        return !userList.isEmpty();
    }

    @Override
    public void addBranch(UserEntity user, BranchEntity branch) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        user.getBranchesById().add(branch);
        session.getTransaction().commit();
    }

    @Override
    public Collection<BranchEntity> getBranches(UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Collection<BranchEntity> branches = user.getBranchesById();
        session.getTransaction().commit();
        return branches;
    }

    @Override
    public void authorize(String userName, String pass) {
        String hashedPass = HashGenerator.getHash(pass);
        Context.getInstance().authorize(userName, hashedPass);
    }

    @Override
    public void createNewUser(String userName, String pass) {
        saveOrUpdate(new UserEntity(userName, pass));
        Context.getInstance().authorize(userName, pass);
    }
}


