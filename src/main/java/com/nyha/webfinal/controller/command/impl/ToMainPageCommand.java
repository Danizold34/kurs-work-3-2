package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.RequestAttribute;
import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.entity.ShortTrainData;
import com.nyha.webfinal.model.service.TrainService;
import com.nyha.webfinal.model.service.impl.TrainServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The command is responsible for going to the main page
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class ToMainPageCommand implements Command {
    static Logger logger = LogManager.getLogger();
    public static final String TRAINS_NOT_FOUND = "trainsNotFound";

    private TrainService service = new TrainServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPage(PagePath.MAIN);
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionAttribute.CURRENT_COMMAND, PagePath.MAIN);
        List<ShortTrainData> shortTrainsData;
        try {
            shortTrainsData = service.findPopularTrains();
            if (shortTrainsData.isEmpty()) {
                request.setAttribute(RequestAttribute.INCORRECT_DATA, TRAINS_NOT_FOUND);
                return router;
            }
            session.setAttribute(SessionAttribute.SHORT_TRAINS_DATA, shortTrainsData);
        } catch (ServiceException e) {
            router.setPage(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            logger.error("search error", e);
        }
        return router;
    }
}
