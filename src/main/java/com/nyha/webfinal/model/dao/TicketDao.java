package com.nyha.webfinal.model.dao;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.entity.Ticket;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface for working with database table tickets
 *
 * @author Dovlet Jumaev
 * @see BaseDao
 */
public interface TicketDao extends BaseDao<Ticket> {
    /**
     * Adds a ticket
     *
     * @param ticket {@link Ticket}
     * @return boolean true if the ticket added successfully, else false
     * @throws DaoException if {@link SQLException} occur
     */
    boolean addTicket(Ticket ticket) throws DaoException;
    /**
     * Finds tickets by user id
     *
     * @param userId {@link Long}
     * @return {@link List} of tickets
     * @throws DaoException if {@link SQLException} occur
     */
    List<Ticket> findUsersTickets(Long userId) throws DaoException;
}