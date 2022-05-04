package com.nyha.webfinal.model.dao.impl;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.model.dao.TrainDao;
import com.nyha.webfinal.entity.Route;
import com.nyha.webfinal.entity.Train;
import com.nyha.webfinal.pool.ConnectionPool;

import static com.nyha.webfinal.model.dao.ColumnName.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Works with database table trains
 *
 * @author Dovlet Jumaev
 * @see TrainDao
 */
public class TrainDaoImpl implements TrainDao {
    static Logger logger = LogManager.getLogger();
    private static final String FIND_ALL_TRAINS = "SELECT trains.train_id, trains.number_of_seats, routes.station, routes.route_id, routes.time, routes.price FROM trains JOIN routes ON trains.train_id = routes.train_id order by trains.train_id, routes.station_number";
    private static final String FIND_TRAINS_BY_ID = "SELECT trains.train_id, trains.number_of_seats, routes.station, routes.route_id, routes.time, routes.price FROM trains JOIN routes ON trains.train_id = routes.train_id where trains.train_id = ?  order by trains.train_id, routes.station_number";
    private static final String FIND_ROUTES_BY_STATIONS = "SELECT route_id, train_id, time, station, price FROM routes WHERE station = ?";
    private static final String FIND_POPULAR_TRAINS = "SELECT train_id, count(train_id) FROM tickets group by train_id order by count(train_id) desc limit 5";
    private static final String ADD_TRAIN = "INSERT INTO `trains` (`train_id`, `number_of_seats`) VALUES (?, ?)";

    @Override
    public List<Train> findAll() throws DaoException {
        List<Train> trains = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TRAINS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Train train = new Train();
                List<Route> routes = new ArrayList<>();
                train.setId(resultSet.getLong(TRAIN_ID));
                train.setNumberOfSeats(resultSet.getInt(TRAIN_NUMBER_OF_SEATS));
                do {
                    Route route = new Route();
                    route.setId(resultSet.getLong(ROUTE_ID));
                    route.setStation(resultSet.getString(ROUTE_STATION));
                    route.setTime(resultSet.getTime(ROUTE_TIME));
                    route.setTrainNumber(resultSet.getLong(ROUTE_TRAIN_NUMBER));
                    route.setPrice(resultSet.getDouble(ROUTE_PRICE));
                    routes.add(route);
                } while (resultSet.next() && train.getId().equals(resultSet.getLong(TRAIN_ID)));
                if (!resultSet.isAfterLast()) {
                    resultSet.previous();
                }
                train.setRoutes(routes);
                trains.add(train);
            }
        } catch (SQLException e) {
            logger.error("search trains error", e);
            throw new DaoException("search trains error", e);
        }
        return trains;
    }

    @Override
    public List<Train> findTrainByStation(String departureStation) throws DaoException {
        List<Train> trains = new ArrayList<>();
        List<Route> routes = findRoutesByStation(departureStation);
        if (routes.isEmpty()) {
            return trains;
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_TRAINS_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
        ) {
            for (Route route : routes) {
                preparedStatement.setLong(1, route.getTrainNumber());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Train train = new Train();
                    List<Route> trainRoutes = new ArrayList<>();
                    train.setId(resultSet.getLong(TRAIN_ID));
                    train.setNumberOfSeats(resultSet.getInt(TRAIN_NUMBER_OF_SEATS));
                    do {
                        Route trainRoute = new Route();
                        trainRoute.setId(resultSet.getLong(ROUTE_ID));
                        trainRoute.setStation(resultSet.getString(ROUTE_STATION));
                        trainRoute.setTime(resultSet.getTime(ROUTE_TIME));
                        trainRoute.setTrainNumber(resultSet.getLong(ROUTE_TRAIN_NUMBER));
                        trainRoute.setPrice(resultSet.getDouble(ROUTE_PRICE));
                        trainRoutes.add(trainRoute);
                    } while (resultSet.next() && train.getId().equals(resultSet.getLong(TRAIN_ID)));
                    if (!resultSet.isAfterLast()) {
                        resultSet.previous();
                    }
                    train.setRoutes(trainRoutes);
                    trains.add(train);
                }
                preparedStatement.clearParameters();
            }
        } catch (SQLException e) {
            logger.error("search trains error", e);
            throw new DaoException("search trains error", e);
        }
        return trains;
    }

    @Override
    public Optional<Train> findTrainById(Long trainId) throws DaoException {
        Train train = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_TRAINS_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
        ) {
            preparedStatement.setLong(1, trainId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                train = new Train();
                List<Route> routes = new ArrayList<>();
                train.setId(resultSet.getLong(TRAIN_ID));
                train.setNumberOfSeats(resultSet.getInt(TRAIN_NUMBER_OF_SEATS));
                do {
                    Route route = new Route();
                    route.setId(resultSet.getLong(ROUTE_ID));
                    route.setStation(resultSet.getString(ROUTE_STATION));
                    route.setTime(resultSet.getTime(ROUTE_TIME));
                    route.setTrainNumber(resultSet.getLong(ROUTE_TRAIN_NUMBER));
                    route.setPrice(resultSet.getDouble(ROUTE_PRICE));
                    routes.add(route);
                } while (resultSet.next() && train.getId().equals(resultSet.getLong(TRAIN_ID)));
                if (!resultSet.isAfterLast()) {
                    resultSet.previous();
                }
                train.setRoutes(routes);
            }
            preparedStatement.clearParameters();
        } catch (SQLException e) {
            logger.error("search trains error", e);
            throw new DaoException("search trains error", e);
        }
        return Optional.ofNullable(train);
    }

    @Override
    public List<Train> findPopularTrains() throws DaoException {
        List<Train> trains = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_POPULAR_TRAINS, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long trainId = resultSet.getLong(TRAIN_ID);
                findTrainById(trainId);
                Train train = findTrainById(trainId).get();
                trains.add(train);
            }
        } catch (SQLException e) {
            logger.error("search trains error", e);
            throw new DaoException("search trains error", e);
        }
        return trains;
    }

    @Override
    public boolean addTrain(Train train) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TRAIN)) {
            preparedStatement.setLong(1, train.getId());
            preparedStatement.setInt(2, train.getNumberOfSeats());
            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("add error, " + train, e);
            throw new DaoException("add error, " + train, e);
        }
        return isAdd;
    }

    /**
     * Finds routes by station passenger
     *
     * @param departureStation {@link String}
     * @return {@link List} of {@link Route}
     * @throws DaoException if {@link SQLException} occur
     */
    private List<Route> findRoutesByStation(String departureStation) throws DaoException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROUTES_BY_STATIONS)) {
            preparedStatement.setString(1, departureStation);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getLong(ROUTE_ID));
                route.setStation(resultSet.getString(ROUTE_STATION));
                route.setTime(resultSet.getTime(ROUTE_TIME));
                route.setTrainNumber(resultSet.getLong(ROUTE_TRAIN_NUMBER));
                route.setPrice(resultSet.getDouble(ROUTE_PRICE));
                routes.add(route);
            }
        } catch (SQLException e) {
            logger.error("search error, " + departureStation, e);
            throw new DaoException("search error, " + departureStation, e);
        }
        return routes;
    }
}
