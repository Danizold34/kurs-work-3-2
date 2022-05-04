package com.nyha.webfinal.model.dao.impl;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.model.dao.PassengerDao;
import com.nyha.webfinal.model.dao.RouteDao;
import com.nyha.webfinal.entity.Route;
import com.nyha.webfinal.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.nyha.webfinal.model.dao.ColumnName.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Works with database table routes
 *
 * @author Dovlet Jumaev
 * @see RouteDao
 */
public class RouteDaoImpl implements RouteDao {
    static Logger logger = LogManager.getLogger();
    private static final String FIND_ALL_ROUTES = "SELECT route_id, train_id, time, station, price FROM routes";
    private static final String ADD_ROUTE = "INSERT INTO `routes` (`train_id`, `time`, `station`, `price`, `station_number`) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<Route> findAll() throws DaoException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ROUTES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getLong(ROUTE_ID));
                route.setStation(resultSet.getString(ROUTE_STATION));
                route.setTime(resultSet.getTime(ROUTE_TIME));
                route.setTrainNumber(resultSet.getLong(ROUTE_TRAIN_NUMBER));
                routes.add(route);
            }
        } catch (SQLException e) {
            logger.error("search error", e);
            throw new DaoException("search error", e);
        }
        return routes;
    }

    @Override
    public boolean addRoute(Route route, int stationNumber) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROUTE)) {
            preparedStatement.setLong(1, route.getTrainNumber());
            preparedStatement.setTime(2, route.getTimeTime());
            preparedStatement.setString(3, route.getStation());
            preparedStatement.setDouble(4, route.getPrice());
            preparedStatement.setInt(5, stationNumber);
            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("add error, " + route, e);
            throw new DaoException("add error, " + route, e);
        }
        return isAdd;
    }
}
