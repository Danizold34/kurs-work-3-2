package test.nyha.webfinal.model.service.impl;

import com.nyha.webfinal.entity.Passenger;
import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.dao.PassengerDao;
import com.nyha.webfinal.model.service.impl.PassengerServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class PassengerServiceImplTest {
    @Mock
    private PassengerDao passengerDao;
    @InjectMocks
    private PassengerServiceImpl passengerService;
    private AutoCloseable autoCloseable;

    private Passenger passenger;

    @BeforeClass
    public void setUp() {
        passenger = new Passenger();
        passenger.setName("Dovlet");
        passenger.setLastName("Jumaev");
        passenger.setPhoneNumber("3752921402334");
        passenger.setPassportNumber("2935434AB");
        passenger.setId(1L);
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
    public void addPassengerPositiveTest() throws DaoException, ServiceException {
        Optional expected = Optional.empty();
        when(passengerDao.addPassenger(any(Passenger.class))).thenReturn(true);
        Optional<String> actual = passengerService.addPassenger(passenger);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void addPassengerNegativeTest() throws DaoException, ServiceException {
        Optional expected = Optional.empty();
        when(passengerDao.addPassenger(any(Passenger.class))).thenReturn(false);
        Optional<String> actual = passengerService.addPassenger(passenger);
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void addPassengerServiceExceptionTest() throws ServiceException, DaoException {
        when(passengerDao.addPassenger(any(Passenger.class))).thenThrow(DaoException.class);
        passengerService.addPassenger(passenger);
    }
}
