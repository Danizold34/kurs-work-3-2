package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.RequestAttribute;
import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.entity.Ticket;
import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.model.service.TicketService;
import com.nyha.webfinal.model.service.impl.TicketServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The command is responsible for going to the tickets page
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class ToMyTicketsPageCommand implements Command {
    static Logger logger = LogManager.getLogger();
    public static final String NOT_TICKETS = "notTickets";
    public static final String ERROR_ACCESS = "errorAccess";
    private TicketService service = new TicketServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession(true);
        if (session.getAttribute(SessionAttribute.USER) == null) {
            request.setAttribute(RequestAttribute.EXCEPTION, ERROR_ACCESS);
            router.setPage(PagePath.ERROR_500);
            return router;
        }
        session.setAttribute(SessionAttribute.CURRENT_COMMAND, PagePath.TO_MY_TICKETS);
        router.setPage(PagePath.TICKETS);
        User user = (User) session.getAttribute(SessionAttribute.USER);
        Long userId = user.getId();
        List<Ticket> tickets;
        try {
            tickets = service.findUsersTickets(userId);
            if (!tickets.isEmpty()) {
                request.setAttribute(RequestAttribute.TICKETS, tickets);
            } else {
                request.setAttribute(RequestAttribute.MESSAGE, NOT_TICKETS);
            }
        } catch (ServiceException e) {
            logger.error("search error", e);
            router.setPage(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
        }
        return router;
    }
}
