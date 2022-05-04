package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.RequestAttribute;
import com.nyha.webfinal.controller.RequestParameter;
import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 * The command is responsible for going to the ticket page
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class ToBuyTicketPageCommand implements Command {
    public static final String ERROR_ACCESS = "errorAccess";

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
        router.setPage(PagePath.TICKET);
        String trainIdStr = request.getParameter(RequestParameter.TRAIN_ID);
        String departureStation = request.getParameter(RequestParameter.DEPARTURE_STATION);
        String arrivalStation = request.getParameter(RequestParameter.ARRIVAL_STATION);
        String arrivalTime = request.getParameter(RequestParameter.ARRIVAL_TIME);
        String departureTime = request.getParameter(RequestParameter.DEPARTURE_TIME);
        String price = request.getParameter(RequestParameter.PRICE);

        request.setAttribute(RequestAttribute.TRAIN_ID, trainIdStr);
        request.setAttribute(RequestAttribute.DEPARTURE_STATION, departureStation);
        request.setAttribute(RequestAttribute.ARRIVAL_STATION, arrivalStation);
        request.setAttribute(RequestAttribute.ARRIVAL_TIME, arrivalTime);
        request.setAttribute(RequestAttribute.DEPARTURE_TIME, departureTime);
        request.setAttribute(RequestAttribute.PRICE, price);
        request.setAttribute(RequestAttribute.CURRENT_DATE, LocalDate.now().toString());
        return router;
    }
}
