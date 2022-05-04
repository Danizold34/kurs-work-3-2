package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.RequestAttribute;
import com.nyha.webfinal.controller.RequestParameter;
import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;
import com.nyha.webfinal.entity.Route;
import com.nyha.webfinal.entity.Train;
import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.service.RouteService;
import com.nyha.webfinal.model.service.TrainService;
import com.nyha.webfinal.model.service.impl.RouteServiceImpl;
import com.nyha.webfinal.model.service.impl.TrainServiceImpl;
import com.nyha.webfinal.util.UserAccessControl;
import com.nyha.webfinal.validator.TicketValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The command is responsible for adding a train
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class AddTrainCommand implements Command {
    static Logger logger = LogManager.getLogger();
    public static final String ERROR_ACCESS = "errorAccess";
    public static final String INCORRECT_DATA = "incorrectData";
    public static final String TRAIN_ADDED = "trainAdded";
    private TrainService trainService = new TrainServiceImpl();
    private RouteService routeService = new RouteServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession(true);
        if (session.getAttribute(SessionAttribute.USER) == null) {
            request.setAttribute(RequestAttribute.EXCEPTION, ERROR_ACCESS);
            router.setPage(PagePath.ERROR_500);
            return router;
        }
        if (!UserAccessControl.isValidForRole(request, User.Role.ADMIN)) {
            request.setAttribute(RequestAttribute.EXCEPTION, ERROR_ACCESS);
            router.setPage(PagePath.ERROR_500);
            return router;
        }
        session.setAttribute(SessionAttribute.CURRENT_COMMAND, PagePath.ADD_TRAIN);
        String trainIdStr = request.getParameter(RequestParameter.TRAIN_ID);
        String seatNumberStr = request.getParameter(RequestParameter.SEAT_NUMBER);
        if (!TicketValidator.isValidSeat(seatNumberStr) || !TicketValidator.isValidTrainNumber(trainIdStr)) {
            request.setAttribute(RequestAttribute.INCORRECT_DATA, INCORRECT_DATA);
            router.setPage(PagePath.ADD_TRAIN);
            return router;
        }
        Long trainId = Long.parseLong(trainIdStr);
        int seatNumber = Integer.parseInt(seatNumberStr);
        String[] stations = request.getParameterValues(RequestParameter.STATION);
        String[] time = request.getParameterValues(RequestParameter.TIME);
        String[] prices = request.getParameterValues(RequestParameter.PRICE);
        Train train = new Train();
        train.setId(trainId);
        train.setNumberOfSeats(seatNumber);
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < stations.length; i++) {
            routes.add(new Route(stations[i], Time.valueOf(time[i] + ":00"), Double.parseDouble(prices[i]), trainId));
        }
        try {
            Optional<String> message = trainService.addTrain(train);
            if (message.isPresent()) {
                request.setAttribute(RequestAttribute.INCORRECT_DATA, message);
                return router;
            }
            routeService.addRoutes(routes);
            router.setPage(PagePath.ADD_TRAIN);
            router.setRedirect();
        } catch (ServiceException e) {
            logger.error("add train error", e);
            router.setPage(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
        }
        return router;
    }
}
