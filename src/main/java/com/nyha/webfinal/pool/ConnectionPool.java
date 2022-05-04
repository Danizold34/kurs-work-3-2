package com.nyha.webfinal.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Pool of connections used while the system is running
 *
 * @author Dovlet Jumaev
 */
public class ConnectionPool {
    static Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 8;
    private static Lock lock = new ReentrantLock();
    private static ConnectionPool instance;
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> givenAwayConnections;

    /**
     * Initialize connection pool
     */
    private ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new LinkedBlockingDeque<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionFactory.createConnection();
                freeConnections.add(new ProxyConnection(connection));
            } catch (SQLException e) {
                logger.fatal("couldn't create connection to data base: ", e);
            }
            if (freeConnections.size() == 0) {
                throw new RuntimeException("couldn't create connection to data base");
            }
        }
    }

    /**
     * Gets instance of this class
     *
     * @return {@link ConnectionPool} instance
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    logger.info("ConnectionPool was created");
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Gets a connection from the connection pool
     *
     * @return {@link Connection} connection to the database
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("InterruptedException in method getConnection " + e);
        }
        return connection;
    }

    /**
     * Returns the connection to the connection pool
     *
     * @param connection {@link Connection} connection to the database
     */
    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class || givenAwayConnections.remove(connection)) {
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.error("Invalid connection to release" + e);
            }
            logger.info("Connection has been released");
        } else {
            logger.error("Invalid connection to release");
        }
    }

    /**
     * Destroy connection pool
     */
    public void destroyPool() {
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                freeConnections.take().reallyClose();
            }
            logger.info("Connection pool has been destroyed");
            deregisterDrivers();
        } catch (SQLException e) {
            logger.error("Connection was not deleted", e);
        } catch (InterruptedException e) {
            logger.error("Thread was interrupted", e);
        }
    }

    /**
     * Unregisters drivers
     *
     * @throws {@link SQLException}
     */
    private void deregisterDrivers() throws SQLException {
        while (DriverManager.getDrivers().hasMoreElements()) {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
        logger.info("Drivers removed from registration");
    }
}
