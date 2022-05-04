package com.nyha.webfinal.model.dao;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.entity.Passenger;

import java.sql.SQLException;

/**
 * The interface for working with database table passengers
 *
 * @author Dovlet Jumaev
 * @see BaseDao
 */
public interface PassengerDao extends BaseDao<Passenger> {
    /**
     * Adds a passenger
     *
     * @param passenger {@link Passenger}
     * @return boolean true if the passenger added successfully, else false
     * @throws DaoException if {@link SQLException} occur
     */
    boolean addPassenger(Passenger passenger) throws DaoException;
}
