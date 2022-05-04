package com.nyha.webfinal.model.dao.impl;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.model.dao.RouteDao;
import com.nyha.webfinal.model.dao.TicketDao;
import com.nyha.webfinal.entity.Passenger;
import com.nyha.webfinal.entity.Ticket;
import com.nyha.webfinal.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.nyha.webfinal.model.dao.ColumnName.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Works with database table tickets
 *
 * @author Dovlet Jumaev
 * @see TicketDao
 */
public class TicketDaoImpl implements TicketDao {
    static Logger logger = LogManager.getLogger();
    private static final String FIND_ALL_TICKETS = "SELECT ticket_id, train_id, departure_station, arrival_station, seat, departure_date, arrival_date, ticket_price, passengers.passenger_id, first_name, last_name, passport_number, phone_number, user_id FROM tickets JOIN passengers ON tickets.passenger_id = passengers.passenger_id";
    private static final String FIND_USERS_TICKETS = "SELECT ticket_id, train_id, departure_station, arrival_station, seat, departure_date, arrival_date, ticket_price, passengers.passenger_id, first_name, last_name, passport_number, phone_number, user_id FROM tickets JOIN passengers ON tickets.passenger_id = passengers.passenger_id WHERE user_id = ?";
    private static final String ADD_TICKET = "INSERT INTO `tickets` (`train_id`, `passenger_id`, `departure_station`, `arrival_station`, `seat`, `departure_date`, `arrival_date`, `ticket_price`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<Ticket> findAll() throws DaoException {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TICKETS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong(TICKET_ID));
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getLong(PASSENGER_ID));
                passenger.setName(resultSet.getString(PASSENGER_FIRST_NAME));
                passenger.setLastName(resultSet.getString(PASSENGER_LAST_NAME));
                passenger.setPassportNumber(resultSet.getString(PASSENGER_PASSPORT_NUMBER));
                passenger.setPhoneNumber(resultSet.getString(PASSENGER_PHONE_NUMBER));
                passenger.setUserId(resultSet.getLong(USER_ID));
                ticket.setPassenger(passenger);
                ticket.setTrainId(resultSet.getLong(TRAIN_ID));
                ticket.setDepartureStation(resultSet.getString(TICKET_DEPARTURE_STATION));
                ticket.setArrivalStation(resultSet.getString(TICKET_ARRIVAL_STATION));
                ticket.setSeat(resultSet.getInt(TICKET_SEAT));
                ticket.setDepartureDate(resultSet.getTimestamp(TICKET_DEPARTURE_DATE));
                ticket.setArrivalDate(resultSet.getTimestamp(TICKET_ARRIVAL_DATE));
                ticket.setPrice(resultSet.getDouble(TICKET_PRICE));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            logger.error("search error", e);
            throw new DaoException("search error", e);
        }
        return tickets;
    }

    @Override
    public boolean addTicket(Ticket ticket) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TICKET)) {
            preparedStatement.setLong(1, ticket.getTrainId());
            preparedStatement.setLong(2, Long.parseLong(ticket.getPassenger().getPassportNumber()));
            preparedStatement.setString(3, ticket.getDepartureStation());
            preparedStatement.setString(4, ticket.getArrivalStation());
            preparedStatement.setInt(5, ticket.getSeat());
            preparedStatement.setTimestamp(6, ticket.getDepartureDateDate());
            preparedStatement.setTimestamp(7, ticket.getArrivalDateDate());
            preparedStatement.setDouble(8, ticket.getPrice());
            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("add error, " + ticket, e);
            throw new DaoException("add error, " + ticket, e);
        }
        return isAdd;
    }

    @Override
    public List<Ticket> findUsersTickets(Long userId) throws DaoException {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERS_TICKETS)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong(TICKET_ID));
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getLong(PASSENGER_ID));
                passenger.setName(resultSet.getString(PASSENGER_FIRST_NAME));
                passenger.setLastName(resultSet.getString(PASSENGER_LAST_NAME));
                passenger.setPassportNumber(resultSet.getString(PASSENGER_PASSPORT_NUMBER));
                passenger.setPhoneNumber(resultSet.getString(PASSENGER_PHONE_NUMBER));
                passenger.setUserId(resultSet.getLong(USER_ID));
                ticket.setPassenger(passenger);
                ticket.setTrainId(resultSet.getLong(TRAIN_ID));
                ticket.setDepartureStation(resultSet.getString(TICKET_DEPARTURE_STATION));
                ticket.setArrivalStation(resultSet.getString(TICKET_ARRIVAL_STATION));
                ticket.setSeat(resultSet.getInt(TICKET_SEAT));
                ticket.setDepartureDate(resultSet.getTimestamp(TICKET_DEPARTURE_DATE));
                ticket.setArrivalDate(resultSet.getTimestamp(TICKET_ARRIVAL_DATE));
                ticket.setPrice(resultSet.getDouble(TICKET_PRICE));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            logger.error("search error", e);
            throw new DaoException("search error", e);
        }
        return tickets;
    }
}
