package utility;

import java.sql.SQLException;

public interface TransactionWithReturn<T> {
    T runWithReturn();
}
