package com.nyha.webfinal.model.service.impl;

import com.nyha.webfinal.entity.Route;
import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.dao.RouteDao;
import com.nyha.webfinal.model.dao.impl.RouteDaoImpl;
import com.nyha.webfinal.model.service.RouteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The service is responsible for user operations
 *
 * @author Dovlet Jumaev
 * @see RouteService
 */
public class RouteServiceImpl implements RouteService {
    static Logger logger = LogManager.getLogger();
    private RouteDao routeDao = new RouteDaoImpl();

    @Override
    public Optional<String> addRoutes(List<Route> routes) throws ServiceException {
        try {
            for (int i = 0; i < routes.size(); i++) {
                routeDao.addRoute(routes.get(i), i);
            }
        } catch (DaoException e) {
            logger.error("add routes error, " + routes, e);
            throw new ServiceException("add routes error, " + routes, e);
        }
        return Optional.empty();
    }
}
