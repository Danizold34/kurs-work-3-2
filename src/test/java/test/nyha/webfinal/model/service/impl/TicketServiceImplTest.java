package test.nyha.webfinal.model.service.impl;

import com.nyha.webfinal.entity.Passenger;
import com.nyha.webfinal.entity.Ticket;
import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.dao.TicketDao;
import com.nyha.webfinal.model.service.impl.TicketServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class TicketServiceImplTest {
    @Mock
    private TicketDao ticketDao;
    @InjectMocks
    private TicketServiceImpl ticketService;
    private AutoCloseable autoCloseable;

    private Ticket ticket;

    @BeforeClass
    public void setUp() {
        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setPassenger(new Passenger("Dovlet", "Jumaev", "3752921402334", "2935434AB", 1L));
        ticket.setDepartureStation("Zhabinka");
        ticket.setArrivalStation("Minsk");
        ticket.setDepartureDate(Timestamp.valueOf("2022-06-24 10:00:00"));
        ticket.setArrivalDate(Timestamp.valueOf("2022-06-24 13:00:00"));
        ticket.setSeat(1);
        ticket.setPrice(10);
        ticket.setTrainId(1L);
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
    public void findAllTicketsPositiveTest() throws DaoException, ServiceException {
        List<Ticket> expected = List.of(ticket);
        when(ticketDao.findAll()).thenReturn(List.of(ticket));
        List<Ticket> actual = ticketService.findAllTickets();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findAllTicketsNegativeTest() throws DaoException, ServiceException {
        List<Ticket> expected = List.of(ticket);
        when(ticketDao.findAll()).thenReturn(Collections.emptyList());
        List<Ticket> actual = ticketService.findAllTickets();
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllTicketsServiceExceptionTest() throws ServiceException, DaoException {
        when( ticketDao.findAll()).thenThrow(DaoException.class);
        ticketService.findAllTickets();
    }

    @Test
    public void findUsersTicketsPositiveTest() throws DaoException, ServiceException {
        List<Ticket> expected = List.of(ticket);
        when(ticketDao.findUsersTickets(anyLong())).thenReturn(List.of(ticket));
        List<Ticket> actual = ticketService.findUsersTickets(1L);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findUsersTicketsNegativeTest() throws DaoException, ServiceException {
        List<Ticket> expected = List.of(ticket);
        when(ticketDao.findUsersTickets(anyLong())).thenReturn(Collections.emptyList());
        List<Ticket> actual = ticketService.findUsersTickets(1L);
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findUsersTicketsServiceExceptionTest() throws ServiceException, DaoException {
        when( ticketDao.findUsersTickets(anyLong())).thenThrow(DaoException.class);
        ticketService.findUsersTickets(1L);
    }

    @Test
    public void addTicketPositiveTest() throws DaoException, ServiceException {
        Optional expected = Optional.empty();
        when(ticketDao.addTicket(any(Ticket.class))).thenReturn(true);
        Optional<String> actual = ticketService.addTicket(ticket);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void addTicketNegativeTest() throws DaoException, ServiceException {
        Optional expected = Optional.empty();
        when(ticketDao.addTicket(any(Ticket.class))).thenReturn(false);
        Optional<String> actual = ticketService.addTicket(ticket);
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void addTicketServiceExceptionTest() throws ServiceException, DaoException {
        when( ticketDao.addTicket(any(Ticket.class))).thenThrow(DaoException.class);
        ticketService.addTicket(ticket);
    }
}
