package client;

import client.managerInterface.GenericFileStateManager;
import dao.FileStateEntity;
import dao.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utility.Logger;

public class FileManager implements GenericFileStateManager {

    private SessionFactory sessionFactory;

    public FileManager(){
        this.sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void saveOrUpdate(FileStateEntity fileState) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Logger.getInstance().log(()->session.saveOrUpdate(fileState));
        session.getTransaction().commit();
    }

    @Override
    public void delete(FileStateEntity fileState) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Logger.getInstance().log(()->session.delete(fileState));
        session.getTransaction().commit();
    }
}
