package in.fssa.productprice.exception;

import java.sql.SQLException;

public class PersistenceException extends Exception {
	
	public PersistenceException(SQLException e) {
        super(e.getMessage(), e);
    }
}
