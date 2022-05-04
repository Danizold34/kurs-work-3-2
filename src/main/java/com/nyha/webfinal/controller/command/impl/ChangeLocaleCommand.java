package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.RequestParameter;
import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The command is responsible for changing a locale
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class ChangeLocaleCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String locale = request.getParameter(RequestParameter.LANGUAGE);
        session.setAttribute(SessionAttribute.LOCALE, locale);
        String currentCommand = (String) session.getAttribute(SessionAttribute.CURRENT_COMMAND);
        Router router = new Router();
        router.setPage(currentCommand);
        logger.debug("currentCommand " + currentCommand);
        return router;
    }
}
