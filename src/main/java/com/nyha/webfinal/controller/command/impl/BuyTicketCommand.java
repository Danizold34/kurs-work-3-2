package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.RequestAttribute;
import com.nyha.webfinal.controller.RequestParameter;
import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.entity.Passenger;
import com.nyha.webfinal.entity.Ticket;
import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.model.service.BankService;
import com.nyha.webfinal.model.service.PassengerService;
import com.nyha.webfinal.model.service.TicketService;
import com.nyha.webfinal.model.service.impl.BankServiceImpl;
import com.nyha.webfinal.model.service.impl.PassengerServiceImpl;
import com.nyha.webfinal.model.service.impl.TicketServiceImpl;
import com.nyha.webfinal.validator.BankValidator;
import com.nyha.webfinal.validator.PassengerValidator;
import com.nyha.webfinal.validator.TicketValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

/**
 * The command is responsible for buying a ticket
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class BuyTicketCommand implements Command {
    static Logger logger = LogManager.getLogger();
    public static final String ERROR_ACCESS = "errorAccess";
    public static final String TICKET_BOUGHT = "ticketBought";
    public static final String INCORRECT_DATA = "incorrectData";
    private TicketService ticketService = new TicketServiceImpl();
    private PassengerService passengerService = new PassengerServiceImpl();
    private BankService bankService = new BankServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession(true);
        if (session.getAttribute(SessionAttribute.USER) == null) {
            request.setAttribute(RequestAttribute.EXCEPTION, ERROR_ACCESS);
            router.setPage(PagePath.ERROR_500);
            return router;
        }
        session.setAttribute(SessionAttribute.CURRENT_COMMAND, PagePath.INDEX);
        String trainIdStr = request.getParameter(RequestParameter.TRAIN_ID);
        Long trainId = Long.parseLong(trainIdStr);
        String departureStation = request.getParameter(RequestParameter.DEPARTURE_STATION);
        String arrivalStation = request.getParameter(RequestParameter.ARRIVAL_STATION);
        String arrivalTime = request.getParameter(RequestParameter.ARRIVAL_TIME);
        String departureTime = request.getParameter(RequestParameter.DEPARTURE_TIME);
        String priceStr = request.getParameter(RequestParameter.PRICE);
        double price = Double.parseDouble(priceStr);
        String name = request.getParameter(RequestParameter.NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String passportNumber = request.getParameter(RequestParameter.PASSPORT_NUMBER);
        String phoneNumber = request.getParameter(RequestParameter.PHONE_NUMBER);
        String seatStr = request.getParameter(RequestParameter.SEAT);
        String dateStr = request.getParameter(RequestParameter.DATE);
        String accountNumber = request.getParameter(RequestParameter.ACCOUNT_NUMBER);
        if (!PassengerValidator.isValidName(name) || !PassengerValidator.isValidName(lastName) ||
                !PassengerValidator.isValidNumber(passportNumber) || !PassengerValidator.isValidNumber(phoneNumber) ||
                !TicketValidator.isValidSeat(seatStr) || !BankValidator.isValidAccountNumber(accountNumber)) {
            request.setAttribute(RequestAttribute.INCORRECT_DATA, INCORRECT_DATA);
            router.setPage(PagePath.TICKET);
            request.setAttribute(RequestAttribute.TRAIN_ID, trainIdStr);
            request.setAttribute(RequestAttribute.DEPARTURE_STATION, departureStation);
            request.setAttribute(RequestAttribute.ARRIVAL_STATION, arrivalStation);
            request.setAttribute(RequestAttribute.ARRIVAL_TIME, arrivalTime);
            request.setAttribute(RequestAttribute.DEPARTURE_TIME, departureTime);
            request.setAttribute(RequestAttribute.PRICE, price);
            return router;
        }
        try {
            Optional<String> message = bankService.debitAccount(accountNumber, price);
            User user = (User) session.getAttribute(SessionAttribute.USER);
            Passenger passenger = new Passenger(name, lastName, passportNumber, phoneNumber, user.getId());

            Timestamp departureDate = Timestamp.valueOf(dateStr + " " + departureTime + ":00");
            if (departureTime.compareTo(arrivalTime) > 0) {
                LocalDate date = LocalDate.parse(dateStr);
                dateStr = date.plusDays(1).toString();
            }
            Timestamp arrivalDate = Timestamp.valueOf(dateStr + " " + arrivalTime + ":00");
            int seat = Integer.parseInt(seatStr);
            Ticket ticket = new Ticket(passenger, trainId, departureStation, arrivalStation, seat, departureDate, arrivalDate, price);
            if (message.isPresent()) {
                request.setAttribute(RequestAttribute.INCORRECT_DATA, message.get());
                router.setPage(PagePath.TICKET);
                request.setAttribute(RequestAttribute.TRAIN_ID, trainIdStr);
                request.setAttribute(RequestAttribute.DEPARTURE_STATION, departureStation);
                request.setAttribute(RequestAttribute.ARRIVAL_STATION, arrivalStation);
                request.setAttribute(RequestAttribute.ARRIVAL_TIME, arrivalTime);
                request.setAttribute(RequestAttribute.DEPARTURE_TIME, departureTime);
                request.setAttribute(RequestAttribute.PRICE, price);
                return router;
            }
            passengerService.addPassenger(passenger);
            ticketService.addTicket(ticket);
            session.setAttribute(SessionAttribute.TICKET_BOUGHT, TICKET_BOUGHT);
            router.setPage(PagePath.MESSAGE);
            router.setRedirect();
        } catch (ServiceException e) {
            logger.error("buy ticket error", e);
            router.setPage(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
        }
        return router;
    }
}
