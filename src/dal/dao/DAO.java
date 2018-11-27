package dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAO<T> {
    void connect() throws SQLException;
    void close() throws SQLException;

    void add(T item) throws SQLException;
    void update(T item) throws SQLException;
    void delete(T item) throws SQLException;
    T get(String ID) throws SQLException;
    T extract(ResultSet rs) throws SQLException;
}
