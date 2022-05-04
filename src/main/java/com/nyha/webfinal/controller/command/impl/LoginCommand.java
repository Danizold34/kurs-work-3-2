package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.RequestAttribute;
import com.nyha.webfinal.controller.RequestParameter;
import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;
import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.service.UserService;
import com.nyha.webfinal.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The command is responsible for the login
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class LoginCommand implements Command {
    static Logger logger = LogManager.getLogger();
    public static final String INCORRECT_EMAIL_OR_PASSWORD = "incorrectEmailOrPassword";
    private UserService service = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionAttribute.CURRENT_COMMAND, PagePath.LOGIN);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        try {
            Optional<User> user = service.findUserByEmailAndPassword(email, password);
            if (user.isPresent()) {
                session.setAttribute(SessionAttribute.USER, user.get());
                router.setPage(PagePath.INDEX);
                router.setRedirect();
            } else {
                request.setAttribute(RequestAttribute.INCORRECT_DATA, INCORRECT_EMAIL_OR_PASSWORD);
                router.setPage(PagePath.LOGIN);
            }
        } catch (ServiceException e) {
            logger.error("login error", e);
            router.setPage(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
        }
        return router;
    }
}
