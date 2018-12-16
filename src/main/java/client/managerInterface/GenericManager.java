package client.managerInterface;

public interface GenericManager<T> {
    void saveOrUpdate(T entity);
    void delete(T entity);
}
