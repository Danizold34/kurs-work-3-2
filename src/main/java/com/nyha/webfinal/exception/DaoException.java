package com.nyha.webfinal.exception;

/**
 * Describes exception in Dao
 *
 * @author Dovlet Jumaev
 * @see Exception
 */
public class DaoException extends Exception {
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}