package com.nyha.webfinal.model.service;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.entity.Passenger;

import java.util.Optional;

/**
 * The interface for operations with the passenger
 *
 * @author Dovlet Jumaev
 */
public interface PassengerService {
    /**
     * Adds passenger
     *
     * @param passenger {@link Passenger}
     * @return {@link Optional} of {@link String}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<String> addPassenger(Passenger passenger) throws ServiceException;
}
