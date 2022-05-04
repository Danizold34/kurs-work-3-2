package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.RequestAttribute;
import com.nyha.webfinal.controller.RequestParameter;
import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.entity.Train;
import com.nyha.webfinal.model.service.TrainService;
import com.nyha.webfinal.model.service.impl.TrainServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The command is responsible for finding a train by id
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class FindTrainByIdCommand implements Command {
    static Logger logger = LogManager.getLogger();
    public static final String TRAIN_NOT_FOUND = "trainNotFound";
    private TrainService service = new TrainServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionAttribute.CURRENT_COMMAND, PagePath.INDEX);
        Router router = new Router();
        String trainIdStr = request.getParameter(RequestParameter.TRAIN_ID);
        Long trainId = Long.parseLong(trainIdStr);
        Optional<Train> optionalTrain;
        try {
            optionalTrain = service.findTrainById(trainId);
            if (optionalTrain.isPresent()) {
                request.setAttribute(RequestAttribute.TRAIN, optionalTrain.get());
                request.setAttribute(RequestAttribute.TRAIN_NUMBER, trainIdStr);
            } else {
                request.setAttribute(RequestAttribute.INCORRECT_DATA, TRAIN_NOT_FOUND);
            }
            router.setPage(PagePath.TRAIN);
        } catch (ServiceException e) {
            router.setPage(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            logger.error("search error",e);
        }
        return router;
    }
}
