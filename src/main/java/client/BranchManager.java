package client;

import client.managerInterface.GenericBranchManager;
import dao.BranchEntity;
import dao.FileStateEntity;
import dao.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utility.Logger;

import java.util.Collection;

public class BranchManager implements GenericBranchManager {
    private SessionFactory sessionFactory;

    public BranchManager() {
        this.sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void saveOrUpdate(BranchEntity branch) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Logger.getInstance().log(()->session.saveOrUpdate(branch));
        session.getTransaction().commit();
    }

    @Override
    public void delete(BranchEntity branch) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Logger.getInstance().log(()->session.delete(branch));
        session.getTransaction().commit();
    }

    @Override
    public void addFileState(BranchEntity branch, FileStateEntity fileState) {
        branch.getFileStatesById().add(fileState);
    }

    @Override
    public Collection<FileStateEntity> getFiles(BranchEntity branch) {
        return branch.getFileStatesById();
    }
}
