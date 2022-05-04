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
import com.nyha.webfinal.util.UserAccessControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The command is responsible for changing a user's role
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class ChangeRoleCommand implements Command {
    static Logger logger = LogManager.getLogger();
    public static final String ERROR_ACCESS = "errorAccess";
    private UserService service = new UserServiceImpl();

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
        session.setAttribute(SessionAttribute.CURRENT_COMMAND, PagePath.TO_USERS);
        String email = request.getParameter(RequestParameter.EMAIL);
        String username = request.getParameter(RequestParameter.USERNAME);
        String roleStr = request.getParameter(RequestParameter.ROLE);
        User.Role role;
        if (roleStr.equals("0")) {
            role = User.Role.ADMIN;
        }
        else {
            role = User.Role.USER;
        }
        String userIdStr = request.getParameter(RequestParameter.USER_ID);
        Long userId = Long.parseLong(userIdStr);
        User user = new User(username,email, role);
        user.setId(userId);
        List<User> users;
        try {
            service.updateUser(user);
            users = service.findAllUsers();
            request.setAttribute(RequestAttribute.USERS, users);
            router.setPage(PagePath.USERS);
        } catch (ServiceException e) {
            router.setPage(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            logger.error("search error", e);
        }
        return router;
    }
}
