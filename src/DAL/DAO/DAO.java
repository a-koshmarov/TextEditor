package DAL.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public interface DAO<T> {
    void add(T object) throws SQLException;

    void update(T object) throws SQLException;

    void delete(T object) throws SQLException;

    T get(T object) throws SQLException;

    T extract(ResultSet rs) throws SQLException;
}
