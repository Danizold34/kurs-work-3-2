package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.RequestAttribute;
import com.nyha.webfinal.controller.RequestParameter;
import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.model.service.UserService;
import com.nyha.webfinal.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The command is responsible for user registration
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class RegisterCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private UserService service = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionAttribute.CURRENT_COMMAND, PagePath.REGISTRATION);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String username = request.getParameter(RequestParameter.USERNAME);
        String roleStr = request.getParameter(RequestParameter.ROLE);
        User.Role role = User.Role.valueOf(roleStr.toUpperCase());
        User user = new User(username, email, role);
        try {
            Optional<String> message = service.addUser(user, password);
            if (message.isEmpty()) {
                session.setAttribute(SessionAttribute.USER, user);
                router.setPage(PagePath.INDEX);
                router.setRedirect();
            } else {
                request.setAttribute(RequestAttribute.INCORRECT_DATA, message.get());
                router.setPage(PagePath.REGISTRATION);
            }
        } catch (ServiceException e) {
            logger.error("registration error", e);
            router.setPage(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
        }
        return router;
    }
}
