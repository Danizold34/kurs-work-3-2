package test.nyha.webfinal.model.service.impl;

import com.nyha.webfinal.entity.Route;
import com.nyha.webfinal.entity.ShortTrainData;
import com.nyha.webfinal.entity.Train;
import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.dao.TrainDao;
import com.nyha.webfinal.model.service.impl.TrainServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TrainServiceImplTest {
    @Mock
    private TrainDao trainDao;
    @InjectMocks
    private TrainServiceImpl trainService;
    private AutoCloseable autoCloseable;
    private ShortTrainData shortTrainData;
    private Train train;

    @BeforeClass
    public void setUp() {
        shortTrainData = new ShortTrainData();
        shortTrainData.setTrainId(1L);
        shortTrainData.setPrice(10);
        shortTrainData.setDepartureStation("Brest");
        shortTrainData.setArrivalStation("Minsk");
        shortTrainData.setDepartureTime("10:00");
        shortTrainData.setArrivalTime("13:10");
        List<Route> routes = new ArrayList<>();
        routes.add(new Route("Brest", Time.valueOf("10:00:00"), 5, 1L));
        routes.add(new Route("Baranovichi", Time.valueOf("11:40:00"), 5, 1L));
        routes.add(new Route("Minsk", Time.valueOf("13:10:00"), 0, 1L));
        train = new Train();
        train.setNumberOfSeats(800);
        train.setId(1L);
        train.setRoutes(routes);
    }

    @BeforeMethod
    public void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void tierDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void findAllTrainsPositiveTest() throws DaoException, ServiceException {
        List<ShortTrainData> expected = List.of(shortTrainData);
        when(trainDao.findAll()).thenReturn(List.of(train));
        List<ShortTrainData> actual = trainService.findAllTrains();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findAllTrainsNegativeTest() throws DaoException, ServiceException {
        List<ShortTrainData> expected = List.of(shortTrainData);
        when(trainDao.findAll()).thenReturn(Collections.emptyList());
        List<ShortTrainData> actual = trainService.findAllTrains();
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllTrainsServiceExceptionTest() throws ServiceException, DaoException {
        when(trainDao.findAll()).thenThrow(DaoException.class);
        trainService.findAllTrains();
    }

    @Test
    public void findTrainByStationsPositiveTest() throws DaoException, ServiceException {
        List<ShortTrainData> expected = List.of(shortTrainData);
        when(trainDao.findTrainByStation("Brest")).thenReturn(List.of(train));
        List<ShortTrainData> actual = trainService.findTrainByStations("Brest","Minsk");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findTrainByStationsNegativeTest() throws DaoException, ServiceException {
        List<ShortTrainData> expected = List.of(shortTrainData);
        when(trainDao.findTrainByStation("Baranovichi")).thenReturn(List.of(train));
        List<ShortTrainData> actual = trainService.findTrainByStations("Baranovichi","Minsk");
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findTrainByStationsServiceExceptionTest() throws ServiceException, DaoException {
        when(trainDao.findTrainByStation("Baranovichi")).thenThrow(DaoException.class);
        trainService.findTrainByStations("Baranovichi", "Minsk");
    }

    @Test
    public void findTrainByIdPositiveTest() throws DaoException, ServiceException {
        Optional<Train> expected = Optional.of(train);
        when(trainDao.findTrainById(anyLong())).thenReturn(Optional.of(train));
        Optional<Train> actual = trainService.findTrainById(1L);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findTrainByIdNegativeTest() throws DaoException, ServiceException {
        Optional<Train> expected = Optional.of(train);
        when(trainDao.findTrainById(anyLong())).thenReturn(Optional.empty());
        Optional<Train> actual = trainService.findTrainById(1L);
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findTrainByIdServiceExceptionTest() throws ServiceException, DaoException {
        when(trainDao.findTrainById(anyLong())).thenThrow(DaoException.class);
        trainService.findTrainById(1L);
    }
}
