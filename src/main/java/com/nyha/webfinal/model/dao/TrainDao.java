package com.nyha.webfinal.model.dao;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.entity.Train;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * The interface for working with database table trains
 *
 * @author Dovlet Jumaev
 * @see BaseDao
 */
public interface TrainDao extends BaseDao<Train> {
    /**
     * Finds trains by departure station
     *
     * @param departureStation {@link String}
     * @return {@link List} of trains
     * @throws DaoException if {@link SQLException} occur
     */
    List<Train> findTrainByStation(String departureStation) throws DaoException;

    /**
     * Finds popular trains
     *
     * @return {@link List} of trains
     * @throws DaoException if {@link SQLException} occur
     */
    List<Train> findPopularTrains() throws DaoException;

    /**
     * Finds trains by id
     *
     * @param trainId {@link Long}
     * @return {@link Optional} of {@link Train}
     * @throws DaoException if {@link SQLException} occur
     */
    Optional<Train> findTrainById(Long trainId) throws DaoException;

    /**
     * Adds train
     *
     * @param train {@link Train}
     * @return boolean true if the train added successfully, else false
     * @throws DaoException if {@link SQLException} occur
     */
    boolean addTrain(Train train) throws DaoException;
}
