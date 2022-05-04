package com.nyha.webfinal.model.service;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.entity.ShortTrainData;
import com.nyha.webfinal.entity.Train;

import java.util.List;
import java.util.Optional;

/**
 * The interface for operations with the train
 *
 * @author Dovlet Jumaev
 */
public interface TrainService {
    /**
     * Finds all trains
     *
     * @return {@link List} of {@link ShortTrainData}
     * @throws ServiceException if {@link DaoException} occurs
     */
    List<ShortTrainData> findAllTrains() throws ServiceException;

    /**
     * Finds trains by stations
     *
     * @param departureStation {@link String}
     * @param arrivalStation   {@link String}
     * @return {@link List} of {@link ShortTrainData}
     * @throws ServiceException if {@link DaoException} occurs
     */
    List<ShortTrainData> findTrainByStations(String departureStation, String arrivalStation) throws ServiceException;

    /**
     * Finds popular trains
     *
     * @return {@link List} of {@link ShortTrainData}
     * @throws ServiceException if {@link DaoException} occurs
     */
    List<ShortTrainData> findPopularTrains() throws ServiceException;

    /**
     * Finds train by id
     *
     * @param trainId {@link Long}
     * @return {@link Optional} of {@link Train}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<Train> findTrainById(Long trainId) throws ServiceException;

    /**
     * Adds train
     *
     * @param train     {@link Train}
     * @return {@link Optional} of {@link String}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<String> addTrain(Train train) throws ServiceException;
}
