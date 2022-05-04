package com.nyha.webfinal.model.dao.impl;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.model.dao.BankDao;
import com.nyha.webfinal.model.dao.PassengerDao;
import com.nyha.webfinal.entity.Passenger;
import com.nyha.webfinal.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.nyha.webfinal.model.dao.ColumnName.*;

/**
 * Works with database table passengers
 *
 * @author Dovlet Jumaev
 * @see PassengerDao
 */
public class PassengerDaoImpl implements PassengerDao {
    static Logger logger = LogManager.getLogger();
    private static final String FIND_ALL_PASSENGERS = "SELECT passenger_id, first_name, last_name, passport_number, phone_number, user_id FROM passengers";
    private static final String ADD_PASSENGER = "INSERT INTO `passengers` (`first_name`, `last_name`, `passport_number`, `phone_number`, `user_id`, `passenger_id`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_PASSENGERS_BY_ID = "SELECT passenger_id, first_name, last_name, passport_number, phone_number, user_id FROM passengers WHERE passenger_id = ?";
    private static final String UPDATE_PASSENGER = "UPDATE passengers SET first_name = ?, last_name = ?, phone_number = ? WHERE passport_number = ?";

    @Override
    public List<Passenger> findAll() throws DaoException {
        List<Passenger> passengers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PASSENGERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getLong(PASSENGER_ID));
                passenger.setName(resultSet.getString(PASSENGER_FIRST_NAME));
                passenger.setLastName(resultSet.getString(PASSENGER_LAST_NAME));
                passenger.setPassportNumber(resultSet.getString(PASSENGER_PASSPORT_NUMBER));
                passenger.setPhoneNumber(resultSet.getString(PASSENGER_PHONE_NUMBER));
                passenger.setUserId(resultSet.getLong(USER_ID));
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            logger.error("search error", e);
            throw new DaoException("search error", e);
        }
        return passengers;
    }

    @Override
    public boolean addPassenger(Passenger passenger) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PASSENGER);
             PreparedStatement preparedStatementFind = connection.prepareStatement(FIND_PASSENGERS_BY_ID)) {
            preparedStatementFind.setLong(1, Long.parseLong(passenger.getPassportNumber()));
            ResultSet resultSet = preparedStatementFind.executeQuery();
            if (resultSet.next()) {
                updatePassenger(passenger);
                return true;
            }
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getLastName());
            preparedStatement.setString(3, passenger.getPassportNumber());
            preparedStatement.setString(4, passenger.getPhoneNumber());
            preparedStatement.setLong(5, passenger.getUserId());
            preparedStatement.setLong(6, Long.parseLong(passenger.getPassportNumber()));
            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("add error, " + passenger, e);
            throw new DaoException("add error, " + passenger, e);
        }
        return isAdd;
    }

    /**
     * Updates passenger
     *
     * @param passenger {@link Passenger}
     * @return boolean true if the passenger updated successfully, else false
     * @throws DaoException if {@link SQLException} occur
     */
    private boolean updatePassenger(Passenger passenger) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSENGER)) {
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getLastName());
            preparedStatement.setString(3, passenger.getPhoneNumber());
            preparedStatement.setString(4, passenger.getPassportNumber());
            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("update error, " + passenger, e);
            throw new DaoException("update error, " + passenger, e);
        }
        return isUpdate;
    }
}
