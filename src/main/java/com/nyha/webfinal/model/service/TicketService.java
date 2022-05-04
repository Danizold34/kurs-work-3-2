package com.nyha.webfinal.model.service;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.entity.Ticket;

import java.util.List;
import java.util.Optional;

/**
 * The interface for operations with the ticket
 *
 * @author Dovlet Jumaev
 */
public interface TicketService {
    /**
     * Finds all tickets
     *
     * @return {@link List} of tickets
     * @throws ServiceException if {@link DaoException} occurs
     */
    List<Ticket> findAllTickets() throws ServiceException;

    /**
     * Finds tickets by user id
     *
     * @param userId {@link Long}
     * @return {@link List} of tickets
     * @throws ServiceException if {@link DaoException} occurs
     */
    List<Ticket> findUsersTickets(Long userId) throws ServiceException;

    /**
     * Adds ticket
     *
     * @param ticket {@link Ticket}
     * @return {@link Optional} of {@link String}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<String> addTicket(Ticket ticket) throws ServiceException;
}
