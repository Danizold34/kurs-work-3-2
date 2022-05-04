package com.nyha.webfinal.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The ConnectionFactory is responsible for for creating connections
 *
 * @author Dovlet Jumaev
 */
final class ConnectionFactory {
    static Logger logger = LogManager.getLogger();
    private static final String RESOURCE = "database";
    private static final ResourceBundle resourceBundle;
    private static final String URL = "url";
    private static final String DB_DRIVER = "driver";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DATABASE_URL;
    private static final String DATABASE_PASSWORD;
    private static final String DATABASE_USERNAME;

    static {
        try {
            resourceBundle = ResourceBundle.getBundle(RESOURCE);
            String driverName = resourceBundle.getString(DB_DRIVER);
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            logger.fatal("can't register driver or find a resources" + e);
            throw new ExceptionInInitializerError("fatal: can't register driver or find a resources");
        }
        DATABASE_URL = resourceBundle.getString(URL);
        DATABASE_USERNAME = resourceBundle.getString(USERNAME);
        DATABASE_PASSWORD = resourceBundle.getString(PASSWORD);
    }

    private ConnectionFactory() {
    }

    /**
     * Creates a connection to the database
     *
     * @return {@link Connection} connection to the database
     * @throws SQLException
     */
    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }
}
