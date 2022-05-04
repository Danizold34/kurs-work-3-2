package com.nyha.webfinal.model.service.impl;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.dao.TrainDao;
import com.nyha.webfinal.model.dao.impl.TrainDaoImpl;
import com.nyha.webfinal.entity.Route;
import com.nyha.webfinal.entity.ShortTrainData;
import com.nyha.webfinal.entity.Train;
import com.nyha.webfinal.model.service.TrainService;
import com.nyha.webfinal.util.MailSender;
import com.nyha.webfinal.util.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * The service is responsible for train operations
 *
 * @author Dovlet Jumaev
 * @see TrainService
 */
public class TrainServiceImpl implements TrainService {
    static Logger logger = LogManager.getLogger();
    public static final String TRAIN_ALREADY_EXISTS = "trainAlreadyExists";
    private TrainDao trainDao = new TrainDaoImpl();

    @Override
    public List<ShortTrainData> findAllTrains() throws ServiceException {
        List<ShortTrainData> resultTrains = new ArrayList<>();
        try {
            List<Train> trains = trainDao.findAll();
            for (Train train : trains) {
                ShortTrainData shortTrainData = new ShortTrainData();
                String departureStation = train.getRoutes().get(0).getStation();
                String arrivalStation = train.getRoutes().get(train.getRoutes().size() - 1).getStation();
                boolean flag = false;
                for (Route route : train.getRoutes()) {
                    if (departureStation.equals(route.getStation())) {
                        shortTrainData.setDepartureTime(route.getTime().toString().substring(0, 5));
                        flag = true;
                    }
                    if (arrivalStation.equals(route.getStation()) && flag) {
                        shortTrainData.setTrainId(train.getId());
                        shortTrainData.setDepartureStation(departureStation);
                        shortTrainData.setArrivalStation(arrivalStation);
                        shortTrainData.setArrivalTime(route.getTime().toString().substring(0, 5));
                        double price = calculatePrice(train, departureStation, arrivalStation);
                        shortTrainData.setPrice(price);
                        resultTrains.add(shortTrainData);
                        break;
                    }
                }
            }
        } catch (DaoException e) {
            logger.error("search error, ", e);
            throw new ServiceException("search error, ", e);
        }
        return resultTrains;
    }

    @Override
    public List<ShortTrainData> findTrainByStations(String departureStation, String arrivalStation) throws ServiceException {
        List<ShortTrainData> resultTrains = new ArrayList<>();
        try {
            List<Train> trains = trainDao.findTrainByStation(departureStation);
            for (Train train : trains) {
                ShortTrainData shortTrainData = new ShortTrainData();
                boolean flag = false;
                for (Route route : train.getRoutes()) {
                    if (departureStation.equals(route.getStation())) {
                        shortTrainData.setDepartureTime(route.getTime().toString().substring(0, 5));
                        flag = true;
                    }
                    if (arrivalStation.equals(route.getStation()) && flag) {
                        shortTrainData.setTrainId(train.getId());
                        shortTrainData.setDepartureStation(departureStation);
                        shortTrainData.setArrivalStation(arrivalStation);
                        shortTrainData.setArrivalTime(route.getTime().toString().substring(0, 5));
                        double price = calculatePrice(train, departureStation, arrivalStation);
                        shortTrainData.setPrice(price);
                        resultTrains.add(shortTrainData);
                        break;
                    }
                }
            }
        } catch (DaoException e) {
            logger.error("search error, " + departureStation + ", " + arrivalStation, e);
            throw new ServiceException("search error, " + departureStation + ", " + arrivalStation, e);
        }
        resultTrains.sort(Comparator.comparing(ShortTrainData::getDepartureTime));
        return resultTrains;
    }

    @Override
    public Optional<Train> findTrainById(Long trainId) throws ServiceException {
        Optional<Train> train;
        try {
            train = trainDao.findTrainById(trainId);
        } catch (DaoException e) {
            logger.error("search train error, " + trainId, e);
            throw new ServiceException("search train error, " + trainId, e);
        }
        return train;
    }

    @Override
    public List<ShortTrainData> findPopularTrains() throws ServiceException {
        List<ShortTrainData> resultTrains = new ArrayList<>();
        try {
            List<Train> trains = trainDao.findPopularTrains();
            for (Train train : trains) {
                ShortTrainData shortTrainData = new ShortTrainData();
                String departureStation = train.getRoutes().get(0).getStation();
                String arrivalStation = train.getRoutes().get(train.getRoutes().size() - 1).getStation();
                boolean flag = false;
                for (Route route : train.getRoutes()) {
                    if (departureStation.equals(route.getStation())) {
                        shortTrainData.setDepartureTime(route.getTime().toString().substring(0, 5));
                        flag = true;
                    }
                    if (arrivalStation.equals(route.getStation()) && flag) {
                        shortTrainData.setTrainId(train.getId());
                        shortTrainData.setDepartureStation(departureStation);
                        shortTrainData.setArrivalStation(arrivalStation);
                        shortTrainData.setArrivalTime(route.getTime().toString().substring(0, 5));
                        double price = calculatePrice(train, departureStation, arrivalStation);
                        shortTrainData.setPrice(price);
                        resultTrains.add(shortTrainData);
                        break;
                    }
                }
            }
        } catch (DaoException e) {
            logger.error("search error, ", e);
            throw new ServiceException("search error, ", e);
        }
        return resultTrains;
    }

    @Override
    public Optional<String> addTrain(Train train) throws ServiceException {
        if (findTrainById(train.getId()).isPresent()) {
            return Optional.of(TRAIN_ALREADY_EXISTS);
        }
        try {
            trainDao.addTrain(train);
        } catch (DaoException e) {
            logger.error("add user error, " + train, e);
            throw new ServiceException("add user error, " + train, e);
        }
        return Optional.empty();
    }

    /**
     * Calculates price
     *
     * @param train            {@link Train}
     * @param departureStation {@link String}
     * @param arrivalStation   {@link String}
     * @return double
     */
    private double calculatePrice(Train train, String departureStation, String arrivalStation) {
        double price = 0;
        boolean flag = false;
        for (Route route : train.getRoutes()) {
            if (departureStation.equals(route.getStation())) {
                flag = true;
            }
            if (arrivalStation.equals(route.getStation())) {
                break;
            }
            if (flag) {
                price += route.getPrice();
            }
        }
        return price;
    }
}
