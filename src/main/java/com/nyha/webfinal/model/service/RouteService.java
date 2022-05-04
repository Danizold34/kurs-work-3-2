package com.nyha.webfinal.model.service;

import com.nyha.webfinal.entity.Route;
import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface for operations with the route
 *
 * @author Dovlet Jumaev
 */
public interface RouteService {
    /**
     * Adds user
     *
     * @param routes {@link List}     {@link Route}
     * @return {@link Optional} of {@link String}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<String> addRoutes(List<Route> routes) throws ServiceException;
}
