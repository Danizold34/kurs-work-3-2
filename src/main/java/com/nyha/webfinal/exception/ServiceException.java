package com.nyha.webfinal.exception;

/**
 * Describes exception in Service
 *
 * @author Dovlet Jumaev
 * @see Exception
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}