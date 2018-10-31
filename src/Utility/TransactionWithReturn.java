package Utility;

import java.sql.SQLException;

public interface TransactionWithReturn<T> {
    T runWithReturn() throws SQLException;
}
