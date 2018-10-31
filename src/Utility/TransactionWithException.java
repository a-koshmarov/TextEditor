package Utility;

import java.sql.SQLException;

public interface TransactionWithException {
    void run() throws SQLException;
}
