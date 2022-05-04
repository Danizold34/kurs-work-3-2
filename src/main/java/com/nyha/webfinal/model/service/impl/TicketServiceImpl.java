package com.nyha.webfinal.model.service.impl;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.dao.TicketDao;
import com.nyha.webfinal.model.dao.impl.TicketDaoImpl;
import com.nyha.webfinal.entity.Ticket;
import com.nyha.webfinal.model.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The service is responsible for ticket operations
 *
 * @author Dovlet Jumaev
 * @see TicketService
 */
public class TicketServiceImpl implements TicketService {
    static Logger logger = LogManager.getLogger();
    public static final String TICKET_NOT_ADDED = "ticketNotAdded";
    private TicketDao ticketDao = new TicketDaoImpl();

    @Override
    public List<Ticket> findAllTickets() throws ServiceException {
        List<Ticket> tickets;
        try {
            tickets = ticketDao.findAll();
        } catch (DaoException e) {
            logger.error("search error", e);
            throw new ServiceException("search error", e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> findUsersTickets(Long userId) throws ServiceException {
        List<Ticket> tickets;
        try {
            tickets = ticketDao.findUsersTickets(userId);
        } catch (DaoException e) {
            logger.error("search error", e);
            throw new ServiceException("search error", e);
        }
        return tickets;
    }

    @Override
    public Optional<String> addTicket(Ticket ticket) throws ServiceException {
        try {
            if (!ticketDao.addTicket(ticket)) {
                return Optional.of(TICKET_NOT_ADDED);
            }
        } catch (DaoException e) {
            logger.error("add ticket error, " + ticket, e);
            throw new ServiceException("add ticket error, " + ticket, e);
        }
        return Optional.empty();
    }
}
